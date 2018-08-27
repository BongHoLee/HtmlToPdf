package pac;

/*
 * Copyright 2016-2017, iText Group NV.
 * This example was created by Bruno Lowagie.
 * It was written in the context of the following book:
 * https://leanpub.com/itext7_pdfHTML
 * Go to http://developers.itextpdf.com for more info.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.itextpdf.html2pdf.HtmlConverter;

/**
 * Converts a simple HTML file to PDF using File objects
 * as arguments for the convertToPdf() method.
 */
public class ItextTest2 {

	/** The Base URI of the HTML page. */
	public static final String BASEURI = "/Users/leebongho/Desktop/";
	/** The path to the source HTML file. */
	public static final String SRC = String.format("%snaver.html", BASEURI);
	/** The target folder for the result. */
	public static final String TARGET = "/Users/leebongho/Desktop/";
	/** The path to the resulting PDF file. */
	public static final String DEST = String.format("%stest-03.pdf", TARGET);
	
	/**
	 * The main method of this example.
	 *
	 * @param args no arguments are needed to run this example.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    public static void main(String[] args) throws IOException {
        File file = new File(TARGET);
        file.mkdirs();
        new ItextTest2().createPdf(BASEURI, SRC, DEST);
        //Cralwing();
    }

    /**
     * Creates the PDF file.
     *
     * @param baseUri the base URI
     * @param src the path to the source HTML file
     * @param dest the path to the resulting PDF
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void createPdf(String baseUri, String src, String dest) throws IOException {
        HtmlConverter.convertToPdf(new File(src), new File(dest));
    }
    
    public static String Cralwing(){
    	Document doc;
    	String defaultUrl = "https://www.google.co.kr/search?q=naver&oq=naver&aqs=chrome..69i57j69i60j35i39l2j0l2.4643j0j4&sourceid=chrome&ie=UTF-8";
    	String returnValue = null;
    	
    	
    	try {
			doc = Jsoup.connect(defaultUrl).get();
			System.out.println(doc.html());
			returnValue = doc.html();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return returnValue;
    }
}
