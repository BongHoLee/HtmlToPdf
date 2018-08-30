package itextpro;

public class MainClass {
	/** The HTML-string that we are going to convert to PDF. */
	public static final String URL = "https://www.google.co.kr/search?q=java&rlz=1C1SQJL_koKR810KR810&oq=java&aqs=chrome..69i57j69i60j0l4.2516j1j4&sourceid=chrome&ie=UTF-8";
	/** The target folder for the result. */
	public static final String TARGET = "c:\\Users\\USER\\Desktop\\";
	/** The path to the resulting PDF file. */
	public static final String DEST = String.format("%stest-01.pdf", TARGET);
	public static final String FONT = "c:\\externalJar\\malgun.ttf";
	public static final String FILE_NAME = "result.html";

	
	public static void main(String[] args) {
		UrlToHtml uth = new UrlToHtml(URL, FILE_NAME, TARGET);
		HtmlToPdf htp = new HtmlToPdf(uth, FONT, DEST);
		System.out.println("SUECESS");
	}	
	
	
	
	

}
