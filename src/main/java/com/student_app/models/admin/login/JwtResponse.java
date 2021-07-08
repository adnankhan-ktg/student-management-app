package com.student_app.models.admin.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private String token;
	private String activeAdminFirstName;
	private String activeAdminLastName;
	

}
