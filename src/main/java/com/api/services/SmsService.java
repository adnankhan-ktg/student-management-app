package com.api.services;

//import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import com.api.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import org.springframework.util.MultiValueMap;
import com.api.models.SmsPojo;

//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;

//import okhttp3.OkHttpClient;
//import okhttp3.RequestBody;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//import okhttp3.Call;



@Component
@Service
public class SmsService {
//     private final String ACCOUNT_SID ="AC728c523dea14738f035b8a0fa1155ef3";
//
//    private final String AUTH_TOKEN = "925d365fe522036141e42b0c54cbd3a4";
//
//    private final String FROM_NUMBER = "+12677972584";
    
    @Autowired
    private	OtpService otpService;
    
    
    OkHttpClient client = new OkHttpClient();

    public void send(SmsPojo sms) throws ParseException , IOException, InterruptedException  {
//    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        int number=otpService.generateOTP(sms.getMobileNumber());
//        String msg ="Your OTP - "+number+ " please verify this OTP in SSISM Student_app";
//        System.out.println(map.get("mobileNumber"));
      
    	
           

      
        
        
        
       
    	int GeneratedOtp=otpService.generateOTP(sms.getMobileNumber());
        String GeneratedOtpString = Integer.toString(GeneratedOtp);

        String mobileNumber = sms.getMobileNumber();
        String Type = sms.getSmsType();
        System.out.println("okok"+mobileNumber+Type);
//        

        
//     
        OkHttpClient client = new OkHttpClient().newBuilder()
        		  .build();
        		MediaType mediaType = MediaType.parse("application/json");
//        		@SuppressWarnings("deprecation")
				RequestBody body = RequestBody.create( "{"
        				+ "\n  \"SenderId\": \"BWBHSC\","
        				+ "\n  \"Is_Unicode\": true,"
        				+ "\n  \"Is_Flash\": false,"
        				+ "\n  \"Message\": \"नमस्ते,सेफ क्रॉप एप्लीकेशन में लॉगिन करने के लिए, दिया गया "+GeneratedOtpString+" नंबर डाले। सादर बेलवर्क्स इनोवेशन प्राइवेट लिमिटेड\","
        				+ "\n  \"MobileNumbers\": \""+mobileNumber+"\","
        				+ "\n  \"ApiKey\": \"6SCzcO1CzznAffqHDTdfr4b2atB+d1HE22rGGWa5kWM=\","
        				+ "\n  \"ClientId\": \"e038f2d0-1e34-46ef-8513-92a2ee011a57\""
        				+ "\n}",mediaType);
        		Request request = new Request.Builder()
        		  .url("https://api.mylogin.co.in/api/v2/SendSMS")
        		  .method("POST", body)
        		  .addHeader("Content-Type", "application/json")
        		  .addHeader("ApiKey", "6SCzcO1CzznAffqHDTdfr4b2atB+d1HE22rGGWa5kWM=")
        		  .build();
        		Response response = client.newCall(request).execute();
        		System.out.println(response.body().string());

   
       
    }


}