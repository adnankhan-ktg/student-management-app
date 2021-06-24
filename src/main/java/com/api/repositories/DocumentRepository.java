package com.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.models.Document;

public interface DocumentRepository extends MongoRepository<Document, String> {

}
