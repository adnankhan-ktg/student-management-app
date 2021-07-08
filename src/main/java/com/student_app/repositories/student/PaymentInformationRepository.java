package com.student_app.repositories.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.student_app.models.student.PaymentInformation;

@Repository
public interface PaymentInformationRepository extends MongoRepository<PaymentInformation, String> {
	
	  PaymentInformation findByMobileNumber(String mobileNumberString);

}
