package com.student_app.controllers.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.student_app.models.admin.Admin;
import com.student_app.models.admin.login.forgetpassword.MailRequest;
import com.student_app.models.admin.login.forgetpassword.PasswordForgetRequest;
import com.student_app.models.admin.login.forgetpassword.UpdatePassword;
import com.student_app.repositories.admin.AdminRepository;
import com.student_app.services.MailService;
import com.student_app.services.OtpService;
import com.student_app.services.admin.AdminService;

@RestController
@CrossOrigin
public class AdminPasswordForgetController {
	
	@Autowired
	private OtpService otpService;
	@Autowired
	private MailService mailService;
	@Autowired
	private AdminRepository adminRepository;	
	@Autowired
	private AdminService adminService;
	
	private static final Logger log = LoggerFactory.getLogger(AdminPasswordForgetController.class);
	
	@PostMapping("/get-password-forget-otp")
	public ResponseEntity<?> getOtp(@RequestBody PasswordForgetRequest request)
	{
		log.info("Request came on the password forget otp method");
		log.info("Object sent to the DAO layer for check the User is existing or not");
		if(adminRepository.findByUsername(request.getUsername()) == null)
		{
			log.error("Username not found in the Database");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		log.info("Username send to the  otpService for generating otp");
		int GeneratedOtp = otpService.generateOTP(request.getUsername());
		String GeneratedOtpString = Integer.toString(GeneratedOtp);
		
		MailRequest mailRequest = new MailRequest();
		mailRequest.setMessage(GeneratedOtpString);
		mailRequest.setReceiverAddress(request.getUsername());
		
		log.info("Object send to the mailService for send the otp");
		mailService.sendemail(mailRequest);
		log.debug("Mail send successfully");
        return ResponseEntity.status(HttpStatus.OK).build();		
	}
	     
	      @PostMapping("/validate-otp")
	      public ResponseEntity<?> validateOtp(@RequestBody PasswordForgetRequest request)
	      {
	    	  log.info("Request came on the valudate otp method");
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
	    	     log.info("Request came on the update password controller");
	    	     log.info("Object send to the admin repository layer for find the user");
	    	    Admin tempAdmin = this.adminRepository.findByUsername(updatePassword.getUsername());
	    	    if(tempAdmin != null)
	    	    {
	    	    	if(updatePassword.getId().equals(tempAdmin.getId()) == true)
	    	    	{
	    	    		tempAdmin.setPassword(updatePassword.getPassword());
		    	    	Admin admin12 = this.adminService.updateAdmin(tempAdmin);
		    	    	log.debug("password update successfully");
		    	    	return ResponseEntity.status(HttpStatus.OK).build();
	    	    	}
	    	    }
	    	    
	    	    log.debug("User not found in the database");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	
	     }
	      }
             

