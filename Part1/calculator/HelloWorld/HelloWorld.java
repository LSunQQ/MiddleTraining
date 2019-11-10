public class HelloWorld {
	private String str;
	
	public static void main(String[] args) {
		HelloWorld testHelloWorld = new HelloWorld();
		testHelloWorld.setStr();
		System.out.println(testHelloWorld.str);
	}
	
	public void setStr() {
		str = "Hello World!";
	}
}
