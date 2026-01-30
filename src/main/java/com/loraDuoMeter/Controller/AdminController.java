package com.loraDuoMeter.Controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.DTO.BatteryCutOff_Dto;
import com.loraDuoMeter.DTO.MeterDetailsBuildingName_Dto;
import com.loraDuoMeter.DTO.MeterDetailsMeterSlNo_Dto;
import com.loraDuoMeter.DTO.MeterDetailsOnly_Dto;
import com.loraDuoMeter.DTO.MeterDetails_Dto;
import com.loraDuoMeter.DTO.MqttApiKey_Dto;
import com.loraDuoMeter.DTO.PaymentBill_Dto;
import com.loraDuoMeter.DTO.RechargeFinish_Dto;
import com.loraDuoMeter.DTO.TamperEventAndMeterDetailsDto;
import com.loraDuoMeter.DTO.TamperEventsDto;
import com.loraDuoMeter.Repo.LoginRepo;
import com.loraDuoMeter.Repo.TariffRepo;
import com.loraDuoMeter.Service.Main_Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private Main_Service service;
	
	@Autowired
	private TariffRepo tariffRepo;
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
    private JavaMailSender mailSender;
	
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
//		System.out.println("meterSlNo === "+meterSlNo);
		try {
			MeterDetailsOnly_Dto fetchedData = service.getMeterDetailsByMeterSlNo(meterSlNo);
			return new ResponseEntity<MeterDetailsOnly_Dto>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/postPaidMeterDetails")
	public ResponseEntity<List<MeterDetailsOnly_Dto>> fetchMeterDetailsForPostPaidType(){
		try {
			List<MeterDetailsOnly_Dto> fetchedData = service.fetchMeterDetailsForPostPaidType();
			return new ResponseEntity<>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/updatePostpaidBilling")
	public ResponseEntity<String> updateBillUpdatesForPostpaidConnection(@RequestParam String billingDate, @RequestParam String bufferDay){
		try {
			boolean status = service.updateBillDetailsForPostPaidData(billingDate, bufferDay);
			if(status) {
				return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem in updating billing details");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/fetchAllTamperData")
	public ResponseEntity<?> getAllTamperEventsDetail(){
		try {
			List<TamperEventsDto> fetchedData = service.fetchAllTamperEventsDetail();
			return new ResponseEntity<List<TamperEventsDto>>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/updateMeterReplacementDetails")
	public ResponseEntity<String> updateMeterReplacedDetails(@RequestParam String newMeterSlNo, @RequestParam String tamperDetail,
																@RequestParam String replaceReason, @RequestParam String meterSlNo){
		//System.out.println("newMeterSlNo = "+newMeterSlNo+" meterSlNo == "+meterSlNo+" tamperDetail == "+tamperDetail+" replaceReason == "+replaceReason);
		try {
			boolean status = service.updateMeterReplacedDetails(newMeterSlNo, tamperDetail, replaceReason, meterSlNo);
			if(status) {
				return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem in updating meter replacement details");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/fetchAllTamperDataWithMeterDetails")
	public ResponseEntity<?> getAllTamperEventsDetailWithMeterDetails(){
		try {
			List<TamperEventAndMeterDetailsDto> fetchedData = service.fetchAllTamperAndMeterDetailsData();
			return new ResponseEntity<List<TamperEventAndMeterDetailsDto>>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/udatedUnitPrice")
	public ResponseEntity<?> getRecentUnitPriceValue(){
		try {
			int value = tariffRepo.getRecentUnitPriceDetail();
			return new ResponseEntity<Integer>(value, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching unit price value!");
		}
	}
	
	@GetMapping("/fetchAllMqttApiKeys")
	public ResponseEntity<?> getAllMqttApiKeysDetails(){
		try {
			List<MqttApiKey_Dto> fetchedData = service.fetchAllMqttDetails();
			return new ResponseEntity<List<MqttApiKey_Dto>>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping("/generateOtpBasedOnEmail/{email}")
	public ResponseEntity<?> generateOtpByOnEmail(@PathVariable String email){
		try {
			boolean isExists = loginRepo.existsByEmail(email);
			if(isExists) {
				String otp = generateOTP(email);
				sendOtpToValidEmail(email, otp);
				return new ResponseEntity<String>(otp, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Invalid Email Id!", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	public String generateOTP(String loggedInEmail) {
	    Random random = new Random();
	    int otpValue = 100_000 + random.nextInt(900_000);
	    String otp = String.valueOf(otpValue);
	    // Calculate expiration time (current time + 60 seconds)
//	    Timestamp expirationTime = new Timestamp(System.currentTimeMillis() + 60 * 1000);
	    // Update OTP and expiration in the database
	    loginRepo.updateOtpBasedOnEmail(otp, loggedInEmail);
	    return otp;
	}
	
	public void sendOtpToValidEmail(String email, String otp) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String originalSenderEmail = "noreply@melangesystems.com";
            helper.setFrom(originalSenderEmail);
            helper.setTo(email);
            
            /*List<String> ccList = new ArrayList<>();
            if (firstReportManagerEmail != null) {
                ccList.add(firstReportManagerEmail);
            }
            if (secondReportManagerEmail != null) {
                ccList.add(secondReportManagerEmail);
            }
            if (!ccList.isEmpty()) {
                helper.setCc(ccList.toArray(new String[0]));
            }*/
            
            helper.setSubject("Generated OTP"); 
            helper.setText("Dear Team,<br><br>" + 
                           "Please use the following One-Time Password (OTP) to complete your authentication:<br><br>" +
                           "<b>OTP: </b>"+otp+"<br><br>"+
                           "Please do not share it with anyone.<br><br>" + 
                           "Best regards,<br>The Melange Team", true);

            mailSender.send(message);
            
        } catch (MessagingException | MailException e) {
            System.out.println(e);
            throw new Exception("Failed to send email");
        }
	}
	
	@PostMapping("/insertApiKeysData")
	public ResponseEntity<?> saveApiKeysDetails(@RequestBody MqttApiKey_Dto dto){
		try {
			int status = service.insertApiKetDetails(dto);
			if(status == 1) {
				return new ResponseEntity<String>("Data inserted successfully!", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("There is a problem in saving api key details", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/fetchAllRechargeFinishData")
	public ResponseEntity<?> getAllRechargeFinishData(){
		try {
			List<RechargeFinish_Dto> fetchedData = service.getAllRechargeFinishDetails();
			return new ResponseEntity<List<RechargeFinish_Dto>>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping("/billPayment")
	public ResponseEntity<?> postPaymentDetails(@RequestBody PaymentBill_Dto payLoad){
		try {
			System.out.println("payLoad == "+payLoad);
			return new ResponseEntity<String>("Payment Done!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
