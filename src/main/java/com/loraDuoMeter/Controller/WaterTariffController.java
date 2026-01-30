package com.loraDuoMeter.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.Entity.WaterTariffEntity;
import com.loraDuoMeter.Repo.WaterTariffRepo;

@RestController
@RequestMapping("/api/water-tariff")
public class WaterTariffController {

    @Autowired
    private WaterTariffRepo waterTariffRepo;

    // Updated mapping to match frontend call
    @PostMapping("/add-tariff")
    public ResponseEntity<?> addWaterTariff(@RequestBody WaterTariffEntity tariff) {
        try {
        	// Set manual timestamp string in dd-MM-yyyy HH:mm:ss format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            tariff.setLastUpdated(LocalDateTime.now().format(formatter));
            System.out.println("Saving Water Tariff for Location: " + tariff.getLocation());
            WaterTariffEntity saved = waterTariffRepo.save(tariff);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<WaterTariffEntity>> getAllWaterTariffs() {
        return ResponseEntity.ok(waterTariffRepo.findAll());
    }

    // Added update mapping to match frontend
    @PutMapping("/update-tariff/{id}")
    public ResponseEntity<?> updateWaterTariff(@PathVariable Long id, @RequestBody WaterTariffEntity tariffDetails) {
        Optional<WaterTariffEntity> optionalTariff = waterTariffRepo.findById(id);
        if (optionalTariff.isEmpty()) {
            return ResponseEntity.badRequest().body("Water Tariff not found");
        }

        WaterTariffEntity tariff = optionalTariff.get();
        tariff.setLocation(tariffDetails.getLocation());
        tariff.setBuildingIds(tariffDetails.getBuildingIds());
        tariff.setUnitPrice(tariffDetails.getUnitPrice());
        tariff.setSecurityDeposit(tariffDetails.getSecurityDeposit());
        tariff.setConnectionFee(tariffDetails.getConnectionFee());
        tariff.setProcessingFee(tariffDetails.getProcessingFee());
        tariff.setDisconnectFee(tariffDetails.getDisconnectFee());
        tariff.setLateCharge(tariffDetails.getLateCharge());
        tariff.setInitialRechargeFee(tariffDetails.getInitialRechargeFee());
        tariff.setReplacementFee(tariffDetails.getReplacementFee());
     // ... (Your existing field updates: location, buildingIds, etc.)
        tariff.setZoneId(tariffDetails.getZoneId()); // Ensure zone is updated

        // Update the timestamp string on every edit
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        tariff.setLastUpdated(LocalDateTime.now().format(formatter));
        // Water specific fields
        tariff.setMeterCharges(tariffDetails.getMeterCharges());
        tariff.setSanitaryCharges(tariffDetails.getSanitaryCharges());
        tariff.setBorewellCharges(tariffDetails.getBorewellCharges());
        tariff.setOtherCharges(tariffDetails.getOtherCharges());

        return ResponseEntity.ok(waterTariffRepo.save(tariff));
    }
    
    
    
    @DeleteMapping("/delete-tariff/{id}")
    public ResponseEntity<?> deleteWaterTariff(@PathVariable Long id) {
        try {
            waterTariffRepo.deleteById(id);
            return ResponseEntity.ok("Water Tariff deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}