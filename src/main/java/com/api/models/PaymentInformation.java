package com.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment_information")
public class PaymentInformation {
	
	@Id
	private String mobileNumber;
	private String orderId;
	private String paymentId;
	private String date;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public PaymentInformation(String mobileNumber, String orderId, String paymentId, String date) {
		super();
		this.mobileNumber = mobileNumber;
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.date = date;
	}
	public PaymentInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaymentInformation [mobileNumber=" + mobileNumber + ", orderId=" + orderId + ", paymentId=" + paymentId
				+ ", date=" + date + "]";
	}
	
	
	
	

}
