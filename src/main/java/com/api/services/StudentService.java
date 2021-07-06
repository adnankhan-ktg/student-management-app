package com.api.services;

import java.util.List;

import com.api.models.Student;

public interface StudentService {
	
	 public Student addStudent(Student student);
	 public Student updateStudent(Student student);
	 public Student updateStudentAtAdminSide(Student student);
	 public Student getStudent(String mobile);
	 public List<Student> getStudents();
	 

}