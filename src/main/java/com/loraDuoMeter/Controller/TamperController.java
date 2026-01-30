package com.loraDuoMeter.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.DTO.DailyUpdateDisplay_DTO;
import com.loraDuoMeter.DTO.TamperEventDto;
import com.loraDuoMeter.DTO.TamperGraphDto;
import com.loraDuoMeter.Entity.BuildingEntity;
import com.loraDuoMeter.Entity.MeterEntity;
import com.loraDuoMeter.Entity.TamperEventEntity;
import com.loraDuoMeter.Repo.BuildingRepo; // Import BuildingRepo
import com.loraDuoMeter.Repo.IDaily_Update_Display_Repo;
import com.loraDuoMeter.Repo.MeterRepo;
import com.loraDuoMeter.Repo.TamperEventRepo;
import com.loraDuoMeter.Service.Daily_Update_Service;
import com.loraDuoMeter.Service.Main_Service;

@RestController
@RequestMapping("/api/tamper")
public class TamperController {

    @Autowired
    private TamperEventRepo tamperRepo;

    @Autowired
    private MeterRepo meterRepo;

    @Autowired
    private BuildingRepo buildingRepo; // 1. Inject Building Repo

	@Autowired
	private Main_Service mainService;
    
    @GetMapping("/get-tamper-events")
    public ResponseEntity<?> getAllTamperEvents() {
        try {
            // 2. Fetch all required data
            List<TamperEventEntity> events = tamperRepo.findAllByOrderBySlNoAsc();
            List<MeterEntity> meters = meterRepo.findAll();
            List<BuildingEntity> buildings = buildingRepo.findAll();

            // 3. Create a Map for Buildings for faster lookup (Key: BuildingID)
            Map<String, BuildingEntity> buildingMap = buildings.stream()
                .filter(b -> b.getBuildingId() != null)
                .collect(Collectors.toMap(BuildingEntity::getBuildingId, b -> b, (b1, b2) -> b1));

            List<TamperEventDto> filteredEvents = new ArrayList<>();

            for (TamperEventEntity event : events) {
                String tamperDevEui = event.getDevEui();

                if (tamperDevEui != null) {
                    // 4. Find matching Meter
                    Optional<MeterEntity> matchingMeter = meters.stream()
                        .filter(m -> m.getSerialNo() != null && m.getSerialNo().equalsIgnoreCase(tamperDevEui))
                        .findFirst();

                    if (matchingMeter.isPresent()) {
                        MeterEntity meter = matchingMeter.get();
                        
                        // 5. Strict Check: Must have Building ID
                        if (meter.getBuildingId() != null && !meter.getBuildingId().trim().isEmpty()) {
                            
                            TamperEventDto dto = new TamperEventDto();
                            
                            // A. Map Tamper Info
                            dto.setSlNo(event.getSlNo());
                            dto.setDeviceType(event.getDeviceType());
                            dto.setDevEui(event.getDevEui());
							/* dto.setTamperType(event.getTamperType()); */
                            dto.setEventTime(event.getReceivedAt());
                            dto.setLastInsertedDate(event.getLastUpdateTime());
                            
                            // B. Map Meter/Resident Info
                            dto.setBuildingName(meter.getBuildingName());
                            dto.setBuildingId(meter.getBuildingId());
                            dto.setResidentName(meter.getResidentName());
                            dto.setFloorNo(meter.getFloorNo());
                            dto.setFlatNo(meter.getFlatNo());
                            dto.setResidentType(meter.getResidentType());

                            // C. Map Address Info from Building Entity
                            if (buildingMap.containsKey(meter.getBuildingId())) {
                                BuildingEntity building = buildingMap.get(meter.getBuildingId());
                                dto.setAddress(building.getAddress());
                                dto.setState(building.getState());
                                dto.setCountry(building.getCountry());
                            } else {
                                // Fallback if building ID exists in meter but not in building table
                                dto.setAddress("N/A");
                                dto.setState("N/A");
                                dto.setCountry("N/A");
                            }

                            filteredEvents.add(dto);
                        }
                    }
                }
            }
            
            if (filteredEvents.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            
            return ResponseEntity.ok(filteredEvents);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error fetching tamper data: " + e.getMessage());
        }
    }
    
    
    @GetMapping("/graph-data")
    public ResponseEntity<List<TamperGraphDto>> getTamperGraphData() {
        // You are calling the service method we are about to write in Step 4
        List<TamperGraphDto> data = mainService.getTamperGraphData(); 
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}