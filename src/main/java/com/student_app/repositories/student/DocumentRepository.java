package com.student_app.repositories.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.student_app.models.student.Document;

@Repository
public interface DocumentRepository extends MongoRepository<Document, String> {
	
	 public Document findByMobileNumber(String mobileNumber);
	 
	 
	 

}
