package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



import resources.base;

public class dynamicContent extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;
	static String urlAddr;


	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("Dynamic Content Test Case","");
		driver =initializeDriver();
		urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Dynamic Content')]")).click();
		//To get direct descendants with 'row' class of div elements
		List<WebElement> dynamicContent = driver.findElements(By.cssSelector("div#content>div.row"));
		//To get the text from the 2nd row of dynamic content
		System.out.println(dynamicContent.get(0).findElement(By.cssSelector("div:nth-child(2)")).getText());		
		Thread.sleep(6000);
	}
	
	
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}