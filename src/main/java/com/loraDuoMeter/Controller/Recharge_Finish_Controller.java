package com.loraDuoMeter.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.DTO.RechargeOverDisplay_DTO;
import com.loraDuoMeter.Repo.IRecharge_Finish_Repo;
import com.loraDuoMeter.Service.Recharge_Finish_Service;

@RestController
@RequestMapping("/api")
public class Recharge_Finish_Controller {

	 @Autowired
	    private Recharge_Finish_Service service;	
	 
	 @Autowired
	 private IRecharge_Finish_Repo repo;

	    
	 @GetMapping("/recharge_finish")
	 public List<RechargeOverDisplay_DTO> fetchLatestByDevEui() {
	     return service.getLatestByDevEui();
	 }
	 
	 @GetMapping("/recharge_finish_devEui/{devEui}")
	 public Map<String, Object> fetchByDevEui(
	         @PathVariable String devEui,
	         @RequestParam(defaultValue = "0") int page,
	         @RequestParam(defaultValue = "2000") int size,
	         @RequestParam(required = false) String search
	 ) {
	     return service.getByDevEui(page, size, devEui, search);
	 }



}
