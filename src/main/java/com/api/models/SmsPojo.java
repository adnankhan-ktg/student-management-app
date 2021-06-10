package com.api.models;

public class SmsPojo {
	

	private String phoneNo;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = "+91"+phoneNo;
	}

	@Override
	public String toString() {
		return "SmsPojo [phoneNo=" + phoneNo + "]";
	}
	
}
