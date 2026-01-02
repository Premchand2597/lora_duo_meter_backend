package com.loraDuoMeter.Controller;

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

import com.loraDuoMeter.Entity.TariffEntity;
import com.loraDuoMeter.Repo.TariffRepo;

@RestController
@RequestMapping("/api/tariff")
public class TariffController {

	@Autowired
    private TariffRepo tariffRepo;

   /* @PostMapping("/add-tariff")
    public ResponseEntity<?> addTariff(@RequestBody TariffEntity tariff) {
        try {
            TariffEntity saved = tariffRepo.save(tariff);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving tariff: " + e.getMessage());
        }
    }*/

    
	@PostMapping("/add-tariff")
	public ResponseEntity<?> addTariff(@RequestBody TariffEntity tariff) {

	    // 🔹 Printing received tariff data
	    System.out.println("===== Received Tariff Data =====");
	    System.out.println("ID                    : " + tariff.getId());
	    System.out.println("Location              : " + tariff.getLocation());
	    System.out.println("Building IDs          : " + tariff.getBuildingIds());
	    System.out.println("Unit Price            : " + tariff.getUnitPrice());
	    System.out.println("Security Deposit      : " + tariff.getSecurityDeposit());
	    System.out.println("Connection Fee        : " + tariff.getConnectionFee());
	    System.out.println("Processing Fee        : " + tariff.getProcessingFee());
	    System.out.println("Disconnect Fee        : " + tariff.getDisconnectFee());
	    System.out.println("Late Charge           : " + tariff.getLateCharge());
	    System.out.println("Initial Recharge Fee  : " + tariff.getInitialRechargeFee());
	    System.out.println("Replacement Fee       : " + tariff.getReplacementFee());

	    try {
	        // 🔹 Save tariff to database
	        TariffEntity saved = tariffRepo.save(tariff);

	        // 🔹 Printing saved tariff data
	        System.out.println("===== Tariff Saved Successfully =====");
	        System.out.println("Generated ID          : " + saved.getId());
	        System.out.println("Location              : " + saved.getLocation());
	        System.out.println("Building IDs          : " + saved.getBuildingIds());
	        System.out.println("Unit Price            : " + saved.getUnitPrice());

	        return ResponseEntity.ok(saved);

	    } catch (Exception e) {

	        // 🔹 Print error if save fails
	        System.out.println("❌ Error while saving tariff");
	        System.out.println("Reason: " + e.getMessage());

	        return ResponseEntity
	                .badRequest()
	                .body("Error saving tariff: " + e.getMessage());
	    }
	}


    @GetMapping("/get-tariffs")
    public ResponseEntity<List<TariffEntity>> getAllTariffs() {
        return ResponseEntity.ok(tariffRepo.findAll());
    }

    @PutMapping("/update-tariff/{id}")
    public ResponseEntity<?> updateTariff(@PathVariable Long id, @RequestBody TariffEntity tariffDetails) {
        Optional<TariffEntity> optionalTariff = tariffRepo.findById(id);
        if (optionalTariff.isEmpty()) {
            return ResponseEntity.badRequest().body("Tariff not found");
        }

        TariffEntity tariff = optionalTariff.get();
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

        return ResponseEntity.ok(tariffRepo.save(tariff));
    }

    @DeleteMapping("/delete-tariff/{id}")
    public ResponseEntity<?> deleteTariff(@PathVariable Long id) {
        try {
            tariffRepo.deleteById(id);
            return ResponseEntity.ok("Tariff deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting tariff: " + e.getMessage());
        }
    }
	
}
