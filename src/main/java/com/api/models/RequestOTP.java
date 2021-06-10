package com.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OTP")
public class RequestOTP {
	 
	@Id
	private String mobileNumber;
	private String OTP;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	public RequestOTP(String mobileNumber, String oTP) {
		super();
		this.mobileNumber = mobileNumber;
		OTP = oTP;
	}
	public RequestOTP() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RequestOTP [mobileNumber=" + mobileNumber + ", OTP=" + OTP + "]";
	}
	
	
	
	
	
	
	

}
