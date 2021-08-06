package com.student_app.service.impl.student;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.student_app.models.student.Student;
import com.student_app.repositories.student.StudentRepository;
import com.student_app.services.student.StudentService;

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
		
		
		Student tempStudent = null;
		try {
		tempStudent = this.studentRepository.save(student);
		return tempStudent;
		}catch (Exception e) {
		  e.printStackTrace();
		  return tempStudent;
		}
	}


	@Override
	public Student getStudent(String mobile) {
		Student tempStudent = null;
		try {
		tempStudent = this.studentRepository.findByMobileNumber(mobile);
		return tempStudent;
		
	}catch (Exception e) {
	        e.printStackTrace();
	        return tempStudent;
	        	
	}
		}


	@Override
	public List<Student> getStudents() {
		
		   return this.studentRepository.findAll();
	}

}