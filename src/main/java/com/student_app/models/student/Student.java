package com.student_app.models.student;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "student")
public class Student {
  
	 @Id
     private String id;
     private String firstName;
     private String lastName;
     private String fatherFirstName;
     @Indexed(unique = true)
     private String mobileNumber;
     private String schoolStream;
     private String townName;
     private String collageStream;
     private String aadharNumber;
     private String schoolName;
     private String tenthPercentage;
     private String eleventhPercentage;
     private String twelthPercentage;
     private String casteCategory;
     private String fatherAnnualIncome;
     private String districtName;                                              
     private String gender;
     private String otp;
     private String role = "STUDENT";
     private String status = "for approval";
     private String remark;
     private String altMobileNumber;
    }
