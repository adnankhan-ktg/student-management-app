package com.api.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.config.JwtTokenUtil;
import com.api.models.JwtResponse;
import com.api.models.Student;
import com.api.models.admin.Admin;
import com.api.models.admin.login.JwtRequest;
import com.api.repositories.admin.AdminRepository;
import com.api.services.JwtUserDetailsService;

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
	   
	  @PostMapping("/token")
	  public ResponseEntity<?> RequestToken(@RequestBody JwtRequest jwtRequest)
	  {
//		  System.out.println(("$2a$10$24i6k4mqvrhQubT.917WT.T3SNbx2f5qnlfqC6oSXwbEpqw7mAsPa").equals("adnan123"));
          		  
//		      Admin tempAdmin = this.adminRepository.findByUsername(jwtRequest.getUsername());
//		      
//		      if(tempAdmin != null)
//		      {
//		    	  if((jwtRequest.getUsername().equals(tempAdmin.getUsername())) && jwtRequest.getPassword().equals(tempAdmin.getPassword()));  
//		      }
		  Admin admin1 = null;
//		  jwtRequest.setPassword(bcryptEncoder.encode(jwtRequest.getPassword()));
		  System.out.println(jwtRequest.getPassword());
		    admin1 = this.adminRepository.findByUsernameAndPassword(jwtRequest.getUsername(), jwtRequest.getPassword());
//		    System.out.println(admin1.getUsername());
		    if(admin1 != null)
		    {
		    
		    final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
			System.out.println(userDetails.getUsername());

			final String token1 = jwtTokenUtil.generateToken(userDetails);
		    
		    
		    return ResponseEntity.ok(new com.api.models.admin.login.JwtResponse(token1,admin1.getFirstName(),admin1.getLastName()));
		    }
		    else {
		    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		    }
		    
		    
		      
	  }
	

}
