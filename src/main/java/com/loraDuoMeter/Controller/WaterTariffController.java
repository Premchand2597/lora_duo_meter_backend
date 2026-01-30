package com.loraDuoMeter.Controller;

import com.loraDuoMeter.Entity.WaterTariffEntity;
import com.loraDuoMeter.Repo.WaterTariffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/water-tariff")
public class WaterTariffController {

    @Autowired
    private WaterTariffRepo waterTariffRepo;

    // Updated mapping to match frontend call
    @PostMapping("/add-tariff")
    public ResponseEntity<?> addWaterTariff(@RequestBody WaterTariffEntity tariff) {
        try {
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
        
        // Water specific fields
        tariff.setMeterCharges(tariffDetails.getMeterCharges());
        tariff.setSanitaryCharges(tariffDetails.getSanitaryCharges());
        tariff.setBorewellCharges(tariffDetails.getBorewellCharges());
        tariff.setOtherCharges(tariffDetails.getOtherCharges());

        return ResponseEntity.ok(waterTariffRepo.save(tariff));
    }
}