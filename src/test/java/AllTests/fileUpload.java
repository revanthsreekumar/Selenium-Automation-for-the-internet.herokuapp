package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
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

public class fileUpload extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;


	@BeforeTest
	public static void initialize() throws IOException {
		ExtentHtmlReporter spark = new ExtentHtmlReporter(MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("File upload Test case","files are uploaded");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'File Upload')]")).click();
		Thread.sleep(2000);
		WebElement uploadElement = driver.findElement(By.xpath("//input[@id='file-upload']"));
		uploadElement.sendKeys("C:\\Users\\Revanth\\Downloads\\Test1.txt");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='file-submit']")).click();
		test.pass("file uploaded");
		Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}