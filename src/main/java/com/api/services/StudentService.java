package com.api.services;

import com.api.models.Student;

public interface StudentService {
	
	 public Student addStudent(Student student);
	 public Student studentExist(String str);

}