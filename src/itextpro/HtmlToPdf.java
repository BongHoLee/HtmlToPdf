package itextpro;

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
import com.sun.xml.internal.fastinfoset.sax.Properties;


public class HtmlToPdf{
	/** The HTML-string that we are going to convert to PDF. */
	public static final String HTML = "<h1>Test</h1><p>Hello Worlddsfsdfsdfsdf</p>";
	/** The target folder for the result. */
	public static final String TARGET = "c:\\Users\\USER\\Desktop\\";
	/** The path to the resulting PDF file. */
	public static final String DEST = String.format("%stest-01.pdf", TARGET);
	public static final String FONT = "c:\\externalJar\\malgun.ttf";
	
	Crawling crl;
	
	//최종적으로 경로 등을 받아서 pdf화 하는 메소드.
    public void createPdf(String html, String font, String dest) throws IOException {
    	
    	ConverterProperties properties = setProperties(html, font, dest);
    	PdfDocument pdf = setPdfWriter(dest);
        HtmlConverter.convertToPdf(html, pdf, properties);
        
        System.out.println("suc");
    }
    
    //defaultUrl을 읽어서 crawling ->  targetUrl/fileName 파일 생성 
    public static String makeFile(String defaultUrl, String targetUrl, String fileName) throws FileNotFoundException{
    	Document doc;
    	String returnValue = null;
    	
    	FileOutputStream out = new FileOutputStream("/Users/leebongho/Desktop/Hi/test1.html");
    	
    	try {
    		//크롤링 및 정규표현식을 이용한 html 정제 -> html 파일 생성.
    		
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

    
    // PDF 프로퍼티 세팅 메소드
    public static ConverterProperties setProperties(String html, String font, String dest) throws IOException {
    	
    	ConverterProperties properties = new ConverterProperties();
    	PdfDocument pdf = setPdfWriter(dest);
    	
    	// 1. pdf 사이즈 세팅
    	PageSize pageSize = pdf.getDefaultPageSize();
	
    	// 2. Font 설정 (한글 깨짐 방지)
    	FontProvider fontProvider = new DefaultFontProvider();
    	FontProgram fontProgram = FontProgramFactory.createFont(font);
    	fontProvider.addFont(fontProgram);
    	properties.setFontProvider(fontProvider);
    	
    	// 3. Media 쿼리 관련
    	MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.SCREEN);
    	mediaDeviceDescription.setWidth(pageSize.getWidth());
    	properties.setMediaDeviceDescription(mediaDeviceDescription);
    	
    	// 4. 세팅된 properties를 반환
    	return properties;
    }
    
    //PdfWriter 세팅 -> 목적지 경로에 타겟 파일(pdf) 생성을 위함(pdf Size 등 설정)
    public static PdfDocument setPdfWriter(String dest) throws FileNotFoundException {
    	PdfWriter writer = new PdfWriter(dest);
    	PdfDocument pdf = new PdfDocument(writer);
    	PageSize pageSize = PageSize.A4.rotate();
    	pdf.setDefaultPageSize(pageSize);
    	
    	return pdf;

    }



}
