package com.student_app.controllers.student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.SmsPojo;
import com.student_app.models.student.Student;
import com.student_app.models.student.login.JwtRequest;
import com.student_app.models.student.login.JwtResponse;
import com.student_app.repositories.student.StudentRepository;
import com.student_app.security.config.JwtTokenUtil;
import com.student_app.services.JwtUserDetailsService;
import com.student_app.services.OtpService;
import com.student_app.services.SmsService;

@RestController
@CrossOrigin
public class StudentLoginController {
	
	@Autowired
    private StudentRepository studentRepository;

    
	@Autowired
    SmsService service;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	 @PostMapping("/get-login-otp")
	   public ResponseEntity<String> sendOTP(@RequestBody SmsPojo sms)
	   {
		   String s = sms.getMobileNumber();   
		   if(this.studentRepository.findByMobileNumber(s) != null) 
		   {
			   try{
		         	 System.out.println("hello"); 
            
		         	 sms.setSmsType("Login");
		         	 service.send(sms);
		              System.out.println("hello");
		              return new ResponseEntity<String>("OTP Send successfully",HttpStatus.OK);
		              }
		             catch(Exception e){

		         	 return new ResponseEntity<String>("OTP not send",HttpStatus.INTERNAL_SERVER_ERROR);
		         }
		   }
		   else
		   {
			   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User are not register");
		   
		  
		            }
		   
	   }
	
	           private String getTimeStamp() {
	        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	     }
	 
	 @PostMapping("/login_student")
	   public ResponseEntity<?> loginStudent(@RequestBody JwtRequest request)
	   {
               		
				String phoneno = "91"+request.getMobileNumber();

				
			    int serverOtp = otpService.getOtp(phoneno);
				int clientOtp = Integer.parseInt(request.getOtp());
				System.out.println("client"+clientOtp);
				System.out.println("server"+serverOtp);
				
				if(clientOtp == serverOtp) {
					otpService.clearOTP(phoneno);
					System.out.println("otp varified");
					
					final UserDetails userDetails = userDetailsService.loadUserByUsername("91"+request.getMobileNumber());
					System.out.println(userDetails.getUsername());

					final String token1 = jwtTokenUtil.generateToken(userDetails);
					
				    String username = "91"+request.getMobileNumber();
				    
				    Student s = this.studentRepository.findByMobileNumber(username);
				    
				    
				    return ResponseEntity.ok(new JwtResponse(token1, s.getFirstName(),s.getLastName(),s.getMobileNumber()));
			 
				}else {
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please enter correct OTP");
				}
	   }

		
					
					
	   }