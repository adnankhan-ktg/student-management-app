package com.api.services;

import com.api.models.Document;

public interface DocumentService {
	
	Document addDocument(Document document);
	Document status(String mobileNumber);

}
