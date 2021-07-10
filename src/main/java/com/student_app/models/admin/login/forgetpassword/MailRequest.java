package com.student_app.models.admin.login.forgetpassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {
	private String receiverAddress;
	private String message;

}
