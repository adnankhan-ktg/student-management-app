package com.student_app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.admin.Admin;
import com.student_app.models.admin.login.forgetpassword.MailRequest;
import com.student_app.models.admin.login.forgetpassword.PasswordForgetRequest;
import com.student_app.models.admin.login.forgetpassword.UpdatePassword;
import com.student_app.repositories.admin.AdminRepository;
import com.student_app.repositories.student.StudentRepository;
import com.student_app.services.MailService;
import com.student_app.services.OtpService;
import com.student_app.services.admin.AdminService;

@RestController
public class AdminPasswordForgetController {
	
	@Autowired
	private OtpService otpService;
	@Autowired
	private MailService mailService;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/get-password-forget-otp")
	public ResponseEntity<?> getOtp(@RequestBody PasswordForgetRequest request)
	{
		if(adminRepository.findByUsername(request.getUsername()) == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		int GeneratedOtp = otpService.generateOTP(request.getUsername());
		String GeneratedOtpString = Integer.toString(GeneratedOtp);
		
		MailRequest mailRequest = new MailRequest();
		mailRequest.setMessage(GeneratedOtpString);
		mailRequest.setReceiverAddress(request.getUsername());
		
		mailService.sendemail(mailRequest);
        return ResponseEntity.status(HttpStatus.OK).build();		
	}
	     
	      @PostMapping("/validate-otp")
	      public ResponseEntity<?> validateOtp(@RequestBody PasswordForgetRequest request)
	      {
	    	  Admin admin = adminRepository.findByUsername(request.getUsername());
	    	  if(admin != null)
	    	  {
	    	  int serverOtp = otpService.getOtp(request.getUsername());
	    	  int clientOtp = Integer.parseInt(request.getOtp());
	    	  
	    	  if(serverOtp == clientOtp)
	    	   {
	    			otpService.clearOTP(request.getUsername());
	    			return ResponseEntity.status(HttpStatus.OK).body(admin.getId());
	    	   }
	    	  else {
	    		  return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	    	  }
	    	  }
	    	  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    	  
	      }
	      
	      @PostMapping("/update-password")
	      public ResponseEntity<?> setNewPassword(@RequestBody UpdatePassword updatePassword)
	      {
	    	     
	    	    Admin tempAdmin = this.adminRepository.findByUsername(updatePassword.getUsername());
	    	    if(tempAdmin != null)
	    	    {
	    	    	if(updatePassword.getId().equals(tempAdmin.getId()) == true)
	    	    	{
	    	    		tempAdmin.setPassword(updatePassword.getPassword());
		    	    	Admin admin12 = this.adminService.updateAdmin(tempAdmin);
		    	    	return ResponseEntity.status(HttpStatus.OK).build();
	    	    	}
	    	    }
	    	    
//	    	    System.out.println(updatePassword.getId().equals(tempAdmin.getId()));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	
//	    	    if(tempAdmin != null && (tempAdmin.getId().equals(updatePassword.getId())) == true );
//	    	    {
//	    	    	tempAdmin.setPassword(updatePassword.getNewPassword());
//	    	    	this.adminService.updateAdmin(tempAdmin);
//	    	    	return ResponseEntity.status(HttpStatus.OK).build();
//	    	    }
	     }
	      }
             

