package com.student_app.services;

import java.io.IOException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.student_app.models.SmsPojo;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
@Service
public class SmsService {
    
    @Autowired
    private	OtpService otpService;
    
    
    OkHttpClient client = new OkHttpClient();

    public void send(SmsPojo sms) throws ParseException , IOException, InterruptedException  {
        
       
    	int GeneratedOtp=otpService.generateOTP(sms.getMobileNumber());
        String GeneratedOtpString = Integer.toString(GeneratedOtp);

        String mobileNumber = sms.getMobileNumber();
        String Type = sms.getSmsType();
   
        OkHttpClient client = new OkHttpClient().newBuilder()
        		  .build();
        		MediaType mediaType = MediaType.parse("application/json");
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