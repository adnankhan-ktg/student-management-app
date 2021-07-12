package com.student_app.controllers.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_app.models.student.Document;
import com.student_app.models.student.PaymentInformation;
import com.student_app.models.student.ProgressStatus;
import com.student_app.models.student.Student;
import com.student_app.repositories.student.DocumentRepository;
import com.student_app.repositories.student.PaymentInformationRepository;
import com.student_app.repositories.student.StudentRepository;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class ProgressBarController {
	
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private PaymentInformationRepository paymentRepository;
	
	@GetMapping("/progress-status")
	public ResponseEntity<?> progressStatus()
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
String username = userDetails.getUsername();
    Student student = this.studentRepository.findByMobileNumber(username);
      
     PaymentInformation payment = this.paymentRepository.findByMobileNumber(username);
     Document document = this.documentRepository.findByMobileNumber(username);
      ProgressStatus status = new ProgressStatus();
      
      status.setPersonalInfo(((student.getTenthPercentage() != null) ? true : false));
      status.setPaymentInfo(((payment != null) ? true : false));
      status.setDocumentInfo(((document != null) ? true : false));
      
      return ResponseEntity.status(HttpStatus.OK).body(status);
      
		
	}
	

}
