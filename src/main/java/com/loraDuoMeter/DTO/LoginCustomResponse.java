package com.loraDuoMeter.DTO;

public class LoginCustomResponse {

	private String email;
	private String accessToken;
	private String role;
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "LoginCustomResponse [email=" + email + ", accessToken=" + accessToken + ", role=" + role + ", name="
				+ name + "]";
	}
	public LoginCustomResponse(String email, String accessToken, String role, String name) {
		super();
		this.email = email;
		this.accessToken = accessToken;
		this.role = role;
		this.name = name;
	}
	public LoginCustomResponse() {
		
		// TODO Auto-generated constructor stub
	}
}
