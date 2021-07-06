package com.api.services;

import java.util.List;

import com.api.models.Document;

public interface DocumentService {
	
	Document addDocument(Document document);
	Document status(String mobileNumber);
	Document update(Document d);
	public Document getDocument(String id);


}
