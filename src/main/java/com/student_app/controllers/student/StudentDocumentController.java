package com.student_app.controllers.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.student.Document;
import com.student_app.repositories.student.DocumentRepository;
import com.student_app.services.student.DocumentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentDocumentController {
	
	@Autowired
	private DocumentService documentService;
	@Autowired
	private DocumentRepository documentRepository;
	
	
	@PostMapping("/upload")
	public ResponseEntity<?> addDocument(@RequestBody Document document)
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
String username = userDetails.getUsername();
  document.setMobileNumber(username);
  System.out.println(document);
   if(this.documentRepository.findByMobileNumber(username) != null)
   {
	   System.out.println("already");
	      Document d = this.documentService.update(document);
	      if(d == null)
	      {
	    	  return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	      }
	      else
	      {
	    	  return ResponseEntity.status(HttpStatus.CREATED).build();
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
  
  
  

	   @GetMapping("/status") 
	   public ResponseEntity<?> status()
	   {
		   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	                .getPrincipal();
	String username = userDetails.getUsername();
	System.out.println(username);
	
	        Document document = null;
	        
	           document = this.documentService.status(username);
	           if(document == null)
	           {
	        	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Document());
	           }
	           else {
	        	         Document d = new Document();
	        	         d.setMobileNumber(document.getMobileNumber());
	        	         d.setTenthMarksheet(((document.getTenthMarksheet() == null) ? "false" : "true"));
	        	         d.setTwelthMarksheet(((document.getTwelthMarksheet() == null) ? "false" : "true"));
	        	         d.setIncomeCertificate(((document.getIncomeCertificate() == null) ? "false" : "true"));
	        	         d.setCastCertificate(((document.getCastCertificate()== null) ? "false" : "true"));
	        	         d.setDomicileCertificate(((document.getDomicileCertificate() == null) ? "false" : "true"));
	        	         d.setTcCopy(((document.getTcCopy() == null) ? "false" : "true"));
	        	         d.setPassportPhoto(((document.getPassportPhoto() == null ) ? "false" : "true"));
	        	         d.setAadharCard(((document.getAadharCard() == null) ? "false" : "true"));
	        	         d.setBankPassBook(((document.getBankPassBook() == null ) ? "false" : "true"));
	        	         d.setHouseFrontPhotoWithFamily(((document.getHouseFrontPhotoWithFamily() == null) ? "false" : "true"));
	        	         
	        	         return ResponseEntity.status(HttpStatus.OK).body(d);
	        	        		 
	        	         
	        	           
	           }
	         
	          
	   }
	
	        
	

}
