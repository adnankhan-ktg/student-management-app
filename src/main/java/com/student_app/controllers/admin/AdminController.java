package com.student_app.controllers.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.student.Document;
import com.student_app.models.student.Student;
import com.student_app.repositories.student.DocumentRepository;
import com.student_app.repositories.student.StudentRepository;
import com.student_app.services.student.DocumentService;
import com.student_app.services.student.StudentService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DocumentService documentService;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DocumentRepository documentRepository;
   
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
	    
	    
	    @PostMapping("/documents")
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
	    
	    @PostMapping("/update-student")
	    public ResponseEntity<?> updateStuent(@RequestBody Student student)
	    {
	    	 
	    	    Student tempStudent = this.studentRepository.save(student);
	    	    return ResponseEntity.status(HttpStatus.OK).body(tempStudent);
	    }
	    
	    @PostMapping("/update-document")
	    public ResponseEntity<?> documentUpdate(@RequestBody Document document)
	    {
	    	 if(this.documentRepository.findByMobileNumber(document.getMobileNumber()) != null)
	    	   {
	    		   System.out.println("already");
	    		      Document d = this.documentService.update(document);
	    		      if(d == null)
	    		      {
	    		    	  return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	    		      }
	    		      else
	    		      {
	    		    	  return ResponseEntity.status(HttpStatus.CREATED).body(d);
	    		      }
	    	   }
	    	   else {
	    	    
	    	      Document document1 = this.documentService.addDocument(document);
	    	      if(document1 == null)
	    	      {
	    	    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    	      }
	    	      else {
	    	    	  return ResponseEntity.status(HttpStatus.CREATED).build();
	    	      }
	    		}

	    }
	    
}
