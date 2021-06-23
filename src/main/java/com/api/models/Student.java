package com.api.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
     private String otp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFatherFirstName() {
		return fatherFirstName;
	}
	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSchoolStream() {
		return schoolStream;
	}
	public void setSchoolStream(String schoolStream) {
		this.schoolStream = schoolStream;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getCollageStream() {
		return collageStream;
	}
	public void setCollageStream(String collageStream) {
		this.collageStream = collageStream;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getTenthPercentage() {
		return tenthPercentage;
	}
	public void setTenthPercentage(String tenthPercentage) {
		this.tenthPercentage = tenthPercentage;
	}
	public String getEleventhPercentage() {
		return eleventhPercentage;
	}
	public void setEleventhPercentage(String eleventhPercentage) {
		this.eleventhPercentage = eleventhPercentage;
	}
	public String getTwelthPercentage() {
		return twelthPercentage;
	}
	public void setTwelthPercentage(String twelthPercentage) {
		this.twelthPercentage = twelthPercentage;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getFatherAnnualIncome() {
		return fatherAnnualIncome;
	}
	public void setFatherAnnualIncome(String fatherAnnualIncome) {
		this.fatherAnnualIncome = fatherAnnualIncome;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Student(String id, String firstName, String lastName, String fatherFirstName, String mobileNumber,
			String schoolStream, String townName, String collageStream, String aadharNumber, String schoolName,
			String tenthPercentage, String eleventhPercentage, String twelthPercentage, String casteCategory,
			String fatherAnnualIncome, String districtName, String otp) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fatherFirstName = fatherFirstName;
		this.mobileNumber = mobileNumber;
		this.schoolStream = schoolStream;
		this.townName = townName;
		this.collageStream = collageStream;
		this.aadharNumber = aadharNumber;
		this.schoolName = schoolName;
		this.tenthPercentage = tenthPercentage;
		this.eleventhPercentage = eleventhPercentage;
		this.twelthPercentage = twelthPercentage;
		this.casteCategory = casteCategory;
		this.fatherAnnualIncome = fatherAnnualIncome;
		this.districtName = districtName;
		this.otp = otp;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", fatherFirstName="
				+ fatherFirstName + ", mobileNumber=" + mobileNumber + ", schoolStream=" + schoolStream + ", townName="
				+ townName + ", collageStream=" + collageStream + ", aadharNumber=" + aadharNumber + ", schoolName="
				+ schoolName + ", tenthPercentage=" + tenthPercentage + ", eleventhPercentage=" + eleventhPercentage
				+ ", twelthPercentage=" + twelthPercentage + ", casteCategory=" + casteCategory
				+ ", fatherAnnualIncome=" + fatherAnnualIncome + ", districtName=" + districtName + ", otp=" + otp
				+ "]";
	}
     
	
     
     
     
     
}
