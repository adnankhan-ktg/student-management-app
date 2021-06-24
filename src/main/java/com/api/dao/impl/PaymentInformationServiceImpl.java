package com.api.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.models.PaymentInformation;
import com.api.repositories.PaymentInformationRepository;
import com.api.services.PaymentInformationService;

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
