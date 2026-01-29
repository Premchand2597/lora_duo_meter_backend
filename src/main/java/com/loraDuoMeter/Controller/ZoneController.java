// src/main/java/com/loraDuoMeter/Controller/ZoneController.java
package com.loraDuoMeter.Controller;

import com.loraDuoMeter.Entity.ZoneEntity;
import com.loraDuoMeter.Repo.ZoneRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/zones")
@CrossOrigin(origins = "http://localhost:3000") // Adjust for your frontend port
public class ZoneController {
    
    private final ZoneRepo zoneRepo;

    public ZoneController(ZoneRepo zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

    @PostMapping("/add")
    public ZoneEntity addZone(@RequestBody ZoneEntity zone) {
        return zoneRepo.save(zone);
    }

    @GetMapping("/get-all")
    public List<ZoneEntity> getAllZones() {
        return zoneRepo.findAll();
    }
}