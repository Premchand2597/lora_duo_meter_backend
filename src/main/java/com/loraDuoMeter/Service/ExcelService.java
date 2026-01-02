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
    public void saveResidents(MultipartFile file) {
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
    }

    // --- 3. Import Gateways ---
    public void saveGateways(MultipartFile file) {
        try {
            List<GatewayEntity> gateways = ExcelHelper.excelToGateways(file.getInputStream());
            gatewayRepo.saveAll(gateways);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    // --- 4. Import Meters ---
    public void saveMeters(MultipartFile file) {
        try {
            List<MeterEntity> meters = ExcelHelper.excelToMeters(file.getInputStream());
            meterRepo.saveAll(meters);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}