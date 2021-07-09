package com.student_app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.admin.Admin;
import com.student_app.services.admin.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin-registration")
public class AdminRegistrationController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<?> addAdmin(@RequestBody Admin admin)
	{
		    
		   Admin tempAdmin = this.adminService.addAdmin(admin);
			if(tempAdmin == null)
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			}
			else {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
	}

	        
}
