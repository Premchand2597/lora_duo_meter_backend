package com.loraDuoMeter.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loraDuoMeter.Entity.BuildingEntity;
import com.loraDuoMeter.Entity.GatewayEntity;
import com.loraDuoMeter.Entity.MeterEntity;
import com.loraDuoMeter.Entity.ResidentEntity;
import com.loraDuoMeter.Repo.BuildingRepo;
import com.loraDuoMeter.Repo.GatewayRepo;
import com.loraDuoMeter.Repo.MeterRepo;
import com.loraDuoMeter.Repo.ResidentRepo;
import com.loraDuoMeter.Service.ExcelHelper;
import com.loraDuoMeter.Service.ExcelService;

@RestController
@RequestMapping("/api/facility")
public class FacilityController {

	@Autowired
    private BuildingRepo buildingRepo;

	@Autowired
    private ExcelService excelService;
	
	@Autowired
    private ResidentRepo residentRepo;
	
	@Autowired
    private GatewayRepo gatewayRepo;
	
	@Autowired
    private MeterRepo meterRepo;
	
	@PostMapping("/add-building")
    public ResponseEntity<?> addBuilding(@RequestBody BuildingEntity building) {
        try {
            // REMOVED: Set the Last Inserted Time manually
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // String currentTime = LocalDateTime.now().format(formatter);
            // building.setLastInsertedTime(currentTime);
        	// --- ADDED: Set Date Time Format ---
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            building.setDateTime(LocalDateTime.now().format(formatter));
            // 2. Save first to generate the numeric ID (sl_no)
            BuildingEntity savedBuilding = buildingRepo.save(building);

            // 3. Generate the custom ID (e.g., "Buil1", "Buil2")
            String customId = "Buil" + savedBuilding.getId();
            savedBuilding.setBuildingId(customId);

            // 4. Save again to update the building_id column
            BuildingEntity finalBuilding = buildingRepo.save(savedBuilding);

            return ResponseEntity.ok(finalBuilding);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving building: " + e.getMessage());
        }
    }
	
	
	@PutMapping("/update-building/{id}")
    public ResponseEntity<?> updateBuilding(@PathVariable Long id, @RequestBody BuildingEntity building) {
        try {
            if (!buildingRepo.existsById(id)) {
                return ResponseEntity.badRequest().body("Building not found");
            }
            building.setId(id); // Ensure ID matches
            
            // REMOVED: Update timestamp
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // building.setLastInsertedTime(LocalDateTime.now().format(formatter));
         // --- ADDED: Update Date Time on Update ---
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            building.setDateTime(LocalDateTime.now().format(formatter));
         // 1. Save the updated Building first
            BuildingEntity savedBuilding = buildingRepo.save(building);

            // --- ADDITION START: Cascade Update to Related Tables ---
            // If the Building Name changes, we must update it in Residents, Gateways, and Meters
            if (savedBuilding.getBuildingId() != null && savedBuilding.getName() != null) {
                String bId = savedBuilding.getBuildingId();
                String newName = savedBuilding.getName();

                residentRepo.updateBuildingName(bId, newName);
                gatewayRepo.updateBuildingName(bId, newName);
                meterRepo.updateBuildingName(bId, newName);
            }
            // --- ADDITION END ---
            
            return ResponseEntity.ok(buildingRepo.save(building));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating building: " + e.getMessage());
        }
    }

   /* @DeleteMapping("/delete-building/{id}")
    public ResponseEntity<?> deleteBuilding(@PathVariable Long id) {
        try {
            buildingRepo.deleteById(id);
            return ResponseEntity.ok("Building deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting building: " + e.getMessage());
        }
    }*/
    
	
    @Transactional
    @DeleteMapping("/delete-building/{id}")
    public ResponseEntity<?> deleteBuilding(@PathVariable Long id) {
        try {
            // 1. Find the building
            Optional<BuildingEntity> buildingOpt = buildingRepo.findById(id);
            if (buildingOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Building not found");
            }
            String customBuildingId = buildingOpt.get().getBuildingId();

            // 2. CRITICAL FIX: Handle Orphan Meters
            // Find all gateways in this building first
            List<GatewayEntity> gateways = gatewayRepo.findByBuildingId(customBuildingId);

            // Loop through each gateway and delete its specific meters.
            // This catches meters even if they have the wrong Building ID saved.
            for (GatewayEntity gw : gateways) {
                meterRepo.deleteByGatewayId(gw.getGatewayId());
            }

            // 3. Now safe to delete Gateways
            gatewayRepo.deleteAll(gateways); 

            // 4. Delete Residents
            residentRepo.deleteByBuildingId(customBuildingId);

            // 5. Delete Building
            buildingRepo.deleteById(id);

            return ResponseEntity.ok("Building and all dependencies deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error deleting building: " + e.getMessage());
        }
    }
	
	
	// NEW: Get all buildings to populate dropdowns
    @GetMapping("/get-buildings")
    public ResponseEntity<?> getAllBuildings() {
        return ResponseEntity.ok(buildingRepo.findAll());
    }

 // ... existing imports ...

  /*  @PostMapping("/import-buildings")
    public ResponseEntity<?> importBuildings(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                service.saveBuildings(file); // Make sure your service has this method
                return ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + file.getOriginalFilename() + "!");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an excel file!");
    }*/

 // UNCOMMENT AND UPDATE THIS METHOD
 // --- UPDATED IMPORT METHOD ---
    @PostMapping("/import-buildings")
    public ResponseEntity<?> importBuildings(@RequestParam("file") MultipartFile file) {
        // ALLOW BOTH EXCEL AND CSV
        if (ExcelHelper.hasExcelFormat(file) || ExcelHelper.hasCSVFormat(file)) {
            try {
                excelService.saveBuildings(file); 
                return ResponseEntity.status(HttpStatus.OK).body("Uploaded buildings successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace(); // Helps see error in console
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel or CSV file!");
    }
    
    @PostMapping("/import-residents")
    public ResponseEntity<?> importResidents(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveResidents(file); 
                return ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + file.getOriginalFilename() + "!");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an excel file!");
    }

    @PostMapping("/import-gateways")
    public ResponseEntity<?> importGateways(@RequestParam("file") MultipartFile file) {
        // Accept BOTH Excel and CSV to avoid 400 Bad Request
        if (ExcelHelper.hasExcelFormat(file) || ExcelHelper.hasCSVFormat(file)) {
            try {
                excelService.saveGateways(file); 
                return ResponseEntity.status(HttpStatus.OK).body("Uploaded gateways successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error: " + e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel (.xlsx) or CSV file!");
    }

    @PostMapping("/import-meters")
    public ResponseEntity<?> importMeters(@RequestParam("file") MultipartFile file) {
        // ALLOW BOTH EXCEL & CSV
        if (ExcelHelper.hasExcelFormat(file) || ExcelHelper.hasCSVFormat(file)) {
            try {
                excelService.saveMeters(file); 
                return ResponseEntity.status(HttpStatus.OK).body("Uploaded meters successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload: " + e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel or CSV file!");
    }
    
 // --- UPDATED: Add Resident with TWO Files ---
    @PostMapping(value = "/add-resident", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addResident(
            @RequestPart("resident") ResidentEntity resident,
            @RequestPart(value = "aadhaarFile", required = false) MultipartFile aadhaarFile,
            @RequestPart(value = "houseFile", required = false) MultipartFile houseFile
    ) {
        try {
            // REMOVED: Set Date & Time
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // String currentTime = LocalDateTime.now().format(formatter);
            // resident.setLastInsertedTime(currentTime);
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            resident.setDateTime(LocalDateTime.now().format(formatter));
            String folderPath = "D:\\LoraDua Documents\\";
            File directory = new File(folderPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

         // 2. Save Aadhaar Proof (Timestamps removed)
            if (aadhaarFile != null && !aadhaarFile.isEmpty()) {
                String originalFilename = aadhaarFile.getOriginalFilename();
                // Removed System.currentTimeMillis()
                String fullPath = folderPath + originalFilename;
                aadhaarFile.transferTo(new File(fullPath));
                resident.setAadhaarProof(fullPath); 
            }

            // 3. Save House Documents (Timestamps removed)
            if (houseFile != null && !houseFile.isEmpty()) {
                String originalFilename = houseFile.getOriginalFilename();
                // Removed System.currentTimeMillis()
                String fullPath = folderPath  + originalFilename;
                houseFile.transferTo(new File(fullPath));
                resident.setHouseDocuments(fullPath);
            }

            // 4. Save Resident to DB
            ResidentEntity savedResident = residentRepo.save(resident);

            // 5. Generate Custom ID
            String customId = "Res" + savedResident.getId();
            savedResident.setResidentId(customId);

            ResidentEntity finalResident = residentRepo.save(savedResident);

            return ResponseEntity.ok(finalResident);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error saving resident: " + e.getMessage());
        }
    }
    
    
    @PutMapping(value = "/update-resident/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateResident(
            @PathVariable Long id,
            @RequestPart("resident") ResidentEntity resident,
            @RequestPart(value = "aadhaarFile", required = false) MultipartFile aadhaarFile,
            @RequestPart(value = "houseFile", required = false) MultipartFile houseFile
    ) {
        resident.setId(id);
        return saveOrUpdateResident(resident, aadhaarFile, houseFile, true);
    }

    private ResponseEntity<?> saveOrUpdateResident(ResidentEntity resident, MultipartFile aadhaarFile, MultipartFile houseFile, boolean isUpdate) {
        try {
            // REMOVED: Set Last Inserted Time
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // resident.setLastInsertedTime(LocalDateTime.now().format(formatter));
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            resident.setDateTime(LocalDateTime.now().format(formatter));
            String folderPath = "D:\\LoraDua Documents\\";
            File directory = new File(folderPath);
            if (!directory.exists()) directory.mkdirs();

            // Handle Aadhaar File
            if (aadhaarFile != null && !aadhaarFile.isEmpty()) {
                String fullPath = folderPath + aadhaarFile.getOriginalFilename();
                aadhaarFile.transferTo(new File(fullPath));
                resident.setAadhaarProof(fullPath); 
            }
            
            // Handle House File
            if (houseFile != null && !houseFile.isEmpty()) {
                String fullPath = folderPath + houseFile.getOriginalFilename();
                houseFile.transferTo(new File(fullPath));
                resident.setHouseDocuments(fullPath);
            }

            // For updates: If no new file uploaded, the incoming 'resident' JSON usually contains the old path string,
            // so we don't need to manually fetch and set unless the JSON came empty for those fields.
            // (Assuming frontend sends full object)

            ResidentEntity saved = residentRepo.save(resident);

            // Generate ID only if new
            if (!isUpdate && (saved.getResidentId() == null || saved.getResidentId().isEmpty())) {
                String customId = "Res" + saved.getId();
                saved.setResidentId(customId);
                saved = residentRepo.save(saved);
            }
            
         // --- ADDITION START: Cascade Update to Meters ---
            else if (isUpdate) {
                // Sync Name, Type, Floor, and Flat to the Meters table
                if (saved.getResidentId() != null) {
                    meterRepo.updateResidentDetails(
                        saved.getResidentId(), 
                        saved.getFullName(), 
                        saved.getType(),      // Maps to MeterEntity.residentType
                        saved.getFloorNo(),   // Maps to MeterEntity.floorNo
                        saved.getFlatNo()     // Maps to MeterEntity.flatNo
                    );
                }
            }
            // --- ADDITION END ---
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error saving/updating resident: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-resident/{id}")
    public ResponseEntity<?> deleteResident(@PathVariable Long id) {
        try {
            residentRepo.deleteById(id);
            return ResponseEntity.ok("Resident deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting resident: " + e.getMessage());
        }
    }
    
 // NEW: Get All Residents
    @GetMapping("/get-residents")
    public ResponseEntity<?> getAllResidents() {
        return ResponseEntity.ok(residentRepo.findAll());
    }
    
    
 // --- NEW ENDPOINT: VIEW/DOWNLOAD RESIDENT DOCUMENTS ---
    // Changed Content-Disposition to "inline" to allow browser viewing in Modal/Iframe
    @GetMapping("/download-doc/{id}/{type}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long id, @PathVariable String type) throws IOException {
        Optional<ResidentEntity> residentOpt = residentRepo.findById(id);
        if (residentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ResidentEntity resident = residentOpt.get();
        String filePath = null;

        if ("aadhaar".equalsIgnoreCase(type)) {
            filePath = resident.getAadhaarProof();
        } else if ("house".equalsIgnoreCase(type)) {
            filePath = resident.getHouseDocuments();
        }

        if (filePath == null || filePath.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        File file = new File(filePath);
        if (!file.exists()) {
             return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        
        // Try to determine content type
        String contentType = Files.probeContentType(path);
        if(contentType == null) {
        	contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                // CHANGED TO "inline" so it opens in the modal iframe instead of auto-downloading
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    
 // --- NEW: Gateway Endpoints ---
   /* @PostMapping("/add-gateway")
    public ResponseEntity<?> addGateway(@RequestBody GatewayEntity gateway) {
        try {
            // REMOVED: Set Time
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // String currentTime = LocalDateTime.now().format(formatter);
            // gateway.setLastInsertedTime(currentTime);

            // 2. Save Gateway
            GatewayEntity savedGateway = gatewayRepo.save(gateway);
            return ResponseEntity.ok(savedGateway);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving gateway: " + e.getMessage());
        }
    }*/
    
 // --- UPDATED: Add Gateway (Upsert Logic) ---
    @PostMapping("/add-gateway")
    public ResponseEntity<?> addGateway(@RequestBody GatewayEntity gateway) {
        try {
        	
        	// --- ADDED: Set Date Time Format ---
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            gateway.setDateTime(LocalDateTime.now().format(formatter));
            // -----------------------------------
        	
            // 1. Check if a Gateway with this specific ID already exists
            Optional<GatewayEntity> existingOpt = gatewayRepo.findByGatewayId(gateway.getGatewayId());
            
            if (existingOpt.isPresent()) {
                // CASE: Exists -> Update it
                // We take the Primary Key (sl_no/id) from the existing record
                // and set it on the incoming 'gateway' object.
                // This tells Hibernate: "Don't insert new, UPDATE this ID."
                GatewayEntity existing = existingOpt.get();
                gateway.setId(existing.getId()); 
                
                // Note: This replaces the null columns in DB with the data from your form.
            } 
            
            // 2. Save (Updates if ID exists, Inserts if ID is null)
            GatewayEntity savedGateway = gatewayRepo.save(gateway);
            return ResponseEntity.ok(savedGateway);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving gateway: " + e.getMessage());
        }
    }
    
    @PutMapping("/update-gateway/{id}")
    public ResponseEntity<?> updateGateway(@PathVariable Long id, @RequestBody GatewayEntity gateway) {
        try {
            if (!gatewayRepo.existsById(id)) {
                return ResponseEntity.badRequest().body("Gateway not found");
            }
            gateway.setId(id);
            
            // REMOVED: Set Last Inserted Time
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // gateway.setLastInsertedTime(LocalDateTime.now().format(formatter));
         // --- ADDED: Set Date Time Format ---
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            gateway.setDateTime(LocalDateTime.now().format(formatter));
            // -----------------------------------
            return ResponseEntity.ok(gatewayRepo.save(gateway));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating gateway: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-gateway/{id}")
    public ResponseEntity<?> deleteGateway(@PathVariable Long id) {
        try {
            gatewayRepo.deleteById(id);
            return ResponseEntity.ok("Gateway deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting gateway: " + e.getMessage());
        }
    }
    
  /*  @GetMapping("/get-gateways")
    public ResponseEntity<?> getAllGateways() {
        return ResponseEntity.ok(gatewayRepo.findAll());
    }*/
    
    @GetMapping("/get-gateways")
    public ResponseEntity<?> getAllGateways() {
        try {
            // Fetch all gateways
            List<GatewayEntity> allGateways = gatewayRepo.findAll();

            // FILTER: Keep only gateways where buildingId is NOT null and NOT empty
            List<GatewayEntity> filteredGateways = allGateways.stream()
                .filter(g -> g.getBuildingId() != null && !g.getBuildingId().trim().isEmpty())
                .collect(Collectors.toList());

            return ResponseEntity.ok(filteredGateways);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching gateways: " + e.getMessage());
        }
    }
    
 // --- GET ALL GATEWAYS (UNFILTERED) ---
    @GetMapping("/get-all-gateways")
    public ResponseEntity<?> getAllGatewaysForDropdown() {
        try {
            // Fetches every gateway in the DB regardless of buildingId status
            return ResponseEntity.ok(gatewayRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // --- GET ALL METERS (UNFILTERED) ---
    @GetMapping("/get-all-meters")
    public ResponseEntity<?> getAllMetersForDropdown() {
        try {
            // Fetches every meter in the DB regardless of buildingId status
            return ResponseEntity.ok(meterRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
 // --- NEW: Meter Endpoints ---
  /*  @PostMapping("/add-meter")
    public ResponseEntity<?> addMeter(@RequestBody MeterEntity meter) {
        try {
            // REMOVED: Set Last Inserted Time (Backend side)
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // String currentTime = LocalDateTime.now().format(formatter);
            // meter.setLastInsertedTime(currentTime);

            // 2. Save Meter
            MeterEntity savedMeter = meterRepo.save(meter);
            return ResponseEntity.ok(savedMeter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving meter: " + e.getMessage());
        }
    }*/
    
 // --- UPDATED: Add Meter (Upsert Logic) ---
    @PostMapping("/add-meter")
    public ResponseEntity<?> addMeter(@RequestBody MeterEntity meter) {
        try {
        	
            // 1. Check if a Meter with this Serial No already exists
            // (Assumes getSerialNo() maps to 'meter_sl_no' in your Entity)
            Optional<MeterEntity> existingOpt = meterRepo.findBySerialNo(meter.getSerialNo());
            
            if (existingOpt.isPresent()) {
                // CASE: Exists -> Update it
                // We take the Primary Key (sl_no/id) from the existing record
                // and set it on the incoming 'meter' object.
                MeterEntity existing = existingOpt.get();
                meter.setId(existing.getId()); 
                
             // IMPORTANT: If the frontend didn't send a residentId (e.g. during a technical update), 
                // we should preserve the existing one so it doesn't become null.
                if (meter.getResidentId() == null && existing.getResidentId() != null) {
                    meter.setResidentId(existing.getResidentId());
                }
                
                // Same logic for Resident Name if needed
                if (meter.getResidentName() == null && existing.getResidentName() != null) {
                    meter.setResidentName(existing.getResidentName());
                }
                
                // This replaces the null columns in DB with the data from your form
            } 
            
            // 2. Save (Updates if ID exists, Inserts if ID is null)
            MeterEntity savedMeter = meterRepo.save(meter);
            return ResponseEntity.ok(savedMeter);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving meter: " + e.getMessage());
        }
    }
    
    @PutMapping("/update-meter/{id}")
    public ResponseEntity<?> updateMeter(@PathVariable Long id, @RequestBody MeterEntity meter) {
        try {
            if (!meterRepo.existsById(id)) {
                return ResponseEntity.badRequest().body("Meter not found");
            }
            meter.setId(id);
            
            // REMOVED: Set Last Inserted Time
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // meter.setLastInsertedTime(LocalDateTime.now().format(formatter));
            
            return ResponseEntity.ok(meterRepo.save(meter));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating meter: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-meter/{id}")
    public ResponseEntity<?> deleteMeter(@PathVariable Long id) {
        try {
            meterRepo.deleteById(id);
            return ResponseEntity.ok("Meter deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting meter: " + e.getMessage());
        }
    }
 // NEW: Get All Meters
 // --- GET METERS (FILTERED) ---
    @GetMapping("/get-meters")
    public ResponseEntity<?> getAllMeters() {
        try {
            List<MeterEntity> allMeters = meterRepo.findAll();

            // FILTER: Only show meters with Valid Building ID
            List<MeterEntity> filteredMeters = allMeters.stream()
                .filter(m -> m.getBuildingId() != null && !m.getBuildingId().trim().isEmpty())
                .collect(Collectors.toList());

            return ResponseEntity.ok(filteredMeters);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching meters: " + e.getMessage());
        }
    }
    
 
    
}