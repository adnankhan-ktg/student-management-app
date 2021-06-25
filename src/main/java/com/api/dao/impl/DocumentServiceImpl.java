package com.api.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.models.Document;
import com.api.repositories.DocumentRepository;
import com.api.services.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	@Override
	public Document addDocument(Document document) {
		Document tempDocument = null;
		
		try {
              tempDocument = this.documentRepository.save(document);
              return tempDocument;
			
		} catch (Exception e) {
		     e.printStackTrace();
		     return tempDocument;
		}
		
		
		
	}

}
