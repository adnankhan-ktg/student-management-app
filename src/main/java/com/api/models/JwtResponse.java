package com.api.models;

public class JwtResponse {
	private String token;
    private String activeFirstName;
    private String activeLastName;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getActiveFirstName() {
		return activeFirstName;
	}
	public void setActiveFirstName(String activeFirstName) {
		this.activeFirstName = activeFirstName;
	}
	public String getActiveLastName() {
		return activeLastName;
	}
	public void setActiveLastName(String activeLastName) {
		this.activeLastName = activeLastName;
	}
	public JwtResponse(String token, String activeFirstName, String activeLastName) {
		super();
		this.token = token;
		this.activeFirstName = activeFirstName;
		this.activeLastName = activeLastName;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
 

		

}
