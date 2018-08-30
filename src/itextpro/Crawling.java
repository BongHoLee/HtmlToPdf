package itextpro;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawling {
	
    //defaultUrl을 읽어서 crawling ->  targetUrl/fileName 파일 생성 
    public static String makeFile(String defaultUrl, String targetUrl, String fileName) throws FileNotFoundException{
    	Document doc;
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
