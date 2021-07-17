package com.student_app.controllers.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.student.PaymentInformation;
import com.student_app.models.student.Student;
import com.student_app.repositories.student.PaymentInformationRepository;
import com.student_app.repositories.student.StudentRepository;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class Authentication {

	  @Autowired
	  private StudentRepository studentRepository;
	  @Autowired
	  private PaymentInformationRepository paymentInformationRepository;
	@PostMapping("/Authenticate")
	public ResponseEntity<String> Authenticate(){
		
		System.out.println("Authenticated");
		
		return new ResponseEntity<String>("successfully Authenticate",HttpStatus.OK);
	}
	
	
	   @GetMapping("/personal-information-status")
	   public ResponseEntity<?> checkPersonalInformation()
	   {
		   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	                .getPrincipal();
	String username = userDetails.getUsername();
	
	        Student student = this.studentRepository.findByMobileNumber(username);
	           
	        if(student.getCollageStream() != null)
	        {
	        	return ResponseEntity.status(HttpStatus.OK).build();
	        }
	        else {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	      
	   }
	   
	   
	   @GetMapping("/payment-information-status")
	   public ResponseEntity<?> checkPaymentInformation()
	   {
		   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	                .getPrincipal();
	String username = userDetails.getUsername();
	
	      PaymentInformation payInfo = this.paymentInformationRepository.findByMobileNumber(username);
	      if(payInfo != null)
	      {
	    	  return  ResponseEntity.status(HttpStatus.OK).build();
	      }
	      else {
	    	  return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	      }
		   
	   }
	
}