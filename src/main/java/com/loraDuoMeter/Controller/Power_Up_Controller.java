package com.loraDuoMeter.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.loraDuoMeter.DTO.PowerUpDisplay_DTO;
import com.loraDuoMeter.Repo.IPower_Up_Display_Repo;
import com.loraDuoMeter.Service.Power_Up_Service;

@RestController
@RequestMapping("/api")
public class Power_Up_Controller {
	

	 @Autowired
	    private Power_Up_Service service;	
	 
	 @Autowired
	 private IPower_Up_Display_Repo repo;

	    
	 @GetMapping("/power_up")
	 public List<PowerUpDisplay_DTO> fetchLatestByDevEui() {
	     return service.getLatestByDevEui();
	 }
	 
	 @GetMapping("/power_up_devEui/{devEui}")
	 public Map<String, Object> fetchByDevEui(
	         @PathVariable String devEui,
	         @RequestParam(defaultValue = "0") int page,
	         @RequestParam(defaultValue = "2000") int size,
	         @RequestParam(required = false) String search
	 ) {
	     return service.getByDevEui(page, size, devEui, search);
	 }






	
}
