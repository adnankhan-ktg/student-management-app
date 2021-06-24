package com.api.Utils;

//import java.beans.JavaBean;
//import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
//import java.net.URL;
//import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.itextpdf.layout.element.Cell;
import com.api.models.Student;
import com.api.services.StudentService;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
//import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
//import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
//import com.itextpdf.layout.element.Tab;
//import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
//import com.itextpdf.layout.element.Text;
//import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
//import com.itextpdf.layout.renderer.CellRenderer;
//import com.itextpdf.layout.renderer.IRenderer;
//import org.springframework.stereotype.Service;
//import com.itextpdf.*;
//import com.itextpdf.layout.c


@Service
public class generatePdf {
	@Autowired
	private StudentService studentService;
 
	public void generatePdfReciept(String ID) throws FileNotFoundException, URISyntaxException {
		
		System.out.println("reciept "+ ID);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

         Student tempStudent = this.studentService.getStudent(username);

        System.out.println(tempStudent.toString());
        
        String StudentFullName = tempStudent.getFirstName()+" "+tempStudent.getLastName()+" S/O "+tempStudent.getFatherFirstName();
		 
		
		 
		 ClassLoader classLoader = getClass().getClassLoader();
		 String path  = classLoader.getResource("reciept.pdf").getPath();
		 System.out.println(path);
		 
		
		PdfWriter pdfWriter = new PdfWriter(path);
		
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.addNewPage();
		
		Document document = new Document(pdfDocument);
		
		String Title = "SANT SINGAJI INSTITUTE OF SCIENCE AND MANAGEMENT";
		Paragraph TitleP = new Paragraph(Title); 
		TitleP.setTextAlignment(TextAlignment.CENTER);
		TitleP.setFontSize(18);
		TitleP.setMultipliedLeading(0.7F);
		document.add(TitleP); 
		
		String TitleAddress = "Campus : NH-59, Gram-Sandalpur, Teh-Khategaon, Dist-Dewas (MP), Pin Code: 455339";
		Paragraph TitleAddressP = new Paragraph(TitleAddress);
		TitleAddressP.setTextAlignment(TextAlignment.CENTER);
		TitleAddressP.setMultipliedLeading(0.7F);
		document.add(TitleAddressP);
		
		document.add(new Paragraph("\n"));

		
		String Reciept ="RECEIPT";
		Paragraph RecieptP = new Paragraph(Reciept);
		RecieptP.setTextAlignment(TextAlignment.CENTER);
		RecieptP.setUnderline();
		RecieptP.setFontSize(18);
		document.add(RecieptP);
		
		String RecieptId = "BCA12345";
		String RecieptNo = "Reciept No : " + RecieptId;
		Paragraph RecieptNoP = new Paragraph(RecieptNo);
		RecieptNoP.setTextAlignment(TextAlignment.RIGHT);
		document.add(RecieptNoP);
		
		 String pattern = "dd-MM-yyyy";
		String dateInString =new SimpleDateFormat(pattern).format(new Date());
		String Date = "Date : "+dateInString;
		Paragraph DateP = new Paragraph(Date);
		DateP.setTextAlignment(TextAlignment.RIGHT);
		document.add(DateP);
		

		document.add(new Paragraph("\n"));
		
		@SuppressWarnings("deprecation")
		Table table = new Table(2);
		table.addCell("Received with Thanks From : ");
		table.getCell(0,0).setBorder(Border.NO_BORDER);
		table.getCell(0,0).setWidthPercent(30);
		table.getCell(0,0).setFontSize(14);
		
       
		Cell name = new Cell();
		name.add(StudentFullName);
		name.setFontSize(14);
		 Border nameBorder = new SolidBorder(Color.BLACK, 1);
		 name.setBorder(Border.NO_BORDER);
		 name.setBorderBottom(nameBorder);
		 
		 table.addCell(name);
        
		document.add(table);
		
		
document.add(new Paragraph("\n"));
		
		@SuppressWarnings("deprecation")
		Table table2 = new Table(2);
		table2.addCell("Received By The Sum of Rupees : ");
		table2.getCell(0,0).setBorder(Border.NO_BORDER);
		table2.getCell(0,0).setWidthPercent(40);
		table2.getCell(0,0).setFontSize(14);
		
       
		Cell price = new Cell();
		price.add("1500/-(One Thousand Five Hundred Only)");
		price.setFontSize(14);
		price.setBorder(Border.NO_BORDER);
		 Border priceBorder = new SolidBorder(Color.BLACK, 1);
		 price.setBorderBottom(priceBorder);
		 
		 table2.addCell(price);
        
		document.add(table2);
		
document.add(new Paragraph("\n"));
		
		@SuppressWarnings("deprecation")
		Table table3 = new Table(2);
		table3.addCell("Pay By : ");
		table3.getCell(0,0).setBorder(Border.NO_BORDER);
		table3.getCell(0,0).setWidthPercent(8);
		table3.getCell(0,0).setFontSize(14);
		
       
		Cell pay = new Cell();
		pay.add("Online Payment SSISM Student App	");
		pay.setFontSize(14);
		pay.setBorder(Border.NO_BORDER);
		 Border payBorder = new SolidBorder(Color.BLACK, 1);
		 pay.setBorderBottom(payBorder);
		 table3.addCell(pay);
		 
		 document.add(table3);

		 
		 
		 document.add(new Paragraph("\n"));
		 @SuppressWarnings("deprecation")
			Table table4 = new Table(3);
			table4.addCell("RS.");
//			table3.getCell(0,0).setBorder(Border.NO_BORDER);
			table4.getCell(0,0).setWidthPercent(3);
			table4.getCell(0,0).setFontSize(14);
			
			table4.addCell("1500/-");
//			table3.getCell(0,0).setBorder(Border.NO_BORDER);
			table4.getCell(0,1).setWidthPercent(8);
			table4.getCell(0,1).setFontSize(14);
			
		 
			document.add(table4);
			
			document.add(new Paragraph("\n"));
			String Auth = "Authorized Signature";
			Paragraph AuthP = new Paragraph(Auth);
			AuthP.setTextAlignment(TextAlignment.RIGHT);
			AuthP.setFontSize(14);
			document.add(AuthP);
		 
        
		
		 
//		document.toString();
		document.close();
		
		System.out.println("file closed");
		
		
	}
	
	
	
}
