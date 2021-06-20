package com.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Authentication {

	@PostMapping("/Authenticate")
	public ResponseEntity<String> Authenticate(){
		
		System.out.println("Authenticated");
		
		return new ResponseEntity<String>("successfully Authenticate",HttpStatus.OK);
	}
	
}
