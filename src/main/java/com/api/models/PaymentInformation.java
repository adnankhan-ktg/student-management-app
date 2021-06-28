package com.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment_information")
public class PaymentInformation {
	
	@Id
	private String mobileNumber;
	private String razorpay_order_id;
	private String razorpay_payment_id;
	private String date;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRazorpay_order_id() {
		return razorpay_order_id;
	}
	public void setRazorpay_order_id(String razorpay_order_id) {
		this.razorpay_order_id = razorpay_order_id;
	}
	public String getRazorpay_payment_id() {
		return razorpay_payment_id;
	}
	public void setRazorpay_payment_id(String razorpay_payment_id) {
		this.razorpay_payment_id = razorpay_payment_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public PaymentInformation(String mobileNumber, String razorpay_order_id, String razorpay_payment_id, String date) {
		super();
		this.mobileNumber = mobileNumber;
		this.razorpay_order_id = razorpay_order_id;
		this.razorpay_payment_id = razorpay_payment_id;
		this.date = date;
	}
	public PaymentInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaymentInformation [mobileNumber=" + mobileNumber + ", razorpay_order_id=" + razorpay_order_id
				+ ", razorpay_payment_id=" + razorpay_payment_id + ", date=" + date + "]";
	}
	    
	   
	
	}
