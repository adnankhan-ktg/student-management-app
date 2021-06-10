package com.api.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Student studentExist(String str) {
		 Student s = null;
		try {
		    s = this.studentRepository.findByMobileNumber(str);
		    return s;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return s;
		}
	}

}