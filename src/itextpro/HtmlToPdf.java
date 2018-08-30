package itextpro;

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
import com.sun.xml.internal.fastinfoset.sax.Properties;

/*
 * makeFile -> createPdf -> 
 * */

public class HtmlToPdf{

	
	private String dest;
	private String font;
	private String filepath;
	
	UrlToHtml uth;
	

    public HtmlToPdf(UrlToHtml uth, String font, String dest) {
		super();
		this.uth = uth;
		this.filepath = uth.getFilePath();
		createPdf(filepath, font, dest);
	}

	// PDF 프로퍼티 세팅 및 pdf 생성
    public void createPdf(String file, String font, String dest) {
    	
    	
    	ConverterProperties properties = new ConverterProperties();
    	PdfWriter writer;
		try {
			writer = new PdfWriter(dest);
	    	PdfDocument pdf = new PdfDocument(writer);
	    	
	    	
	    	// 1. pdf 사이즈 세팅
	    	PageSize pageSize = new PageSize(1000, 1700);
	    	pdf.setDefaultPageSize(pageSize);
		
	    	// 2. Font 설정 (한글 깨짐 방지)
	    	FontProvider fontProvider = new DefaultFontProvider();
	    	FontProgram fontProgram = FontProgramFactory.createFont(font);
	    	fontProvider.addFont(fontProgram);
	    	properties.setFontProvider(fontProvider);
	    	
	    	// 3. Media 쿼리 관련
	    	MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.SCREEN);
	    	mediaDeviceDescription.setWidth(pageSize.getWidth());
	    	properties.setMediaDeviceDescription(mediaDeviceDescription);
	    	
	    	
	    	HtmlConverter.convertToPdf(new FileInputStream(file), pdf, properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }
    

    



}
