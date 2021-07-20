package com.student_app.controllers.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.SmsPojo;
import com.student_app.models.student.Student;
import com.student_app.models.student.login.JwtResponse;
import com.student_app.repositories.student.StudentRepository;
import com.student_app.security.config.JwtTokenUtil;
import com.student_app.services.JwtUserDetailsService;
import com.student_app.services.OtpService;
import com.student_app.services.SmsService;
import com.student_app.services.student.StudentService;

@RestController
@CrossOrigin
public class StudentRegistrationController {
	
	@Autowired
	private OtpService otpservice;
	
    @Autowired
    private StudentService studentService;
	
	@Autowired
    SmsService service;
    
    @Autowired
    private StudentRepository studentRepository;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
    
	
	   @PostMapping("/get-registration-otp")
	   public ResponseEntity<String> sendOTP(@RequestBody SmsPojo sms)
	   {
		   String s = sms.getMobileNumber();  
		   System.out.println(s);
		   if(this.studentRepository.findByMobileNumber(s) != null) 
		   {
			   return new ResponseEntity<String>("user already Register",HttpStatus.ALREADY_REPORTED);
		   }
		   else
		   {
		   
		  
		         try{
	         	 System.out.println("hello"); 
	         	 sms.setSmsType("Registration");
	         	 service.send(sms);
	              System.out.println("hello");
	              return new ResponseEntity<String>("OTP Send successfully",HttpStatus.OK);
	              }
	             catch(Exception e){

	         	 return new ResponseEntity<String>("somthing problem",HttpStatus.INTERNAL_SERVER_ERROR);
	         }     }
		   
	   }
	   
	   
	   @PostMapping("/register_student")
	   public ResponseEntity<?> addStudent(@RequestBody Student student)
	   {
                 
				System.out.println(student.toString());
				
				String phoneno = "91"+student.getMobileNumber();				
			    int serverOtp = otpservice.getOtp(phoneno);
				int clientOtp = Integer.parseInt(student.getOtp());
				System.out.println("client"+clientOtp);
				System.out.println("server"+serverOtp);
				
				if(clientOtp == serverOtp) {
					otpservice.clearOTP(phoneno);
					System.out.println("otp varified");
					 String y = student.getMobileNumber();
					 student.setMobileNumber("91"+y);
					 student.setOtp(null);
					  Student student2 = this.studentService.addStudent(student);
					   if(student2 == null)
					   {
						   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();   
					   }
					   else {
						   
			  final UserDetails userDetails = userDetailsService.loadUserByUsername(student2.getMobileNumber());
			  final String token1 = jwtTokenUtil.generateToken(userDetails);
			    
						   
					   return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponse(token1, student2.getFirstName(), student2.getLastName(),student2.getMobileNumber()));							   
					   }
					
					
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please enter correct 'OTP'!!!");
				}	
	   }
}