package com.loraDuoMeter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.DTO.BatteryCutOff_Dto;
import com.loraDuoMeter.DTO.MeterDetailsBuildingName_Dto;
import com.loraDuoMeter.DTO.MeterDetailsMeterSlNo_Dto;
import com.loraDuoMeter.DTO.MeterDetailsOnly_Dto;
import com.loraDuoMeter.DTO.MeterDetails_Dto;
import com.loraDuoMeter.Service.Main_Service;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private Main_Service service;

	@GetMapping("/data")
	public String start() {
		return "Admin data";
	}
	
	@GetMapping("/batteryPrediction")
	public ResponseEntity<?> getAllBatteryCutOffData(){
		try {
			List<BatteryCutOff_Dto> datas = service.fetchAllBatteryDetails();
			return new ResponseEntity<List<BatteryCutOff_Dto>>(datas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/meterDetails")
	public ResponseEntity<?> getAllMeterDetailsWithBatteryCutOffLists(){
		try {
			List<MeterDetails_Dto> datas = service.fetchAllMeterDetailsData();
//			System.out.println("datas = "+datas);
			return new ResponseEntity<List<MeterDetails_Dto>>(datas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/meterDetails/{id}")
	public ResponseEntity<?> getMeterDetailsWithBatterCuttOffBasedOnMeterId(@PathVariable long id){
		try {
			MeterDetails_Dto data = service.getMeterDataBasedOnId(id);
			return new ResponseEntity<MeterDetails_Dto>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/buldingNames")
	public ResponseEntity<?> fetchDistinctBuildingNamesLists(){
		try {
			List<MeterDetailsBuildingName_Dto> dtoLists = service.getDistinctBuildingNames();
			return new ResponseEntity<List<MeterDetailsBuildingName_Dto>>(dtoLists, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/meterSlNo/{buildingName}")
	public ResponseEntity<?> fetchMeterSlNoBasedOnBuildingName(@PathVariable String buildingName){
		try {
			List<MeterDetailsMeterSlNo_Dto> dtoLists = service.getMeterSlNoBasedOnBuildingName(buildingName);
			return new ResponseEntity<List<MeterDetailsMeterSlNo_Dto>>(dtoLists, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/meterDetailsBy/{meterSlNo}")
	public ResponseEntity<?> fetchMeterDetailsByMeterSlNo(@PathVariable String meterSlNo){
		try {
			MeterDetailsOnly_Dto fetchedData = service.getMeterDetailsByMeterSlNo(meterSlNo);
			return new ResponseEntity<MeterDetailsOnly_Dto>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
