package com.student_app.controllers.student;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

//import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.razorpay.*;
import com.student_app.Utils.generatePdf;
import com.student_app.models.student.PaymentInformation;
import com.student_app.services.student.PaymentInformationService;

@RestController
@CrossOrigin("/student")
public class StudentPaymentController {
	
	@Autowired
	private generatePdf genratePdf;
	
	@Autowired
	private PaymentInformationService paymentInformationService;
	
	@PostMapping("/create_order")
	public ResponseEntity<String> create_order() throws RazorpayException {
	 
		System.out.println("order");
		
		
		var client = new RazorpayClient("rzp_test_IdYhaTbIE9F0Yt", "aCr3qhk2GydxlrYxSWP97Jx3");
		JSONObject options = new JSONObject();
		options.put("amount",150000);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order = client.Orders.create(options);
		
		if(order != null)
		{
			System.out.println(order);
			
			return new ResponseEntity<String>(order.toString(),HttpStatus.OK);
		}else {
	
			return new ResponseEntity<String>("order not genereted",HttpStatus.UNPROCESSABLE_ENTITY);
	      }
	}
	
	@PostMapping("/create_reciept")
	public ResponseEntity<InputStreamResource> create_reciept(@RequestBody Map<String,String> map) throws RazorpayException, IOException, URISyntaxException, DocumentException {
	 
		System.out.println(map.get("razorpay_order_id")); 
		System.out.println(map.get("razorpay_payment_id"));
		System.out.println(map.get("razorpay_signature"));
	
		System.out.println("order");
		String razorpay_order_id = map.get("razorpay_order_id");
		String razorpay_payment_id = map.get("razorpay_payment_id");
		String razorpay_signature = map.get("razorpay_signature");
		
		JSONObject options = new JSONObject();
		options.put("razorpay_order_id", razorpay_order_id);
		options.put("razorpay_payment_id", razorpay_payment_id);
		options.put("razorpay_signature", razorpay_signature);
		
	boolean generated_signature = Utils.verifyPaymentSignature(options, "aCr3qhk2GydxlrYxSWP97Jx3");
	  
		if (generated_signature) {    

			genratePdf.generatePdfReciept(razorpay_order_id, razorpay_payment_id);
		
			
			System.out.println("order ok "); 
			
			 ClassPathResource pdfFile = new ClassPathResource("reciept.pdf");
			  HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.parseMediaType("application/pdf"));
			  headers.add("Access-Control-Allow-Origin", "*");
			  headers.add("Access-Control-Allow-Methods", "POST");
			  headers.add("Access-Control-Allow-Headers", "Content-Type");
			  headers.add("Content-Disposition", "filename=" + "reciept.pdf");
			  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			  headers.add("Pragma", "no-cache");
			  headers.add("Expires", "0");
			 
			  headers.setContentLength(pdfFile.contentLength());
			  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
			    new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
			  return response;
			
		}else {
			System.out.println("order failed ");
			return new ResponseEntity<InputStreamResource>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	}
	  
	 @PostMapping("/payment-information")
	public ResponseEntity<?> paymentInformation(@RequestBody PaymentInformation paymentInformation)
	{

		 UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	                .getPrincipal();
	String username = userDetails.getUsername();
	paymentInformation.setMobileNumber(username);
	String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	paymentInformation.setDate(date);
	
              PaymentInformation payInfo = null;
              payInfo = this.paymentInformationService.addPaymentInformation(paymentInformation);
              
               if(payInfo == null )
               {
            	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
               }
               else {
            	   return ResponseEntity.status(HttpStatus.OK).build();
               }
	}

	
    }
