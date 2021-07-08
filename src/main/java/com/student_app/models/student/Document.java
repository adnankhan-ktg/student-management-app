package com.student_app.models.student;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document(collection = "document")
@Component
public class Document {
	
	@Id
	private String mobileNumber;
	private String tenthMarksheet;
	private String twelthMarksheet;
	private String incomeCertificate;
	private String castCertificate;
	private String domicileCertificate;
	private String tcCopy;
	private String passportPhoto;
	private String aadharCard;
	private String BankPassBook;
	private String houseFrontPhotoWithFamily;
	private List<String> otherCertificate;
	}
