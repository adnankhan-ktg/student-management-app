package com.api.models;

import org.springframework.stereotype.Component;

@Component
public class SmsPojo {
	
	private String mobileNumber;

	public String getMobileNumber() {
		return "91"+mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
	public SmsPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SmsPojo(String mobileNumber) {
		super();
		this.mobileNumber = mobileNumber;
	}

	

	
}
