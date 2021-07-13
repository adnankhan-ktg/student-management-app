package com.student_app.models.admin;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admin")
public class Admin {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String role = "ADMIN";

}
