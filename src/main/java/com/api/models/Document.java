package com.api.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;


@org.springframework.data.mongodb.core.mapping.Document(collection = "document")
@Component
public class Document {
	
	@Id
	private String mobileNumber;
	private String tenthMarksheet;
	private String twelthMarksheet;
	private String incomeCertificate;
	private String castCertificate;
	private String domicileCertificate;
	private String tcCopy;
	private String passportPhoto;
	private String aadharCard;
	private String BankPassBook;
	private String houseFrontPhotoWithFamily;
	private List<String> otherCertificate;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getTenthMarksheet() {
		return tenthMarksheet;
	}
	public void setTenthMarksheet(String tenthMarksheet) {
		this.tenthMarksheet = tenthMarksheet;
	}
	public String getTwelthMarksheet() {
		return twelthMarksheet;
	}
	public void setTwelthMarksheet(String twelthMarksheet) {
		this.twelthMarksheet = twelthMarksheet;
	}
	public String getIncomeCertificate() {
		return incomeCertificate;
	}
	public void setIncomeCertificate(String incomeCertificate) {
		this.incomeCertificate = incomeCertificate;
	}
	public String getCastCertificate() {
		return castCertificate;
	}
	public void setCastCertificate(String castCertificate) {
		this.castCertificate = castCertificate;
	}
	public String getDomicileCertificate() {
		return domicileCertificate;
	}
	public void setDomicileCertificate(String domicileCertificate) {
		this.domicileCertificate = domicileCertificate;
	}
	public String getTcCopy() {
		return tcCopy;
	}
	public void setTcCopy(String tcCopy) {
		this.tcCopy = tcCopy;
	}
	public String getPassportPhoto() {
		return passportPhoto;
	}
	public void setPassportPhoto(String passportPhoto) {
		this.passportPhoto = passportPhoto;
	}
	public String getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}
	public String getBankPassBook() {
		return BankPassBook;
	}
	public void setBankPassBook(String bankPassBook) {
		BankPassBook = bankPassBook;
	}
	public String getHouseFrontPhotoWithFamily() {
		return houseFrontPhotoWithFamily;
	}
	public void setHouseFrontPhotoWithFamily(String houseFrontPhotoWithFamily) {
		this.houseFrontPhotoWithFamily = houseFrontPhotoWithFamily;
	}
	public List<String> getOtherCertificate() {
		return otherCertificate;
	}
	public void setOtherCertificate(List<String> otherCertificate) {
		this.otherCertificate = otherCertificate;
	}
	public Document(String mobileNumber, String tenthMarksheet, String twelthMarksheet, String incomeCertificate,
			String castCertificate, String domicileCertificate, String tcCopy, String passportPhoto, String aadharCard,
			String bankPassBook, String houseFrontPhotoWithFamily, List<String> otherCertificate) {
		super();
		this.mobileNumber = mobileNumber;
		this.tenthMarksheet = tenthMarksheet;
		this.twelthMarksheet = twelthMarksheet;
		this.incomeCertificate = incomeCertificate;
		this.castCertificate = castCertificate;
		this.domicileCertificate = domicileCertificate;
		this.tcCopy = tcCopy;
		this.passportPhoto = passportPhoto;
		this.aadharCard = aadharCard;
		BankPassBook = bankPassBook;
		this.houseFrontPhotoWithFamily = houseFrontPhotoWithFamily;
		this.otherCertificate = otherCertificate;
	}
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Document [mobileNumber=" + mobileNumber + ", tenthMarksheet=" + tenthMarksheet + ", twelthMarksheet="
				+ twelthMarksheet + ", incomeCertificate=" + incomeCertificate + ", castCertificate=" + castCertificate
				+ ", domicileCertificate=" + domicileCertificate + ", tcCopy=" + tcCopy + ", passportPhoto="
				+ passportPhoto + ", aadharCard=" + aadharCard + ", BankPassBook=" + BankPassBook
				+ ", houseFrontPhotoWithFamily=" + houseFrontPhotoWithFamily + ", otherCertificate=" + otherCertificate
				+ "]";
	}
	
	
	
	
	
	
	
		

}
