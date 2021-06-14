package com.api.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.config.JwtTokenUtil;
import com.api.models.JwtRequest;
import com.api.models.SmsPojo;
import com.api.models.Student;
import com.api.repositories.StudentRepository;
import com.api.services.JwtUserDetailsService;
import com.api.services.OtpService;
import com.api.services.SmsService;

@RestController
public class LoginController {
	
	@Autowired
    private StudentRepository studentRepository;
	
    @Autowired
    private SimpMessagingTemplate webSocket;
    
	@Autowired
    SmsService service;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	 private final String  TOPIC_DESTINATION = "/lesson/sms";
	 
	 
	 @GetMapping("/home")
	 public String home()
	 {
	  return "Home+Home";
		 
	 }
	
	
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
	 
	 @PostMapping("/login")
	   public ResponseEntity<String> loginStudent(@RequestBody JwtRequest request)
	   {
               		
				String phoneno = "+91"+request.getMobileNumber();

				
			    int serverOtp = otpService.getOtp(phoneno);
				int clientOtp = Integer.parseInt(request.getOtp());
				System.out.println("client"+clientOtp);
				System.out.println("server"+serverOtp);
				
				if(clientOtp == serverOtp) {
					otpService.clearOTP(phoneno);
//					student.setOTP(0);
					System.out.println("otp varified");
					
					final UserDetails userDetails = userDetailsService.loadUserByUsername("+91"+request.getMobileNumber());
					System.out.println(userDetails.getUsername());

					final String token = jwtTokenUtil.generateToken(userDetails);
			          
					 
					  
					return ResponseEntity.ok(token);
				}

				
				return null;
					
					
					
					
	   }}