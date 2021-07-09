package com.student_app.controllers.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.student.Student;
import com.student_app.services.student.StudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentPersonalInformationController {
	  
@Autowired
private StudentService studentService;
	
	@PostMapping("/personal-information")
	public ResponseEntity<?> updateStudent(@RequestBody Student student)
	{
		System.out.println("I am in the personal Information Page");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
String username = userDetails.getUsername();

Student tempStudent = this.studentService.getStudent(username);

//............................
student.setId(tempStudent.getId());
student.setFirstName(tempStudent.getFirstName());
student.setLastName(tempStudent.getLastName());
student.setFatherFirstName(tempStudent.getFatherFirstName());
student.setMobileNumber(tempStudent.getMobileNumber());
student.setSchoolStream(tempStudent.getSchoolStream());
student.setTownName(tempStudent.getTownName());
student.setDistrictName(tempStudent.getDistrictName());
student.setGender(tempStudent.getGender());

Student stu = this.studentService.updateStudent(student);
if(stu == null) {
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
else {
	
	return ResponseEntity.status(HttpStatus.OK).build();
}



	}

}
