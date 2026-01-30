package com.loraDuoMeter.Config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.loraDuoMeter.Service.CustomUserDetailsService;

@Configuration
public class Security {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private CustomAuthEntryPoint customAuthEntryPoint;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth.requestMatchers("/api/auth/**").permitAll()
												.requestMatchers("/api/admin/**", "/api/dashboard/**","/api/downlink/**",
												"/api/facility/**").hasRole("Admin")
												.requestMatchers(HttpMethod.GET, "/**").permitAll()
												.requestMatchers("/api/zones/**").hasAnyRole("User", "Admin")
												.requestMatchers("/api/user/**").hasAnyRole("User", "Admin")
												.requestMatchers("/api/tariff/**").hasAnyRole("User", "Admin")
							                    .requestMatchers("/api/water-tariff/**").hasAnyRole("User", "Admin")												
												.anyRequest().authenticated())
				.sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            )
				.exceptionHandling(ex->ex.authenticationEntryPoint(customAuthEntryPoint))
				.cors(cors -> cors.configurationSource(request -> {
                    var config = new CorsConfiguration();
                    //	config.setAllowedOrigins(List.of("http://192.168.1.227:1997"));
                    config.setAllowedOrigins(List.of("http://106.51.72.200:2026"));
                    config.setAllowedOriginPatterns(List.of("*"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
				.userDetailsService(customUserDetailsService)
				.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
}
