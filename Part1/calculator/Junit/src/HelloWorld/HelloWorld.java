import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloWorld {
	private String str;
	
	public static void main(String[] args) {
		HelloWorld testHelloWorld = new HelloWorld();
		testHelloWorld.setStr();
		System.out.println(testHelloWorld.str);
	}
	
	public void setStr() {
		str = "Hello World";
	}
	
	@Test
	public void testhelloWorld() {
		HelloWorld helloworld = new HelloWorld();
		helloworld.setStr();
		assertEquals("Hello World", helloworld.str);
	}
}
