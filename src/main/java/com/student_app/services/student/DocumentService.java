package com.student_app.services.student;

import com.student_app.models.student.Document;

public interface DocumentService {
	
	public Document addDocument(Document document);
	public Document status(String mobileNumber);
	public Document update(Document d);
	public Document getDocument(String id);


}
