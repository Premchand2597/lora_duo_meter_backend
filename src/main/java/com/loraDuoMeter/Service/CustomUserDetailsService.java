package com.loraDuoMeter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loraDuoMeter.DTO.CustomUserDto;
import com.loraDuoMeter.Entity.LoginEntity;
import com.loraDuoMeter.Repo.LoginRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private LoginRepo loginRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginEntity user = loginRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
		return new CustomUserDto(user);
	}
}
