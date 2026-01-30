package com.loraDuoMeter.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.DTO.TamperEventDisplay_DTO;
import com.loraDuoMeter.Repo.ITamper_Event_Repo;
import com.loraDuoMeter.Service.Tamper_Event_Service;

@RestController
@RequestMapping("/api")
public class Tamper_Event_Controller {
	

	 @Autowired
	    private Tamper_Event_Service service;	
	 
	 @Autowired
	 private ITamper_Event_Repo repo;

	    
	 @GetMapping("/tamper_event")
	 public List<TamperEventDisplay_DTO> fetchLatestByDevEui() {
	     return service.getLatestByDevEui();
	 }
	 
	 @GetMapping("/tamper_event_devEui/{devEui}")
	 public Map<String, Object> fetchByDevEui(
	         @PathVariable String devEui,
	         @RequestParam(defaultValue = "0") int page,
	         @RequestParam(defaultValue = "2000") int size,
	         @RequestParam(required = false) String search
	 ) {
	     return service.getByDevEui(page, size, devEui, search);
	 }






	
}
