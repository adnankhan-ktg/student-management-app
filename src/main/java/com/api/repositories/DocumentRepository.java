package com.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.models.Document;

@Repository
public interface DocumentRepository extends MongoRepository<Document, String> {
	
	 public Document findByMobileNumber(String mobileNumber);

}
