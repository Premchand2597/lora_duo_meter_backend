package com.loraDuoMeter.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loraDuoMeter.Service.ExcelHelper; // Make sure package name matches
import com.loraDuoMeter.Entity.BuildingEntity;
import com.loraDuoMeter.Entity.GatewayEntity;
import com.loraDuoMeter.Entity.MeterEntity;
import com.loraDuoMeter.Entity.ResidentEntity;
import com.loraDuoMeter.Repo.BuildingRepo;
import com.loraDuoMeter.Repo.GatewayRepo;
import com.loraDuoMeter.Repo.MeterRepo;
import com.loraDuoMeter.Repo.ResidentRepo;

@Service
public class ExcelService {

    @Autowired
    private BuildingRepo buildingRepo;
    @Autowired
    private ResidentRepo residentRepo;
    @Autowired
    private GatewayRepo gatewayRepo;
    @Autowired
    private MeterRepo meterRepo;

    // --- 1. Import Buildings (UPDATED FOR CSV) ---
    public void saveBuildings(MultipartFile file) {
        try {
            List<BuildingEntity> buildings;
            
            // Check extension/content-type to decide which parser to use
            if (ExcelHelper.hasCSVFormat(file)) {
                buildings = ExcelHelper.csvToBuildings(file.getInputStream());
            } else {
                buildings = ExcelHelper.excelToBuildings(file.getInputStream());
            }

            for (BuildingEntity building : buildings) {
                // Save first to generate ID (sl_no)
                BuildingEntity saved = buildingRepo.save(building);
                // Update with Custom ID
                saved.setBuildingId("Buil" + saved.getId());
                buildingRepo.save(saved);
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store data: " + e.getMessage());
        }
    }

    // --- 2. Import Residents ---
    /*public void saveResidents(MultipartFile file) {
        try {
            List<ResidentEntity> residents = ExcelHelper.excelToResidents(file.getInputStream());
            for (ResidentEntity resident : residents) {
                ResidentEntity saved = residentRepo.save(resident);
                saved.setResidentId("Res" + saved.getId());
                residentRepo.save(saved);
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }*/
    
 // --- 2. Import Residents (UPDATED) ---
 // Inside ExcelService.java

    public void saveResidents(MultipartFile file) {
        try {
            List<ResidentEntity> residents = ExcelHelper.excelToResidents(file.getInputStream());
            
            for (ResidentEntity resident : residents) {
                
                // --- 1. FIND BUILDING ID (STRICT MODE) ---
                String bName = resident.getBuildingName();
                String bAddress = resident.getAddress();

                if (bName != null && bAddress != null && !bName.isEmpty()) {
                    // Try to find the building
                    BuildingEntity foundBuilding = buildingRepo.findByNameAndAddress(bName, bAddress);
                    
                    if (foundBuilding != null) {
                        // Found it! Assign the ID.
                        resident.setBuildingId(foundBuilding.getBuildingId());
                    } else {
                        // --- ERROR THROWN HERE ---
                        // Building not found? STOP THE UPLOAD immediately.
                        throw new RuntimeException("UPLOAD FAILED: Building not found for Name: '" + bName + "' and Address: '" + bAddress + "'. Please check the spelling or create the building first.");
                    }
                } else {
                    // Missing Name/Address? STOP THE UPLOAD.
                    throw new RuntimeException("UPLOAD FAILED: Resident '" + resident.getFullName() + "' is missing a Building Name or Address.");
                }

                // --- 2. SAVE & GENERATE RESIDENT ID ---
                // Save first to get the DB generated ID (sl_no)
                ResidentEntity saved = residentRepo.save(resident);
                
                // Generate Custom ID ("Res" + 50 -> "Res50")
                saved.setResidentId("Res" + saved.getId());
                
                // Update the record
                residentRepo.save(saved);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail to store excel data: " + e.getMessage());
        }
    }
    // --- 3. Import Gateways ---
   /* public void saveGateways(MultipartFile file) {
        try {
            List<GatewayEntity> gateways = ExcelHelper.excelToGateways(file.getInputStream());
            gatewayRepo.saveAll(gateways);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }*/
 // --- 3. IMPORT GATEWAYS (CORRECTED FOR EXCEL) ---
    public void saveGateways(MultipartFile file) {
        try {
            List<GatewayEntity> gateways;

            // 1. Detect Format
            if (ExcelHelper.hasCSVFormat(file)) {
                gateways = ExcelHelper.csvToGateways(file.getInputStream());
            } else {
                gateways = ExcelHelper.excelToGateways(file.getInputStream());
            }

            // 2. Resolve Building ID Logic
            for (GatewayEntity gateway : gateways) {
                
                String bName = gateway.getBuildingName();
                // CRITICAL: We stored the 'Address' inside 'buildingId' in ExcelHelper
                String bAddress = gateway.getBuildingId(); 

                if (bName != null && !bName.isEmpty()) {
                    
                    // A. If address looks like "Buil123", it might already be an ID (legacy file support)
                    if (bAddress != null && bAddress.startsWith("Buil")) {
                        // Do nothing, ID is already correct
                    } 
                    // B. Otherwise, treat it as an Address and Lookup
                    else if (bAddress != null && !bAddress.isEmpty()) {
                        BuildingEntity foundBuilding = buildingRepo.findByNameAndAddress(bName.trim(), bAddress.trim());
                        
                        if (foundBuilding != null) {
                             gateway.setBuildingId(foundBuilding.getBuildingId());
                        } else {
                             throw new RuntimeException("UPLOAD FAILED: Building not found for Name: '" + bName + "' and Address: '" + bAddress + "'.");
                        }
                    } else {
                        throw new RuntimeException("UPLOAD FAILED: Missing Address for Gateway: " + gateway.getGatewayId());
                    }
                }
            }

            // 3. Save All
            gatewayRepo.saveAll(gateways);

        } catch (IOException e) {
            throw new RuntimeException("Fail to store gateway data: " + e.getMessage());
        }
    }
    // --- 4. Import Meters ---
  /*  public void saveMeters(MultipartFile file) {
        try {
            List<MeterEntity> meters = ExcelHelper.excelToMeters(file.getInputStream());
            meterRepo.saveAll(meters);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }*/
    
 // --- 4. Import Meters (UPDATED) ---
    public void saveMeters(MultipartFile file) {
        try {
            List<MeterEntity> meters;

            // 1. Detect CSV or Excel
            if (ExcelHelper.hasCSVFormat(file)) {
                meters = ExcelHelper.csvToMeters(file.getInputStream());
            } else {
                meters = ExcelHelper.excelToMeters(file.getInputStream());
            }

            // 2. Resolve Building ID (Lookup by Name + Address)
            for (MeterEntity meter : meters) {
                String bName = meter.getBuildingName();
                String bAddress = meter.getBuildingId(); // We stored Address here temporarily

                if (bName != null && !bName.isEmpty()) {
                    
                    // A. Check if it's already an ID (starts with "Buil")
                    if (bAddress != null && bAddress.startsWith("Buil")) {
                         // ID is already valid, do nothing
                    } 
                    // B. Lookup by Name & Address
                    else if (bAddress != null && !bAddress.isEmpty()) {
                        BuildingEntity found = buildingRepo.findByNameAndAddress(bName.trim(), bAddress.trim());
                        
                        if (found != null) {
                            meter.setBuildingId(found.getBuildingId()); // Set REAL ID
                        } else {
                            throw new RuntimeException("UPLOAD FAILED: Building not found for Meter: " + meter.getSerialNo() + 
                                                       ". Name: '" + bName + "', Address: '" + bAddress + "'.");
                        }
                    } else {
                        throw new RuntimeException("UPLOAD FAILED: Missing Address for Meter: " + meter.getSerialNo());
                    }
                }
            }

            // 3. Save All
            meterRepo.saveAll(meters);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    
    
}