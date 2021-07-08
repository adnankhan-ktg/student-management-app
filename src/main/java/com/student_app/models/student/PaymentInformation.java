package com.student_app.models.student;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payment_information")
public class PaymentInformation {
	
	@Id
	private String mobileNumber;
	private String razorpay_order_id;
	private String razorpay_payment_id;
	private String date;
		}
