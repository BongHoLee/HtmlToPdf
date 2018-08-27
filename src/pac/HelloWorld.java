package pac;

/*
 * Copyright 2016-2017, iText Group NV.
 * This example was created by Bruno Lowagie.
 * It was written in the context of the following book:
 * https://leanpub.com/itext7_pdfHTML
 * Go to http://developers.itextpdf.com for more info.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.css.media.MediaDeviceDescription;
import com.itextpdf.html2pdf.css.media.MediaType;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;

/**
 * Converts a simple Hello World HTML String to a PDF document.
 */
public class HelloWorld {

	/** The HTML-string that we are going to convert to PDF. */
	public static final String HTML = "<h1>Test</h1><p>Hello Worlddsfsdfsdfsdf</p>";
	/** The target folder for the result. */
	public static final String TARGET = "/Users/leebongho/Desktop/Hi/";
	/** The path to the resulting PDF file. */
	public static final String DEST = String.format("%stest-01.pdf", TARGET);
	
	public static final String SRC = String.format("%stest1.html", TARGET);
	
	public static final String FONT = "/Users/leebongho/Desktop/font/malgun.ttf";
	
	/**
	 * The main method of this example.
	 *
	 * @param args no arguments are needed to run this example.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    public static void main(String[] args) throws IOException {
        File file = new File(TARGET);
        file.mkdirs();
        HelloWorld he = new HelloWorld();
        he.createPdf(Fileing(), FONT,DEST, SRC, TARGET);
    }
    
    /**
     * Creates the PDF file.
     *
     * @param html the HTML as a String value
     * @param dest the path of the resulting PDF
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void createPdf(String html, String font, String dest, String src, String base) throws IOException {
    	PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setTagged();
        PageSize pageSize = PageSize.A4.rotate();
        pdf.setDefaultPageSize(pageSize);
        
    	ConverterProperties properties = new ConverterProperties();
        FontProvider fontProvider = new DefaultFontProvider();
        FontProgram fontProgram = FontProgramFactory.createFont(font);
        fontProvider.addFont(fontProgram);
        properties.setFontProvider(fontProvider);
        //properties.setBaseUri(html);
        properties.setBaseUri(base);
        
        MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.SCREEN);
       	mediaDeviceDescription.setWidth(pageSize.getWidth());
       	properties.setMediaDeviceDescription(mediaDeviceDescription);
        HtmlConverter.convertToPdf(new FileInputStream(src), pdf, properties);
        System.out.println("suc");
    }
    public static String Cralwing() throws FileNotFoundException{
    	Document doc;
    	String defaultUrl = "http://www.google.com/";
    	String returnValue = null;
    	
    	FileOutputStream out = new FileOutputStream("/Users/leebongho/Desktop/Hi/test1.txt");
    	
    	try {
			doc = Jsoup.connect("https://www.google.co.kr/search?q=java&oq=java&aqs=chrome..69i57j69i60l4j69i65.1574j0j7&sourceid=chrome&ie=UTF-8").get();
			//System.out.println(doc.html());
			returnValue = doc.html();
			returnValue= returnValue.replaceAll("src=\"/", "src=\"" + defaultUrl);
			returnValue= returnValue.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
			returnValue = returnValue.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
			out.write(returnValue.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return returnValue;
    }
    
    public static String Fileing() throws FileNotFoundException{
    	Document doc;
    	String defaultUrl = "http://www.google.com/";
    	String returnValue = null;
    	
    	FileOutputStream out = new FileOutputStream("/Users/leebongho/Desktop/Hi/test1.html");
    	
    	try {
			doc = Jsoup.connect("https://www.google.co.kr/search?q=java&oq=java&aqs=chrome..69i57j69i60l4j69i65.1574j0j7&sourceid=chrome&ie=UTF-8").get();
			//System.out.println(doc.html());
			returnValue = doc.html();
			returnValue= returnValue.replaceAll("src=\"/", "src=\"" + defaultUrl);
			returnValue= returnValue.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
			returnValue= returnValue.replaceAll("(?is)<noscript.*?>.*?</noscript>", ""); // remove javascript
			returnValue = returnValue.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
			out.write(returnValue.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "/Users/leebongho/Desktop/Hi/test1.html";
    }
}
