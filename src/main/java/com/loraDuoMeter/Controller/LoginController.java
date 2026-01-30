package com.loraDuoMeter.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.loraDuoMeter.Config.CookieService;
import com.loraDuoMeter.Config.JwtUtil;
import com.loraDuoMeter.DTO.CustomUserDto;
import com.loraDuoMeter.DTO.LoginCustomResponse;
import com.loraDuoMeter.DTO.LoginDto;
import com.loraDuoMeter.DTO.NotificationIndicationDto;
import com.loraDuoMeter.DTO.NotificationIndicationWithResidentDetailsDto;
import com.loraDuoMeter.DTO.RegisterDto;
import com.loraDuoMeter.Entity.RefreshToken_Entity;
import com.loraDuoMeter.Repo.NotificationIndicationRepo;
import com.loraDuoMeter.Repo.RefreshToken_Repo;
import com.loraDuoMeter.Service.CustomUserDetailsService;
import com.loraDuoMeter.Service.Main_Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
    private CookieService cookieService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private RefreshToken_Repo refreshToken_Repo;
	
	@Autowired
	private Main_Service mainService;
	
	@Autowired
	private NotificationIndicationRepo notificationIndicationRepo;
	
	@Autowired
    private JavaMailSender mailSender;
	
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	
	
	@PostMapping("/register")
	public ResponseEntity<?> saveRegistration(@Valid @RequestBody RegisterDto req){
		try {
			RegisterDto response = mainService.addNewData(req);
			return new ResponseEntity<RegisterDto>(response, HttpStatus.CREATED);
		} catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	    }
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDto req, HttpServletResponse response){
		try {
			
			Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), 
																										req.getPassword()));
			
			CustomUserDto user = (CustomUserDto) authenticate.getPrincipal();
			
			String accessToken = jwtUtil.generateAccessToken(user.getUsername(), 
					 user.getAuthorities().iterator().next().getAuthority());
			
			//System.out.println("user.getAuthorities().iterator().next().getAuthority() = "+user.getAuthorities().iterator().next().getAuthority());
		    
			String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());
			
			RefreshToken_Entity savedRefreshToken = new RefreshToken_Entity(refreshToken, LocalDateTime.now(), 
					LocalDateTime.now().plusSeconds(86400), user.getUsername(), false, null);
			
			refreshToken_Repo.save(savedRefreshToken);
			
			// use cookie service to attach refresh token in cookie
	        cookieService.attachRefreshCookie(response, refreshToken, 86400);
	        cookieService.addNoStoreHeader(response);
			
			LoginCustomResponse res = new LoginCustomResponse(user.getUsername(), accessToken, 
					user.getAuthorities().iterator().next().getAuthority());
			
			return new ResponseEntity<LoginCustomResponse>(res, HttpStatus.OK);
		} catch (BadCredentialsException ex) {
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("message", "Invalid email or password"));
	    } catch (Exception ex) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("message", "Something went wrong"));
	    }
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {

	    String refreshToken = cookieService.getRefreshTokenFromCookie(request);
	    
	    System.out.println("refreshToken == "+refreshToken);

	    if (refreshToken == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token missing");
	    }

	    // 1️⃣ Check if refresh token exists in DB
	    RefreshToken_Entity storedToken = refreshToken_Repo.findByToken(refreshToken);
	    String username = storedToken.getUsername();
	    
	    if (storedToken == null || storedToken.isRevoked()) {
	    	System.out.println("removing refresh token from refreshhh endpoint");
	        cookieService.clearRefreshCookie(response);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token invalid");
	    }

	    // 2️⃣ Check if token expired in DB
	    if (storedToken.getExpiresAt().isBefore(LocalDateTime.now())) {
	        cookieService.clearRefreshCookie(response);
	        storedToken.setRevoked(true);
	        refreshToken_Repo.save(storedToken);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
	    }

	    // 3️⃣ Validate signature normally
	    if (!jwtUtil.validateRefreshToken(refreshToken)) {
	        cookieService.clearRefreshCookie(response);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token signature");
	    }
	    
	    // Load user for role
	    CustomUserDto user = (CustomUserDto) customUserDetailsService.loadUserByUsername(username);
	    String role = user.getAuthorities().iterator().next().getAuthority();

	    // 4️⃣ Generate new access + refresh tokens
	    String newAccessToken = jwtUtil.generateAccessToken(username, role);
	    String newRefreshToken = jwtUtil.generateRefreshToken(username + UUID.randomUUID());

	    // 5️⃣ Revoke old token in DB
	    storedToken.setRevoked(true);

	    // 6️⃣ Save new token in DB
	    RefreshToken_Entity newTokenEntity = new RefreshToken_Entity(
	            newRefreshToken,
	            LocalDateTime.now(),
	            LocalDateTime.now().plusSeconds(86400),
	            username,
	            false,
	            storedToken.getToken() // replaced_by_token
	    );

	    refreshToken_Repo.save(storedToken);
	    refreshToken_Repo.save(newTokenEntity);

	    // 7️⃣ Send new refresh token in cookie
	    cookieService.attachRefreshCookie(response, newRefreshToken, 86400);
	    cookieService.addNoStoreHeader(response);

	    return ResponseEntity.ok(new LoginCustomResponse(username, newAccessToken, role));
	}

	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {

	    String refreshToken = cookieService.getRefreshTokenFromCookie(request);

	    if (refreshToken != null) {
	        RefreshToken_Entity storedToken = refreshToken_Repo.findByToken(refreshToken);
	        if (storedToken != null) {
	            storedToken.setRevoked(true);
	            refreshToken_Repo.save(storedToken);
	        }
	    }
	    
	    System.out.println("removing refresh token from logout endpoint");

	    cookieService.clearRefreshCookie(response);

	    return ResponseEntity.ok("Logged out successfully");
	}
	
	/*@GetMapping("/totalNotification")
	public ResponseEntity<?> fetchTotalNotificationCount(){
		try {
			Map<String, Long> data = new HashMap<String, Long>();
			long fetchedCount = notificationIndicationRepo.getTotalNotificationCount();
			data.put("totalNotificationCount", fetchedCount);
			return new ResponseEntity<Map<String, Long>>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}*/
	
	@GetMapping(value = "/totalNotification", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter fetchTotalNotificationCount() {

        SseEmitter emitter = new SseEmitter(0L); // 0 = never timeout

        executor.execute(() -> {
            try {
                while (true) {
                    long count = notificationIndicationRepo.getTotalNotificationCount();

                    emitter.send(SseEmitter.event()
                            .name("totalNotificationCount")
                            .data(count));

                    Thread.sleep(5000); // send update every 5 seconds
                }
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
	
	@GetMapping("/notificationDetails")
	public ResponseEntity<?> getAllNotificationDetailLists(){
		try {
			List<NotificationIndicationDto> fetchedData = mainService.fetchAllNotificationDetails();
			return new ResponseEntity<List<NotificationIndicationDto>>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/notificationDetailsWithResidentData")
	public ResponseEntity<?> getAllNotificationDetailListsWithResidentDetails(){
		try {
			List<NotificationIndicationWithResidentDetailsDto> fetchedData = mainService.fetchAllNotificationDetailsWithResidentDetails();
			return new ResponseEntity<List<NotificationIndicationWithResidentDetailsDto>>(fetchedData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("/notificationEmailAlert/{email}")
	public ResponseEntity<?> sendEmailNotificationToResidentForNotificationAlert(@PathVariable String email, @RequestParam String eventName, 
			@RequestParam String meterSlNo, @RequestParam String residentName){
		try {
			sendEmailDraftForNotificationAlert(email, eventName, meterSlNo, residentName);
			return new ResponseEntity<String>("Email sent successfully!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	public void sendEmailDraftForNotificationAlert(String email, String eventName, String meterSlNo, String residentName) throws Exception {
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
            
            helper.setSubject("Notification: "+eventName); 
            helper.setText("Dear Team,<br><br>" + 
                           "A notification alert has been raised for the following details:<br><br>" +
                           "<b>Resident Name: </b>"+residentName+"<br>"+
                           "<b>Notification Event: </b>"+eventName+"<br>"+
                           "<b>Meter SL No: </b>"+meterSlNo+"<br><br>"+
                           "Kindly review the above information at your earliest convenience.<br><br>" + 
                           "Best regards,<br>The Melange Team", true);

            mailSender.send(message);
            
            if("Meter powered up".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForPowerUp(meterSlNo);
            }else if("Daily updated notify".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForDailyUpdate(meterSlNo);
            }else if("Recharge completed".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForRechargeFinish(meterSlNo);
            }else if("Battery cut off".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForBatteryCutOff(meterSlNo);
            }else if("Gas leak alert".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForGasLeak(meterSlNo);
            }else if("Instantaneous data notify".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForInstantaneousData(meterSlNo);
            }else if("Manually clicked event".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForManualClickDevice(meterSlNo);
            }else if("Meter info details".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForMeterInfo(meterSlNo);
            }else if("Tampering event".equals(eventName)) {
            	notificationIndicationRepo.updateNotificationTableForTamperEvent(meterSlNo);
            }
            
        } catch (MessagingException | MailException e) {
            System.out.println(e);
            throw new Exception("Failed to send email");
        }
	}

}
