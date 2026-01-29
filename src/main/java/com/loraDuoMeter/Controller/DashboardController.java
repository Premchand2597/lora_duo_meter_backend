package com.loraDuoMeter.Controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; // 1. IMPORT THIS
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; // 2. IMPORT THIS

import com.loraDuoMeter.Entity.DailyUpdateEntity;
import com.loraDuoMeter.Entity.GatewayEntity;
import com.loraDuoMeter.Entity.MeterEntity;
import com.loraDuoMeter.Repo.BuildingRepo;
import com.loraDuoMeter.Repo.DailyUpdateRepo;
import com.loraDuoMeter.Repo.GatewayRepo;
import com.loraDuoMeter.Repo.MeterRepo;
import com.loraDuoMeter.Repo.ResidentRepo;

@RestController // 3. ADD THIS: Makes it a Web API
@RequestMapping("/api/dashboard") // 4. ADD THIS: Defines the base URL
public class DashboardController {
	
	@Autowired
    private BuildingRepo buildingRepo;

	@Autowired
    private ResidentRepo residentRepo;
	
	@Autowired
    private GatewayRepo gatewayRepo;
	
	@Autowired
    private DailyUpdateRepo dailyUpdateRepo;
	
	@Autowired
    private MeterRepo meterRepo;
	
	
	// Define these as static constants at the top of your class to avoid recreating them
	private static final DateTimeFormatter FMT_YMD_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter FMT_DMY_HMS = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	private static final DateTimeFormatter FMT_DMY_HM  = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); // Fixes your specific error
	private static final DateTimeFormatter FMT_DMY      = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	// --- OPTIMIZED: INSTANT STATUS CHECK (With Strict Filtering) ---
    @GetMapping("/meter-status")
    public ResponseEntity<?> getDashboardStats() {
        try {
            // 1. Calculate Time String for "24 hours ago"
            LocalDateTime cutoffTime = LocalDateTime.now().minusHours(24);
            DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String cutoffString = cutoffTime.format(dbFormatter);

            // 2. Fetch All Metadata (Fast enough for metadata tables)
            List<MeterEntity> allMeters = meterRepo.findAll();
            List<GatewayEntity> allGateways = gatewayRepo.findAll();

            // 3. Fetch ONLY active IDs from DB (Optimized via SQL)
            Set<String> activeDevEuis = dailyUpdateRepo.findActiveDevEuisSince(cutoffString);
            Set<String> activeGatewayEuis = dailyUpdateRepo.findActiveGatewayEuisSince(cutoffString);

            // 4. STRICT FILTERING: Meters
            // Must have Building ID AND Building Name
            List<MeterEntity> validMeters = new ArrayList<>();
            int activeMeters = 0;
            int gasMeters = 0;
            int waterMeters = 0;

            for (MeterEntity m : allMeters) {
                boolean hasBuildingId = m.getBuildingId() != null && !m.getBuildingId().trim().isEmpty();
                boolean hasBuildingName = m.getBuildingName() != null && !m.getBuildingName().trim().isEmpty();

                if (hasBuildingId && hasBuildingName) {
                    validMeters.add(m);

                    // Check Type
                    if ("Gas".equalsIgnoreCase(m.getDeviceType())) gasMeters++;
                    else if ("Water".equalsIgnoreCase(m.getDeviceType())) waterMeters++;

                    // Check Activity (O(1) Lookup)
                    if (m.getSerialNo() != null && activeDevEuis.contains(m.getSerialNo().trim())) {
                        activeMeters++;
                    }
                }
            }

            // 5. STRICT FILTERING: Gateways
            // Must have Building ID AND Building Name
            List<GatewayEntity> validGateways = new ArrayList<>();
            int activeGateways = 0;

            for (GatewayEntity g : allGateways) {
                boolean hasBuildingId = g.getBuildingId() != null && !g.getBuildingId().trim().isEmpty();
                boolean hasBuildingName = g.getBuildingName() != null && !g.getBuildingName().trim().isEmpty();

                if (hasBuildingId && hasBuildingName) {
                    validGateways.add(g);
                    
                    // Check Activity
                    if (g.getGatewayId() != null && activeGatewayEuis.contains(g.getGatewayId().trim())) {
                        activeGateways++;
                    }
                }
            }

            // 6. Construct Response
            Map<String, Object> response = new HashMap<>();
            
            // Total Meters is now the size of the FILTERED list, not the raw DB count
            response.put("totalMeters", validMeters.size());
            response.put("activeMeters", activeMeters);
            response.put("inactiveMeters", validMeters.size() - activeMeters);
            response.put("gasMeters", gasMeters);
            response.put("waterMeters", waterMeters);
            
            // Total Gateways is now the size of the FILTERED list
            response.put("totalGateways", validGateways.size());
            response.put("activeGateways", activeGateways);
            response.put("inactiveGateways", validGateways.size() - activeGateways);
            
            response.put("buildings", buildingRepo.count());
            response.put("flats", residentRepo.count());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // --- OPTIMIZED: WEEKLY ACTIVITY (Last 7 Days Only) ---
    @GetMapping("/weekly-meter-activity")
    public ResponseEntity<?> getWeeklyMeterActivity() {
        return getWeeklyActivityGeneric("Gas");
    }

    @GetMapping("/weekly-water-activity")
    public ResponseEntity<?> getWeeklyWaterActivity() {
        return getWeeklyActivityGeneric("Water");
    }

    private ResponseEntity<?> getWeeklyActivityGeneric(String deviceType) {
        try {
            LocalDate today = LocalDate.now();
            LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
            
            LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(8); 
            DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String startString = sevenDaysAgo.format(dbFormatter);

            List<MeterEntity> allMeters = meterRepo.findAll();
            
            // OPTIMIZATION: Only fetch recent updates
            List<DailyUpdateEntity> recentUpdates = dailyUpdateRepo.findUpdatesSince(startString);

            // Pre-process updates
            Map<String, Set<LocalDate>> activityMap = new HashMap<>();
            for (DailyUpdateEntity update : recentUpdates) {
                if (update.getDevEui() != null && update.getReceivedAt() != null) {
                    LocalDate date = parseToLocalDate(update.getReceivedAt());
                    if (date != null) {
                        activityMap.computeIfAbsent(update.getDevEui().trim(), k -> new HashSet<>()).add(date);
                    }
                }
            }

            // STRICT FILTERING for Weekly Data as well
            List<MeterEntity> eligibleMeters = allMeters.stream()
                .filter(m -> m.getBuildingId() != null && !m.getBuildingId().trim().isEmpty())
                .filter(m -> m.getBuildingName() != null && !m.getBuildingName().trim().isEmpty()) // Added Building Name Check
                .filter(m -> deviceType.equalsIgnoreCase(m.getDeviceType()))
                .collect(Collectors.toList());

            List<Map<String, Object>> weeklyData = new ArrayList<>();

            for (int i = 0; i < 7; i++) {
                LocalDate currentDay = startOfWeek.plusDays(i);
                if (currentDay.isAfter(today)) break;

                String dayName = currentDay.getDayOfWeek().name().substring(0, 3);
                int activeCount = 0;
                int eligibleCountForDay = 0;

                for (MeterEntity meter : eligibleMeters) {
                    // Check installation date
                    LocalDate installDate = parseToLocalDate(meter.getDateTime());
                    if (installDate == null || installDate.isAfter(currentDay)) continue;

                    eligibleCountForDay++;

                    Set<LocalDate> activeDates = activityMap.get(meter.getSerialNo().trim());
                    if (activeDates != null && activeDates.contains(currentDay)) {
                        activeCount++;
                    }
                }

                Map<String, Object> dayStats = new HashMap<>();
                dayStats.put("day", dayName);
                dayStats.put("active", activeCount);
                dayStats.put("inactive", eligibleCountForDay - activeCount);
                dayStats.put("total", eligibleCountForDay);
                weeklyData.add(dayStats);
            }

            return ResponseEntity.ok(weeklyData);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
	
	/*@GetMapping("/weekly-meter-activity")
	public ResponseEntity<?> getWeeklyMeterActivity() {
	    try {
	        System.out.println("=== Starting Weekly Meter Activity Calculation ===");

	        LocalDate today = LocalDate.now();
	        
	        LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);

	        System.out.println("Week Range: " + startOfWeek + " to " + today);

	        List<MeterEntity> allMeters = meterRepo.findAll();
	        List<DailyUpdateEntity> allUpdates = dailyUpdateRepo.findAll();

	        System.out.println("Total Meters from DB: " + allMeters.size());
	        System.out.println("Total Updates from DB: " + allUpdates.size());

	        List<Map<String, Object>> weeklyData = new ArrayList<>();

	        for (int i = 0; i < 7; i++) {
	            LocalDate currentDay = startOfWeek.plusDays(i);
	            String dayName = currentDay.getDayOfWeek().name().substring(0, 3);

	            System.out.println("\n--- Processing Day: " + dayName + " (" + currentDay + ") ---");

	            // 1. Eligible gas meters
	            List<MeterEntity> eligibleGasMeters = allMeters.stream()
	                .filter(m -> m.getBuildingId() != null && !m.getBuildingId().trim().isEmpty())
	                .filter(m -> "Gas".equalsIgnoreCase(m.getDeviceType()))
	                .filter(m -> {
	                    LocalDate installDate = parseToLocalDate(m.getDateTime());
	                    if (installDate == null) {
	                     //   System.out.println("Skipping meter (invalid install date): " + m.getSerialNo());
	                    	  System.out.println("❌ Install date rejected | Meter: " + m.getSerialNo() + " | Raw date_time: [" + m.getDateTime() + "]");
	                          
	                        return false;
	                    }
	                    System.out.println("Meter: " + m.getSerialNo() + " | Install Date: " + installDate + " | Current Day: " + currentDay);

	                    return !installDate.isAfter(currentDay);
	                })
	                .collect(Collectors.toList());

	            System.out.println("Eligible Gas Meters: " + eligibleGasMeters.size());

	            int activeCount = 0;

	            for (MeterEntity meter : eligibleGasMeters) {
	                String meterSn = meter.getSerialNo();

	                boolean hasUpdate = allUpdates.stream().anyMatch(update -> {
	                    if (update.getReceivedAt() == null || update.getDevEui() == null) return false;
	                    if (!meterSn.equalsIgnoreCase(update.getDevEui())) return false;

	                    LocalDate updateDate = parseToLocalDate(update.getReceivedAt());
	                    if (updateDate == null) {
	                        System.out.println("Skipping malformed update date: " + update.getReceivedAt());
	                        return false;
	                    }

	                    return updateDate.equals(currentDay);
	                });

	                if (hasUpdate) {
	                    activeCount++;
	                    System.out.println("ACTIVE Meter: " + meterSn);
	                }
	            }

	            int inactiveCount = eligibleGasMeters.size() - activeCount;

	            System.out.println("Day Summary -> Active: " + activeCount + ", Inactive: " + inactiveCount);

	            Map<String, Object> dayStats = new HashMap<>();
	            dayStats.put("day", dayName);
	            dayStats.put("active", activeCount);
	            dayStats.put("inactive", inactiveCount);
	            dayStats.put("total", eligibleGasMeters.size());

	            weeklyData.add(dayStats);

	            if (currentDay.equals(today)) {
	                System.out.println("Reached today. Stopping further days.");
	                break;
	            }
	        }

	        System.out.println("\n=== Weekly Meter Activity Calculation Completed ===");
	        return ResponseEntity.ok(weeklyData);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	    }
	}*/
	
	
	/*@GetMapping("/weekly-water-activity")
    public ResponseEntity<?> getWeeklyWaterActivity() {
        try {
            System.out.println("=== Starting Weekly WATER Meter Activity Calculation ===");

            LocalDate today = LocalDate.now();
            LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);

            List<MeterEntity> allMeters = meterRepo.findAll();
            List<DailyUpdateEntity> allUpdates = dailyUpdateRepo.findAll();

            List<Map<String, Object>> weeklyData = new ArrayList<>();

            for (int i = 0; i < 7; i++) {
                LocalDate currentDay = startOfWeek.plusDays(i);
                
                // Stop if future date
                if (currentDay.isAfter(today)) break;

                String dayName = currentDay.getDayOfWeek().name().substring(0, 3);

                // 1. Eligible WATER meters
                List<MeterEntity> eligibleWaterMeters = allMeters.stream()
                    .filter(m -> m.getBuildingId() != null && !m.getBuildingId().trim().isEmpty())
                    .filter(m -> "Water".equalsIgnoreCase(m.getDeviceType())) // Filter for Water
                    .filter(m -> {
                        LocalDate installDate = parseToLocalDate(m.getDateTime());
                        return installDate != null && !installDate.isAfter(currentDay);
                    })
                    .collect(Collectors.toList());

                int activeCount = 0;

                for (MeterEntity meter : eligibleWaterMeters) {
                    String meterSn = meter.getSerialNo();
                    boolean hasUpdate = allUpdates.stream().anyMatch(update -> {
                        if (update.getReceivedAt() == null || update.getDevEui() == null) return false;
                        if (!meterSn.equalsIgnoreCase(update.getDevEui())) return false;
                        LocalDate updateDate = parseToLocalDate(update.getReceivedAt());
                        return updateDate != null && updateDate.equals(currentDay);
                    });

                    if (hasUpdate) activeCount++;
                }

                int inactiveCount = eligibleWaterMeters.size() - activeCount;

                Map<String, Object> dayStats = new HashMap<>();
                dayStats.put("day", dayName);
                dayStats.put("active", activeCount);
                dayStats.put("inactive", inactiveCount);
                dayStats.put("total", eligibleWaterMeters.size());

                weeklyData.add(dayStats);
            }

            return ResponseEntity.ok(weeklyData);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }*/
 
	
/*	@GetMapping("/meter-growth")
    public ResponseEntity<?> getMeterGrowth(@RequestParam String view) {
        try {
            // 1. Fetch all meters
            List<MeterEntity> allMeters = meterRepo.findAll();

            // 2. Filter: Must have valid Building ID and Name
            List<MeterEntity> validMeters = allMeters.stream()
                .filter(m -> m.getBuildingId() != null && !m.getBuildingId().trim().isEmpty())
                .filter(m -> m.getBuildingName() != null && !m.getBuildingName().trim().isEmpty())
                .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            List<Integer> gasData = new ArrayList<>();
            List<Integer> waterData = new ArrayList<>();
            List<String> categories = new ArrayList<>();

            LocalDate today = LocalDate.now();

            if ("weekly".equalsIgnoreCase(view)) {
                // === WEEKLY VIEW (Mon - Sun of current week) ===
                LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
                
                for (int i = 0; i < 7; i++) {
                    LocalDate currentDay = startOfWeek.plusDays(i);
                    categories.add(currentDay.getDayOfWeek().name().substring(0, 3)); // Mon, Tue...

                    // Calculate Cumulative Count up to this day
                    long gasCount = validMeters.stream()
                        .filter(m -> "Gas".equalsIgnoreCase(m.getDeviceType()))
                        .filter(m -> {
                            LocalDate d = parseToLocalDate(m.getDateTime());
                            return d != null && !d.isAfter(currentDay);
                        }).count();

                    long waterCount = validMeters.stream()
                        .filter(m -> "Water".equalsIgnoreCase(m.getDeviceType()))
                        .filter(m -> {
                            LocalDate d = parseToLocalDate(m.getDateTime());
                            return d != null && !d.isAfter(currentDay);
                        }).count();

                    gasData.add((int) gasCount);
                    waterData.add((int) waterCount);
                }

            } else {
                // === MONTHLY VIEW (Jan - Dec of current year) ===
                int currentYear = today.getYear();
                
                for (int i = 1; i <= 12; i++) {
                    // Get the last day of the month
                    LocalDate endOfMonth = LocalDate.of(currentYear, i, 1).plusMonths(1).minusDays(1);
                    
                    categories.add(java.time.Month.of(i).name().substring(0, 3)); // Jan, Feb...

                    // Calculate Cumulative Count up to end of this month
                    long gasCount = validMeters.stream()
                        .filter(m -> "Gas".equalsIgnoreCase(m.getDeviceType()))
                        .filter(m -> {
                            LocalDate d = parseToLocalDate(m.getDateTime());
                            return d != null && !d.isAfter(endOfMonth);
                        }).count();

                    long waterCount = validMeters.stream()
                        .filter(m -> "Water".equalsIgnoreCase(m.getDeviceType()))
                        .filter(m -> {
                            LocalDate d = parseToLocalDate(m.getDateTime());
                            return d != null && !d.isAfter(endOfMonth);
                        }).count();

                    gasData.add((int) gasCount);
                    waterData.add((int) waterCount);
                }
            }

            response.put("categories", categories);
            response.put("gas", gasData);
            response.put("water", waterData);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error fetching meter growth: " + e.getMessage());
        }
    }*/
	
	@GetMapping("/meter-growth")
    public ResponseEntity<?> getMeterGrowth(@RequestParam String view) {
        try {
            // 1. Fetch all meters and filter valid ones
            List<MeterEntity> allMeters = meterRepo.findAll();
            List<MeterEntity> validMeters = allMeters.stream()
                .filter(m -> m.getBuildingId() != null && !m.getBuildingId().trim().isEmpty())
                .filter(m -> m.getBuildingName() != null && !m.getBuildingName().trim().isEmpty())
                .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            List<Integer> gasData = new ArrayList<>();
            List<Integer> waterData = new ArrayList<>();
            List<String> categories = new ArrayList<>();

            LocalDate today = LocalDate.now();

            // 2. Send the Current Month Name for the Title
            response.put("currentMonth", today.getMonth().name()); // e.g., "JANUARY"

            if ("weekly".equalsIgnoreCase(view)) {
                // === WEEKLY VIEW (Monday -> Today) ===
                LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
                
                // Loop 7 days, but STOP if we pass 'today'
                for (int i = 0; i < 7; i++) {
                    LocalDate currentDay = startOfWeek.plusDays(i);
                    
                    // VALIDATION: If currentDay is in the future, stop the loop!
                    if (currentDay.isAfter(today)) {
                        break; 
                    }

                    categories.add(currentDay.getDayOfWeek().name().substring(0, 3)); // Mon, Tue...
                    
                    long gasCount = getCumulativeCount(validMeters, "Gas", currentDay);
                    long waterCount = getCumulativeCount(validMeters, "Water", currentDay);
                    
                    gasData.add((int) gasCount);
                    waterData.add((int) waterCount);
                }

            } else {
                // === MONTHLY VIEW (Day 1 -> Today) ===
                int daysInMonth = today.lengthOfMonth();
                
                for (int i = 1; i <= daysInMonth; i++) {
                    LocalDate currentDay = LocalDate.of(today.getYear(), today.getMonth(), i);

                    // VALIDATION: If currentDay is in the future, stop the loop!
                    if (currentDay.isAfter(today)) {
                        break;
                    }
                    
                    categories.add(String.valueOf(i)); // "1", "2"...
                    
                    long gasCount = getCumulativeCount(validMeters, "Gas", currentDay);
                    long waterCount = getCumulativeCount(validMeters, "Water", currentDay);
                    
                    gasData.add((int) gasCount);
                    waterData.add((int) waterCount);
                }
            }

            response.put("categories", categories);
            response.put("gas", gasData);
            response.put("water", waterData);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Helper method to count meters installed on or before 'targetDate'
    private long getCumulativeCount(List<MeterEntity> meters, String type, LocalDate targetDate) {
        return meters.stream()
            .filter(m -> type.equalsIgnoreCase(m.getDeviceType()))
            .filter(m -> {
                LocalDate d = parseToLocalDate(m.getDateTime()); // Ensure you have this helper or use logic
                return d != null && !d.isAfter(targetDate);
            }).count();
    }
    
    // Helper to parse date string (adjust format as per your DB "dd-MM-yyyy" or similar)
 
	/*private LocalDate parseToLocalDate(String dateStr) {
	    if (dateStr == null || dateStr.trim().isEmpty()) {
	        return null;
	    }

	    try {
	        // yyyy-MM-dd HH:mm:ss  (MOST COMMON IN YOUR DB)
	        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        return LocalDateTime.parse(dateStr, f1).toLocalDate();
	    } catch (Exception ignored) {}

	    try {
	        // dd-MM-yyyy HH:mm:ss
	        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        return LocalDateTime.parse(dateStr, f2).toLocalDate();
	    } catch (Exception ignored) {}

	    try {
	        // yyyy-MM-dd
	        return LocalDate.parse(dateStr);
	    } catch (Exception ignored) {}

	    try {
	        // dd-MM-yyyy
	        DateTimeFormatter f3 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        return LocalDate.parse(dateStr, f3);
	    } catch (Exception ignored) {}

	    try {
	        // ISO instant: 2026-01-02T10:08:05Z
	        return Instant.parse(dateStr)
	                .atZone(ZoneId.systemDefault())
	                .toLocalDate();
	    } catch (Exception ignored) {}

	    System.out.println("❌ Unparseable date format: " + dateStr);
	    return null;
	}*/

	private LocalDate parseToLocalDate(String dateStr) {
	    if (dateStr == null || dateStr.trim().isEmpty()) {
	        return null;
	    }
	    String val = dateStr.trim();
	    int len = val.length();

	    try {
	        // CASE 1: Full Timestamp (e.g., "2025-12-31 13:54:00" or "31-12-2025 13:54:00")
	        if (len == 19) {
	            // Check separator at index 2 to distinguish yyyy vs dd
	            // "31-..." vs "2025..."
	            if (val.charAt(2) == '-') { 
	                return LocalDateTime.parse(val, FMT_DMY_HMS).toLocalDate();
	            } else {
	                return LocalDateTime.parse(val, FMT_YMD_HMS).toLocalDate();
	            }
	        }
	        
	        // CASE 2: Missing Seconds (e.g., "31-12-2025 13:54") -> THIS FIXES YOUR ERROR
	        else if (len == 16) {
	             return LocalDateTime.parse(val, FMT_DMY_HM).toLocalDate();
	        }

	        // CASE 3: Date Only (e.g., "2025-12-31" or "31-12-2025")
	        else if (len == 10) {
	            if (val.charAt(2) == '-') {
	                return LocalDate.parse(val, FMT_DMY);
	            } else {
	                return LocalDate.parse(val); // Default ISO (yyyy-MM-dd)
	            }
	        }

	        // Fallback for ISO Instant or other weird formats (Keep only one try-catch if really needed)
	        if (val.contains("T")) {
	             return java.time.Instant.parse(val)
	                    .atZone(java.time.ZoneId.systemDefault())
	                    .toLocalDate();
	        }

	    } catch (DateTimeParseException e) {
	        System.err.println("❌ Skipped invalid date: " + val);
	    }

	    return null;
	}
	// --- DATE PARSING HELPERS ---

    private LocalDateTime parseToLocalDateTime(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        try {
            DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(dateStr, f1);
        } catch (Exception e1) {
            try {
                DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                return LocalDateTime.parse(dateStr, f2);
            } catch (Exception e2) {
                try {
                     return Instant.parse(dateStr).atZone(ZoneId.systemDefault()).toLocalDateTime();
                } catch (Exception e3) { return null; }
            }
        }
    }

}