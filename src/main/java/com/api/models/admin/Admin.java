package com.api.models.admin;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admin")
public class Admin {
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String role = "admin";

}
