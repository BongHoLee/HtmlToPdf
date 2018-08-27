package pac;

public class ReplaceTest {
	public static void main(String[] args) {
		String test = "<script>hihihihi IM the best of the this!</script><script>";
		test = test.replaceAll("<script>", "/");
		
		String url = "http://www.google.com/";
		String test2 = "<img src=\"/hihi/\" dsdfsdfsdf";
		System.out.println(test2);
		
		test2= test2.replaceAll("src=\"/", "src=\"" + url);
		System.out.println(test2);
		//System.out.println(test);
	}

}
