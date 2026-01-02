package com.loraDuoMeter.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class RegisterDto {
	@NotEmpty
	private String name;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegisterDto [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
}
