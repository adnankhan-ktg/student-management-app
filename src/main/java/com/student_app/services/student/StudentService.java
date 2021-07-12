package com.student_app.services.student;

import java.util.List;

import com.student_app.models.student.Student;

public interface StudentService {
	
	 public Student addStudent(Student student);
	 public Student updateStudent(Student student);
	 public Student getStudent(String mobile);
	 public List<Student> getStudents();
	 

}