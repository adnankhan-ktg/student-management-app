package com.student_app.service.impl.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_app.models.student.Document;
import com.student_app.repositories.student.DocumentRepository;
import com.student_app.services.student.DocumentService;

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
	@Override
	public Document status(String mobileNumber) {
		      
		            Document d = null;
		   try {
			    d = this.documentRepository.findByMobileNumber(mobileNumber);
			    return d;
		              	   
		   }catch (Exception e) {
		          e.printStackTrace();
                     return d;		          
		}
	}
	@Override
	public Document update(Document document) {
		
		Document d = this.documentRepository.findByMobileNumber(document.getMobileNumber());
	
		  d.setTenthMarksheet(((document.getTenthMarksheet() != null) ? document.getTenthMarksheet() : d.getTenthMarksheet()));
		  d.setTwelthMarksheet(((document.getTwelthMarksheet() != null) ? document.getTwelthMarksheet() : d.getTwelthMarksheet()));
		  d.setIncomeCertificate(((document.getIncomeCertificate() != null) ? document.getIncomeCertificate() : d.getIncomeCertificate()));
		  d.setCastCertificate(((document.getCastCertificate() != null) ? document.getCastCertificate() : d.getCastCertificate()));
		  d.setDomicileCertificate(((document.getDomicileCertificate() != null) ? document.getDomicileCertificate() : d.getDomicileCertificate()));
		  d.setTcCopy(((document.getTcCopy() != null) ? document.getTcCopy() : d.getTcCopy()));
		  d.setPassportPhoto(((document.getPassportPhoto() != null) ? document.getPassportPhoto() : d.getPassportPhoto()));
		  d.setAadharCard(((document.getAadharCard() != null) ? document.getAadharCard() : d.getAadharCard()));
		  d.setBankPassBook(((document.getBankPassBook() != null) ? document.getBankPassBook() : d.getBankPassBook()));
		  d.setHouseFrontPhotoWithFamily(((document.getHouseFrontPhotoWithFamily() != null) ? document.getHouseFrontPhotoWithFamily() : d.getHouseFrontPhotoWithFamily()));
		  
		          
		   Document d1 = null;
		   try {
        		      d1 = this.documentRepository.save(d);
        		      return d1;
		   }catch (Exception e) {
			 e.printStackTrace();
			 return d;
		}
		   
		   
                		
	}
	@Override
	public Document getDocument(String mobileNumber) {
		Document document12 = null;
		try {
			     document12 = this.documentRepository.findByMobileNumber(mobileNumber);
			     return document12;
			
		}catch (Exception e) {
		 e.printStackTrace();
		 return document12;
		}
	}
	

}
