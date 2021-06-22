package com.api.dao.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.api.models.Student;
import com.api.repositories.StudentRepository;
import com.api.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	
	  @Autowired
	  private StudentRepository studentRepository;
	  
	  
	@Override
	 
	public Student addStudent(Student student) {
		
		  Student student1 = null;
		  try {
			  student.setId(UUID.randomUUID().toString());
	        student1 =  this.studentRepository.save(student);
	        return student1;
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  return student1;
			  
			  
		  }
	        
	        
	}

	
	@Override
	public Student updateStudent(Student student) {
		
		return this.studentRepository.save(student);
	}


	@Override
	public Student getStudent(String mobile) {
		return this.studentRepository.findByMobileNumber(mobile);
	}


}