package com.loraDuoMeter.Controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.loraDuoMeter.DTO.DailyUpdateDisplay_DTO;
import com.loraDuoMeter.Repo.IDaily_Update_Display_Repo;
import com.loraDuoMeter.Service.Daily_Update_Service;

@RestController
@RequestMapping("/api")
public class Daily_Update_Controller {
	
	 @Autowired
	    private Daily_Update_Service service;	
	 
	 @Autowired
	 private IDaily_Update_Display_Repo repo;
	 

	    
	 @GetMapping("/meter_daily_update")
	 public List<DailyUpdateDisplay_DTO> fetchLatestByDevEui() {
	     return service.getLatestByDevEui();
	 }

	 
//	 @GetMapping("/meter_data_devEui/{devEui}")
//	 public Map<String,Object> fetchByDevEui(
//	         @PathVariable String devEui,
//	         @RequestParam(defaultValue = "0") int page,
//	         @RequestParam(defaultValue = "2000") int size
//	 ) {
//		 try {
//	     Slice<DailyUpdateDisplay_DTO> slice = service.getByDevEui(page, size, devEui);
//	     Map<String,Object> map = new HashMap<>();
//	     map.put("content", slice.getContent());
//	     map.put("hasNext", slice.hasNext());
//	     map.put("totalElements", repo.countByDevEui(devEui)); // manually count
//
//	     return map;
//		 } catch (Exception e) {
//		        e.printStackTrace();
//		        return Map.of("content", Collections.emptyList(), "hasNext", false, "totalElements", 0);
//		    }
//	 }
	 
	 @GetMapping("/meter_data_devEui/{devEui}")
	 public Map<String, Object> fetchByDevEui(
	         @PathVariable String devEui,
	         @RequestParam(defaultValue = "0") int page,
	         @RequestParam(defaultValue = "2000") int size,
	         @RequestParam(required = false) String search
	 ) {
	     return service.getByDevEui(page, size, devEui, search);
	 }






	
}
