package com.student_app.controllers.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.student.Student;
import com.student_app.services.student.StudentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentController {
 
	@Autowired
	  private StudentService studentService;
	
	    @GetMapping("/get_particular_student")
	    public ResponseEntity<?> getStudent()
	    {
	      
	    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	                .getPrincipal();
	String username = userDetails.getUsername();
	
	    	Student tempStudent = null;
	    	tempStudent = this.studentService.getStudent(username);
	    	if(tempStudent != null)
	    	{
	    		return ResponseEntity.status(HttpStatus.OK).body(tempStudent);
	    	}else {
	    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    	}
	    	
	    }
}
