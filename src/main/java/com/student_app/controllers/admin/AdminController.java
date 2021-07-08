package com.student_app.controllers.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.student.Document;
import com.student_app.models.student.Student;
import com.student_app.services.student.DocumentService;
import com.student_app.services.student.StudentService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DocumentService documentService;
   
	    @GetMapping("/students")  
	   public ResponseEntity<List<Student>> getStudents()
	   {
		           
	    	
	    List<Student> list =  this.studentService.getStudents();
	    
	    if(list.size() == 0)
	    {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	    else
	    {
	    	return ResponseEntity.status(HttpStatus.OK).body(list);
	    }
	    	         
	   }
	    
	    @PutMapping("/update-student")
	    public ResponseEntity<?> updateStudentAtAdminSide(Student student)
	    {
	    	Student tempStudent = this.studentService.updateStudentAtAdminSide(student);
	    	if(tempStudent == null)
	    	{
	    		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	    	}
	    	else {
	    		return ResponseEntity.status(HttpStatus.OK).body(tempStudent);
	    	}
	    	
	    }
	    
	    @GetMapping("/documents")
	    public ResponseEntity<Document> getDocuments(@RequestBody Map<String,String> map)
	    {
//	    	System.out.println(studentId);
	    	  Document tempDocument = this.documentService.getDocument(map.get("studentId"));
	    	  if(tempDocument == null)
	    	  {
	    		  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    	  }
	    	  else {
	    		  return ResponseEntity.status(HttpStatus.OK).body(tempDocument);
	    	  }
	    }
	    
}
