package itextpro;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UrlToHtml {
	
	// 크롤링을 하기 위한 URL
	private String url;
	
	// 생성된 html 파일을 저장하는 위치
	private String target;
	
	// 크롤링 결과인 HTML 문자열
	private String returnValue;
	
	//html 파일의 이름
	private String filename;
	
	//html 파일의 전체 경로
	private String filePath;

	
	//생성자
	public UrlToHtml(String url, String filename, String target) {
		super();
		this.filename = filename;
		this.target = target;
		this.url = url;
		
		this.crawling(this.url);
		this.makeFile(this.filename, this.target);
	}
	
	//해당 경로에 파일 생성 후 정제된 HTML 문자열  write
	public void makeFile(String filename, String target){
		this.filePath = target + filename;
		FileOutputStream out;
		
		try {
			out = new FileOutputStream(filePath);
			out.write(this.returnValue.getBytes());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//url에 대한 크롤링 및 html 정제 메소드
	public void crawling(String url) {
		Document doc;
		this.returnValue = null;
		
		try {
			//url을 이용한 크롤링
			doc = Jsoup.connect(url).get();
			
			//크롤링한 html 문자열을 returnValue에 저장
			returnValue = doc.html();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//html 태그 정제
		returnValue= returnValue.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
		returnValue= returnValue.replaceAll("(?is)<noscript.*?>.*?</noscript>", ""); // remove javascript
		returnValue= returnValue.replaceAll("(?is)<iframe.*?>.*?</iframe>", ""); // remove javascript
		returnValue= returnValue.replaceAll("(?is)<div class=\"fbar\".*?>.*?</div>", ""); // remove javascript
		returnValue= returnValue.replaceAll("(?is)id=\"fbar\".*?>.*?</div>", ""); // remove javascript
		returnValue= returnValue.replaceAll("(?is)class=\"action-menu-.*?\"", ""); // remove javascript
		returnValue= returnValue.replaceAll("(?is)<svg.*?>.*?</svg>", ""); // remove javascript
		returnValue = returnValue.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
		
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public String getReturnValue() {
		return returnValue;
	}




	public String getFilePath() {
		return filePath;
	}
	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
