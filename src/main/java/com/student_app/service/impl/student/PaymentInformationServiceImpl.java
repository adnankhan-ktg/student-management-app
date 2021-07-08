package com.student_app.service.impl.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_app.models.student.PaymentInformation;
import com.student_app.repositories.student.PaymentInformationRepository;
import com.student_app.services.student.PaymentInformationService;

@Service
public class PaymentInformationServiceImpl implements PaymentInformationService {
	
	@Autowired
	private PaymentInformationRepository paymentInformationRepository;

	@Override
	public PaymentInformation addPaymentInformation(PaymentInformation info) {
		
		 PaymentInformation payInfo = null;
		try {
                  payInfo = this.paymentInformationRepository.save(info);
                  return payInfo;
		}
		catch (Exception e) {
			e.printStackTrace();
			return payInfo;
		}
            
		    

	}

}
