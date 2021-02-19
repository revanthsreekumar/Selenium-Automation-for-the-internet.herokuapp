package AllTests;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import resources.base;

public class sample extends base{
	public static WebDriver driver;

	public static void main(String[] args) throws IOException {

		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		driver.get(urlAddr);
		

	}

}

