package com.student_app.models.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressStatus {
	
	private boolean personalInfo;
	private boolean paymentInfo;
	private boolean documentInfo;

}
