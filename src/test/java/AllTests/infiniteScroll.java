package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import resources.base;

public class infiniteScroll extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;

	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("infinite scroll Test case","");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Infinite Scroll')]")).click();
		Thread.sleep(2000);
		long lenOfPage = (Long) ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);var lenOfPage=document.body.scrollHeight;return lenOfPage;");
		boolean scrolled = true;
		long lastCount = 0;
		System.out.println(lenOfPage);
		
		while(scrolled==true) {
			Thread.sleep(1500);
			lastCount=lenOfPage;
			lenOfPage = (Long) ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);var lenOfPage=document.body.scrollHeight;return lenOfPage;");
			System.out.println(lenOfPage);
			if (lastCount==lenOfPage) {
				scrolled=false;
			}

		}
		test.pass("infinite scroll checked");
		Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}