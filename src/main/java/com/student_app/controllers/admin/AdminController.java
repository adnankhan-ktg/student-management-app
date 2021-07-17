package com.student_app.controllers.admin;


import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@PostMapping("/Authenticate")
	public ResponseEntity<String> Authenticate(){
	     log.info("Request came one the Authentication Controller");	
		System.out.println("Authenticated");
		
		return new ResponseEntity<String>("successfully Authenticate",HttpStatus.OK);
	}
   
	    @GetMapping("/students")  
	   public ResponseEntity<List<Student>> getStudents()
	   {
		    log.info("Request came to the students contrller");
		    
		    log.info("Request sent to the service layer");
	    List<Student> list =  this.studentService.getStudents();
	    
	    if(list.size() == 0)
	    {
	    	log.debug("Student list size is zero");
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	    else
	    {
	    	log.debug("Student list is send to the client side");
	    	return ResponseEntity.status(HttpStatus.OK).body(list);
	    }
	    	         
	   }
	    
	    
	    @PostMapping("/documents")
	    public ResponseEntity<Document> getDocuments(@RequestBody Map<String,String> map)
	    {
             log.info("Request came on the documents controller");
	    	  Document tempDocument = this.documentService.getDocument(map.get("studentId"));
	    	  if(tempDocument == null)
	    	  {
	    		  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    	  }
	    	  else {
	    		  log.debug("Response send to the client");
	    		  return ResponseEntity.status(HttpStatus.OK).body(tempDocument);
	    	  }
	    }
	    
	    @PostMapping("/update-student")
	    public ResponseEntity<?> updateStuent(@RequestBody Student student)
	    {
	    	 log.info("Request came on the Update Student method");
	    	 if(this.studentRepository.findByMobileNumber(student.getMobileNumber()) != null)
	    	 {
	    	    Student tempStudent = this.studentRepository.save(student);
	    	    log.debug("Response send to the client");
	    	    if(tempStudent != null)
	    	    {
	    	    return ResponseEntity.status(HttpStatus.OK).body(tempStudent);
	    	    }else {
	    	    	return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	    	    }
	    	 }else
	    	 {
	    		 Student tempStudent1 = this.studentService.addStudent(student);
	    		 if(tempStudent1 == null)
	    		 {
	    			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    		 }
	    		 else {
	    			 return ResponseEntity.status(HttpStatus.OK).body(tempStudent1);
	    		 }
	    	 }
	    }
	    
	    @PostMapping("/update-document")
	    public ResponseEntity<?> documentUpdate(@RequestBody Document document)
	    {
	    	log.info("Request came on the update Student method");
	    	 if(this.documentRepository.findByMobileNumber(document.getMobileNumber()) != null)
	    	   {
	    		       log.info("Document object send to the service layer for update student documents");
	    		      Document d = this.documentService.update(document);
	    		      if(d == null)
	    		      {
	    		    	  log.error("Document not modified");
	    		    	  return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	    		      }
	    		      else
	    		      {
	    		    	  log.debug("Document modified successfully");
	    		    	  return ResponseEntity.status(HttpStatus.CREATED).body(d);
	    		      }
	    	   }
	    	   else {
	    	          log.info("Object send to the document service layer for save the given new Student");
	    	      Document document1 = this.documentService.addDocument(document);
	    	      if(document1 == null)
	    	      {
	    	    	  log.error("Document not modified");
	    	    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    	      }
	    	      else {
	    	    	  log.debug("Document modified successfully");
	    	    	  return ResponseEntity.status(HttpStatus.CREATED).build();
	    	      }
	    		}

	    }
	    
}
