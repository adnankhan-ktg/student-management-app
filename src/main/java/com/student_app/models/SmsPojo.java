package com.student_app.models;

import org.springframework.stereotype.Component;

@Component
public class SmsPojo {
	
	private String mobileNumber;

	public String getMobileNumber() {
		return "91"+mobileNumber;
	}
	
//	smstype = login / register
	public String smsType;
	
	public String getSmsType() {
		return smsType;
	}


	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public SmsPojo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SmsPojo(String mobileNumber, String smsType) {
		super();
		this.mobileNumber = mobileNumber;
		this.smsType = smsType;
	}

	
	

	

	
}
