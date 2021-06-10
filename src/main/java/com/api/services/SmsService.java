package com.api.services;

import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import com.api.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import org.springframework.util.MultiValueMap;
import com.api.models.SmsPojo;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
@Service
public class SmsService {
     private final String ACCOUNT_SID ="AC728c523dea14738f035b8a0fa1155ef3";

    private final String AUTH_TOKEN = "925d365fe522036141e42b0c54cbd3a4";

    private final String FROM_NUMBER = "+12677972584";
    
    @Autowired
    private	OtpService otpService;

    public void send(SmsPojo sms) throws ParseException {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
    	
        
        int number=otpService.generateOTP(sms.getPhoneNo());
      
        
        String msg ="Your OTP - "+number+ " please verify this OTP in SSISM Student_app";
       
        
        Message message = Message.creator(new PhoneNumber(sms.getPhoneNo()), new PhoneNumber(FROM_NUMBER), msg).create();
        System.out.println(message);
       
    }

//    public void receive(MultiValueMap<String, String> smscallback) {
//   
//    }

}