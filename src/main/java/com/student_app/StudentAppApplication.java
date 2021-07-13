package com.student_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentAppApplication {

	private static final Logger log = LoggerFactory.getLogger(StudentAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(StudentAppApplication.class, args);
		log.info("Application is Strated");
		
		
	}

}
