package com.student_app.controllers.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.student_app.models.admin.Admin;
import com.student_app.models.admin.login.JwtRequest;
import com.student_app.repositories.admin.AdminRepository;
import com.student_app.security.config.JwtTokenUtil;
import com.student_app.services.JwtUserDetailsService;

@RestController
@CrossOrigin
public class AdminLoginController {
    
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	private static final Logger log = LoggerFactory.getLogger(AdminLoginController.class);
	   
	  @PostMapping("/login_admin")
	  public ResponseEntity<?> RequestToken(@RequestBody JwtRequest jwtRequest)
	  {
		  log.info("Request came on the admin login controller");

		  Admin admin1 = null;
		  
		  log.info("Object send to the DAO layer for check username and password");
		   if(this.adminRepository.findByUsername(jwtRequest.getUsername()) == null)
		   {          
			    log.debug("Given admin doesn't exists with this username");
			   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		   }
		    admin1 = this.adminRepository.findByUsernameAndPassword(jwtRequest.getUsername(), jwtRequest.getPassword());
		    if(admin1 != null)
		    {
		    
		    final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
            
		    log.info("Object send for generate token");
			final String token1 = jwtTokenUtil.generateToken(userDetails);
		    
		      log.debug("Admin login successfully");
		    return ResponseEntity.ok(new com.student_app.models.admin.login.JwtResponse(token1,admin1.getFirstName(),admin1.getLastName()));
		    }
		    else {
		    	log.error("Enter admin password is incorrect with this username");
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		    }
		    
		    
		      
	  }
	

}
