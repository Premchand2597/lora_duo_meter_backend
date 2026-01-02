package com.loraDuoMeter.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; // 1. IMPORT THIS
import org.springframework.web.bind.annotation.RestController; // 2. IMPORT THIS

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
	
	
	@GetMapping("/meter-status")
    public ResponseEntity<?> getDashboardStats() {
        try {
            System.out.println("=== Dashboard Stats API Started ===");

            // 1. Get Today's Date (dd-MM-yyyy to match DB format)
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            
            // ==========================================
            // LOGIC 1: METER STATUS & COUNTS (Gas/Water)
            // ==========================================
            List<String> activeDevEuis = dailyUpdateRepo.findActiveDevEuisForDate(today);
            List<MeterEntity> allMeters = meterRepo.findAll();

            int activeMeters = 0;
            int inactiveMeters = 0;
            int totalGasMeters = 0;
            int totalWaterMeters = 0;

            for (MeterEntity meter : allMeters) {
                // A. Active/Inactive Check
                String serialNo = meter.getSerialNo();
                if (serialNo != null && activeDevEuis.contains(serialNo)) {
                    activeMeters++;
                } else {
                    inactiveMeters++;
                }

                // B. Gas/Water Type Check
                String type = meter.getDeviceType();
                if (type != null) {
                    if (type.equalsIgnoreCase("Gas")) {
                        totalGasMeters++;
                    } else if (type.equalsIgnoreCase("Water")) {
                        totalWaterMeters++;
                    }
                }
            }

            // ==========================================
            // LOGIC 2: GATEWAY STATUS (Active/Inactive)
            // ==========================================
            // Fetch list of gateways that sent data today
            List<String> activeGatewayEuis = dailyUpdateRepo.findActiveGatewayEuisForDate(today);
            List<GatewayEntity> allGateways = gatewayRepo.findAll();

            int activeGateways = 0;
            int inactiveGateways = 0;

            for (GatewayEntity gw : allGateways) {
                // We compare 'gateway_id' from Gateway Table with 'gateway_eui' from Update Table
                String gwId = gw.getGatewayId(); 

                if (gwId != null && activeGatewayEuis.contains(gwId)) {
                    activeGateways++;
                } else {
                    inactiveGateways++;
                }
            }

            // ==========================================
            // 3. CONSTRUCT RESPONSE
            // ==========================================
            Map<String, Object> response = new HashMap<>();
            
            // Meter Stats
            response.put("totalMeters", allMeters.size());
            response.put("activeMeters", activeMeters);
            response.put("inactiveMeters", inactiveMeters);
            response.put("gasMeters", totalGasMeters);
            response.put("waterMeters", totalWaterMeters);
            
            // Gateway Stats
            response.put("totalGateways", allGateways.size());
            response.put("activeGateways", activeGateways);
            response.put("inactiveGateways", inactiveGateways);
            
            // Metadata
            response.put("dateChecked", today);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error calculating stats: " + e.getMessage());
        }
    }

}