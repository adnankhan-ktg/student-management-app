package com.api.models;

public class JwtRequest {
	
	private String mobileNumber;
	private String otp;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public JwtRequest(String mobileNumber, String otp) {
		super();
		this.mobileNumber = mobileNumber;
		this.otp = otp;
	}
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
