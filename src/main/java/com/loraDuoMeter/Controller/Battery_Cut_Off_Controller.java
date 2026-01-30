package com.loraDuoMeter.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.DTO.Battery_Cut_Off_DTO;
import com.loraDuoMeter.Repo.IBattery_Cut_Off_Repo;
import com.loraDuoMeter.Service.Battery_Cut_Off_Service;

@RestController
@RequestMapping("/api")
public class Battery_Cut_Off_Controller {
	
	 @Autowired
	    private Battery_Cut_Off_Service service;	
	 
	 @Autowired
	 private IBattery_Cut_Off_Repo repo;

	    
	 @GetMapping("/battery_cutoff")
	 public List<Battery_Cut_Off_DTO> fetchLatestByDevEui() {
	     return service.getLatestByDevEui();
	 }
	 
	 @GetMapping("/battery_cutoff_devEui/{devEui}")
	 public Map<String, Object> fetchByDevEui(
	         @PathVariable String devEui,
	         @RequestParam(defaultValue = "0") int page,
	         @RequestParam(defaultValue = "2000") int size,
	         @RequestParam(required = false) String search
	 ) {
	     return service.getByDevEui(page, size, devEui, search);
	 }


}
