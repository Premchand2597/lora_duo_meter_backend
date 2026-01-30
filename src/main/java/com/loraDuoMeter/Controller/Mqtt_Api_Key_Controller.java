package com.loraDuoMeter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.Proj.Mqtt_Api_Key_Proj;
import com.loraDuoMeter.Service.Mqtt_Api_key_Service;

@RestController
@RequestMapping("/api")
public class Mqtt_Api_Key_Controller {
	
	@Autowired
	private Mqtt_Api_key_Service mqtt_api_service;
	
	@GetMapping("/api-keys")
	public Slice<Mqtt_Api_Key_Proj> getApiKeys(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "20") int size) {
	    return mqtt_api_service.get_apis(page, size);
	}


}
