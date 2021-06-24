package com.api.Utils;


//import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.api.models.Student;
import com.api.services.StudentService;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.DocumentException;






@Service
public class generatePdf {
	@Autowired
	private StudentService studentService;
 
	public void generatePdfReciept(String orderID , String paymentID) throws FileNotFoundException, URISyntaxException, MalformedURLException, DocumentException {
		
//		System.out.println("reciept "+ );
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

         Student tempStudent = this.studentService.getStudent(username);

        System.out.println(tempStudent.toString());
        
//<<<<<<< HEAD
//        String StudentFullName = tempStudent.getFirstName()+" "+" S/O "+tempStudent.getFatherFirstName()+" "+tempStudent.getLastName();
//		 
//=======
       String FirstName = Character.toUpperCase(tempStudent.getFirstName().charAt(0))+tempStudent.getFirstName().substring(1);
       String FatherFirstName = Character.toUpperCase(tempStudent.getFatherFirstName().charAt(0))+tempStudent.getFatherFirstName().substring(1);
        
        String StudentFullName = FirstName+" "+tempStudent.getLastName()+" S/O "+FatherFirstName;
		String studentClass = tempStudent.getCollageStream();
//>>>>>>> ad17561c111515d94ac46d2661b6f72d2c56aaf1
		
		
//		reciept path
		 ClassLoader classLoader = getClass().getClassLoader();
		 String path  = classLoader.getResource("reciept.pdf").getPath();
		 System.out.println(path);
		 
//		 logo path
		 String logoPath = classLoader.getResource("logo.png").getPath();
		 ImageData logoData = ImageDataFactory.create(logoPath);
		 Image logoImg = new Image(logoData);
//		 System.out.println(logoPath);
		 
//		 stamp path
		 String stampPath = classLoader.getResource("stamp.png").getPath();
		 ImageData stampData = ImageDataFactory.create(stampPath);
		 Image StampImg = new Image(stampData);
		 
		
		PdfWriter pdfWriter = new PdfWriter(path);
		
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.addNewPage();
		
		Document document = new Document(pdfDocument);
		
	
	
//		
		@SuppressWarnings("deprecation")
		
		
//        add logo
		Table table0 = new Table(2);
		table0.addCell(logoImg);
		table0.getCell(0, 0).setWidthPercent(2);
		table0.getCell(0, 0).setBorder(Border.NO_BORDER);
		
		 
		 List header = new List();
//		   add title
		 String Title = "SANT SINGAJI INSTITUTE OF SCIENCE AND MANAGEMENT";
		 ListItem titleItem = new ListItem(Title);
		 titleItem.setFontSize(15);
		 titleItem.setMarginTop(15);
		 titleItem.setTextAlignment(TextAlignment.CENTER);
		 titleItem.setListSymbol("");
		 header.add(titleItem);       
//		 add address
		 String TitleAddress = "Campus : NH-59, Gram-Sandalpur, Teh-Khategaon, Dist-Dewas (MP), Pin Code: 455339";
	      ListItem titleAddressItem = new ListItem(TitleAddress); 
	      titleAddressItem.setFontSize(11);
	 	titleAddressItem.setTextAlignment(TextAlignment.CENTER);
	 	titleAddressItem.setListSymbol("");
	      header.add(titleAddressItem);  
	      
	      Cell headerCell = new Cell();
	      headerCell.add(header);
	      headerCell.setWidthPercent(13);
	      headerCell.setBorder(Border.NO_BORDER);
	      table0.addCell(headerCell);
	      document.add(table0);
	      

		document.add(new Paragraph("\n"));

//		reciept
		String Reciept ="RECEIPT";
		Paragraph RecieptP = new Paragraph(Reciept);
		RecieptP.setTextAlignment(TextAlignment.CENTER);
		RecieptP.setUnderline();
		RecieptP.setFontSize(18);
		document.add(RecieptP);
		
		
		@SuppressWarnings("deprecation")
		Table table1 = new Table(2);
		String orderId = "Order ID : "+ orderID;
		table1.addCell(orderId);
		table1.getCell(0,0).setTextAlignment(TextAlignment.LEFT);
		table1.getCell(0,0).setBorder(Border.NO_BORDER);
		table1.getCell(0,0).setWidthPercent(40);

		
		String RecieptNo = "Reciept No : " + studentClass;
		table1.addCell(RecieptNo);
		table1.getCell(0,1).setTextAlignment(TextAlignment.RIGHT);
		table1.getCell(0,1).setBorder(Border.NO_BORDER);
		table1.getCell(0,1).setWidthPercent(40);

		
		document.add(table1);


		@SuppressWarnings("deprecation")
		Table table2 = new Table(2);
		String PaymentId = "Payment ID : "+ paymentID;
		table2.addCell(PaymentId);
		table2.getCell(0,0).setTextAlignment(TextAlignment.LEFT);
		table2.getCell(0,0).setBorder(Border.NO_BORDER);
		table2.getCell(0,0).setWidthPercent(40);

		
		 String pattern = "dd-MM-yyyy";
		String dateInString =new SimpleDateFormat(pattern).format(new Date());
		String Date = "Date : "+dateInString;
		table2.addCell(Date);
		table2.getCell(0,1).setTextAlignment(TextAlignment.RIGHT);
		table2.getCell(0,1).setBorder(Border.NO_BORDER);
		table2.getCell(0,1).setWidthPercent(40);

		
		document.add(table2);


//		document.add(new Paragraph("\n"));
		
		@SuppressWarnings("deprecation")
		Table table3 = new Table(2);
		table3.addCell("Received with Thanks From : ");
		table3.getCell(0,0).setBorder(Border.NO_BORDER);
		table3.getCell(0,0).setWidthPercent(30);
		table3.getCell(0,0).setFontSize(14);
		
       
		Cell name = new Cell();
		name.add(StudentFullName);
		name.setFontSize(14);
		 Border nameBorder = new SolidBorder(Color.BLACK, 1);
		 name.setBorder(Border.NO_BORDER);
		 name.setBorderBottom(nameBorder);
		 
		 table3.addCell(name);
        
		document.add(table3);
		
		
//        document.add(new Paragraph("\n"));
		
		@SuppressWarnings("deprecation")
		Table table4 = new Table(2);
		table4.addCell("Received By The Sum of Rupees : ");
		table4.getCell(0,0).setBorder(Border.NO_BORDER);
		table4.getCell(0,0).setWidthPercent(40);
		table4.getCell(0,0).setFontSize(14);
		
       
		Cell price = new Cell();
		price.add("1500/-(One Thousand Five Hundred Only)");
		price.setFontSize(14);
		price.setBorder(Border.NO_BORDER);
		 Border priceBorder = new SolidBorder(Color.BLACK, 1);
		 price.setBorderBottom(priceBorder);
		 
		 table4.addCell(price);
        
		document.add(table4);
		
//       document.add(new Paragraph("\n"));
		
		@SuppressWarnings("deprecation")
		Table table5 = new Table(2);
		table5.addCell("Pay By : ");
		table5.getCell(0,0).setBorder(Border.NO_BORDER);
		table5.getCell(0,0).setWidthPercent(8);
		table5.getCell(0,0).setFontSize(14);
		
       
		Cell pay = new Cell();
		pay.add("Online Payment SSISM Student App	");
		pay.setFontSize(14);
		pay.setBorder(Border.NO_BORDER);
		 Border payBorder = new SolidBorder(Color.BLACK, 1);
		 pay.setBorderBottom(payBorder);
		 table5.addCell(pay);
		 
		 document.add(table5);

		 
		 
		 document.add(new Paragraph("\n"));
		 @SuppressWarnings("deprecation")
			Table table6 = new Table(3);
			table6.addCell("RS.");
     		table6.getCell(0,0).setWidthPercent(3);
			table6.getCell(0,0).setFontSize(14);
			
			table6.addCell("1500/-");

			table6.getCell(0,1).setWidthPercent(8);
			table6.getCell(0,1).setFontSize(14);
			
		 
			StampImg.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			StampImg.setRelativePosition(0, 0, 0, 30);
			document.add(table6);
			

			document.add(StampImg).setTextAlignment(TextAlignment.RIGHT);
			String Auth = "Authorized Signature";
			Paragraph AuthP = new Paragraph(Auth);
			AuthP.setTextAlignment(TextAlignment.RIGHT);
			AuthP.setRelativePosition(0, 0, 0, 30);
			AuthP.setFontSize(14);
			document.add(AuthP);
		 
        
		
		 
//		document.toString();
		document.close();
		
		System.out.println("file closed");
		
		
	}
	
	
	
}
