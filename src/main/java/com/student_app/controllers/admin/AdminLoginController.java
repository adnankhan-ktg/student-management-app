package com.student_app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.student_app.models.admin.Admin;
import com.student_app.models.admin.login.JwtRequest;
import com.student_app.repositories.admin.AdminRepository;
import com.student_app.security.config.JwtTokenUtil;
import com.student_app.services.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/admin-login")
public class AdminLoginController {
    
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	   
	  @PostMapping("/login")
	  public ResponseEntity<?> RequestToken(@RequestBody JwtRequest jwtRequest)
	  {

		  Admin admin1 = null;
		  System.out.println(jwtRequest.getPassword());
		    admin1 = this.adminRepository.findByUsernameAndPassword(jwtRequest.getUsername(), jwtRequest.getPassword());
		    if(admin1 != null)
		    {
		    
		    final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
			System.out.println(userDetails.getUsername());

			final String token1 = jwtTokenUtil.generateToken(userDetails);
		    
		    
		    return ResponseEntity.ok(new com.student_app.models.admin.login.JwtResponse(token1,admin1.getFirstName(),admin1.getLastName()));
		    }
		    else {
		    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		    }
		    
		    
		      
	  }
	

}
