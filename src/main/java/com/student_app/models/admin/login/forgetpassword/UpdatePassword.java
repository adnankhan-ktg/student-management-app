package com.student_app.models.admin.login.forgetpassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassword {
	
	private String id;
	private String username;
	private String password;

}
