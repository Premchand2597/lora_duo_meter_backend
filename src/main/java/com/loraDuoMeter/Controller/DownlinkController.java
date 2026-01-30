package com.loraDuoMeter.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.Downlink_DTO.DownlinkRequest_DTO;
import com.loraDuoMeter.Downlink_DTO.MeterDownlinkDTO;
import com.loraDuoMeter.Downlink_Service.DownlinkService;

@RestController
@RequestMapping("/api")
public class DownlinkController {

	 private final DownlinkService downlinkService;
	
	  public DownlinkController(DownlinkService downlinkService) {
	        this.downlinkService = downlinkService;
	    }


	  @PostMapping("/downlink")
	  public ResponseEntity<?> downlink(@RequestBody DownlinkRequest_DTO req) {
	      try {
	          System.out.println("===== DOWNLINK REQUEST RECEIVED =====");
	          System.out.println("GatewayId : " + req.getGatewayId());
	          System.out.println("====================================");

	          for (MeterDownlinkDTO meter : req.getMeters()) {
	              System.out.println("DevEui    : " + meter.getDevEui());
	              System.out.println("Payload   : " + meter.getPayloadHex());

	              downlinkService.sendDownlink(
	                  req.getGatewayId(),
	                  meter.getDevEui().toLowerCase(),
	                  meter.getPayloadHex()
	              );
	          }

	          return ResponseEntity.accepted().body("Downlink queued");
	      } catch (Exception e) {
	          e.printStackTrace();
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                  .body("Failed to queue downlink: " + e.getMessage());
	      }
	  }


}
