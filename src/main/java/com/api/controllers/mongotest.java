package com.api.controllers;

import java.util.List;
import com.api.repositories.StudentRepository;
//import java.util.List;
import com.api.models.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.api.models.student;
@RestController
public class mongotest {

	 @Autowired
	    public StudentRepository studentRepository;
	
	
	
	 @GetMapping("/all")
	    public List<Student> getAllStudent() {

	        System.out.println("hy1");
	        return studentRepository.findAll();
	    }

	    @PostMapping("/create")
	    public ResponseEntity<HttpStatus> createStudent(@RequestBody Student student) {

	        System.out.println(student.toString());
	        student.setMobileNumber(null);
	        Student insertStudent = studentRepository.save(student);
	         String response = "Student created name is " + insertStudent.getFirstName() + " " + insertStudent.getLastName();
	         System.out.println(response);
	        return  ResponseEntity.ok(HttpStatus.OK);
	    }
}
