package com.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.models.Document;
import com.api.services.DocumentService;

@RestController
@CrossOrigin
@RequestMapping("/document")
public class DocumentController {
	
	private DocumentService documentService;
	
	
	@PostMapping("/upload")
	public ResponseEntity<?> addDocument(Document document)
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
String username = userDetails.getUsername();
  document.setMobileNumber(username);
    
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
