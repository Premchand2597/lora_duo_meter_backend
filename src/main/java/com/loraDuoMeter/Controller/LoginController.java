package com.loraDuoMeter.Controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loraDuoMeter.Config.CookieService;
import com.loraDuoMeter.Config.JwtUtil;
import com.loraDuoMeter.DTO.CustomUserDto;
import com.loraDuoMeter.DTO.LoginCustomResponse;
import com.loraDuoMeter.DTO.LoginDto;
import com.loraDuoMeter.DTO.RegisterDto;
import com.loraDuoMeter.Entity.RefreshToken_Entity;
import com.loraDuoMeter.Repo.RefreshToken_Repo;
import com.loraDuoMeter.Service.CustomUserDetailsService;
import com.loraDuoMeter.Service.Main_Service;

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
			
			CustomUserDto fetchedUserData = (CustomUserDto) customUserDetailsService.loadUserByUsername(user.getUsername());
			
			// use cookie service to attach refresh token in cookie
	        cookieService.attachRefreshCookie(response, refreshToken, 86400);
	        cookieService.addNoStoreHeader(response);
			
			LoginCustomResponse res = new LoginCustomResponse(user.getUsername(), accessToken, 
					user.getAuthorities().iterator().next().getAuthority(), fetchedUserData.getName());
			
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

	    if (refreshToken == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token missing");
	    }

	    // 1️⃣ Check if refresh token exists in DB
	    RefreshToken_Entity storedToken = refreshToken_Repo.findByToken(refreshToken);
	    String username = storedToken.getUsername();
	    
	    if (storedToken == null || storedToken.isRevoked()) {
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

	    return ResponseEntity.ok(new LoginCustomResponse(username, newAccessToken, role, user.getName()));
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

	    cookieService.clearRefreshCookie(response);

	    return ResponseEntity.ok("Logged out successfully");
	}

}
