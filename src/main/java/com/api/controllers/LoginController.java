package com.api.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.models.SmsPojo;
import com.api.repositories.StudentRepository;
import com.api.services.SmsService;

@RestController
public class LoginController {
	
	@Autowired
    private StudentRepository studentRepository;
	
    @Autowired
    private SimpMessagingTemplate webSocket;
    
	@Autowired
    SmsService service;
	
	 private final String  TOPIC_DESTINATION = "/lesson/sms";
	
	
	 @PostMapping("/getloginotp")
	   public ResponseEntity<String> sendOTP(@RequestBody SmsPojo sms)
	   {
//		 check student are registered  or not
		   String s = sms.getMobileNumber();   
		   if(this.studentRepository.findByMobileNumber(s) != null) 
		   {
			   try{
		         	 System.out.println("hello"); 
               //	 send opt at time of user login
		         	 service.send(sms);
		              System.out.println("hello");
		              webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+sms.getMobileNumber());
		              return new ResponseEntity<String>("OTP Send successfully",HttpStatus.OK);
		              }
		             catch(Exception e){

		         	 return new ResponseEntity<String>("somthing problem",HttpStatus.INTERNAL_SERVER_ERROR);
		         }
		   }
		   else
		   {
			   return ResponseEntity.ok("user are not  Register");
		   
		  
		            }
		   
	   }
	
	 private String getTimeStamp() {
	        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	     }
	 
	
	
	
	
	

}