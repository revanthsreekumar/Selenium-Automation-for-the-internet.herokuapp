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

public class DisappearingElements extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;


	@BeforeTest
	public static void initialize() throws IOException {
		ExtentHtmlReporter spark = new ExtentHtmlReporter(MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("Disappearing Elements Test Case","Disappearing Elements number checked");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Disappearing Elements')]")).click();
		List<WebElement> elements=driver.findElements(By.cssSelector("ul li"));
		int sizeBeforeRefresh =elements.size();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		List<WebElement> elementsAfterRefresh=driver.findElements(By.cssSelector("ul li"));
		int sizeAfterRefresh=elementsAfterRefresh.size();

		if(sizeBeforeRefresh==sizeAfterRefresh){
			System.out.println("The number of elements are the same after refresh");
			System.out.println("Total number of elements before Refresh are: "+sizeBeforeRefresh);
			System.out.println("Total number of elements after Refresh are: "+sizeBeforeRefresh);
		}else {
			System.out.println("The number of elements changed");
			System.out.println("Total number of elements before Refresh are: "+sizeBeforeRefresh);
			System.out.println("Total number of elements after Refresh are: "+sizeBeforeRefresh);
		}
		Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}