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

public class AddRemoveElements extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;
	//add elements
	public static  void validateAddElements(int noOfClicks, WebDriver driver) throws InterruptedException {
		WebElement addElementBtn = driver.findElement(By.xpath("//button[@onclick='addElement()']"));
		for(int i=0;i<noOfClicks;i++) {
			addElementBtn.click();
			test.pass("Added Element button clicked");
			Thread.sleep(1000);
		}		

	}
	//remove elements
	public static  void validateRemoveElements(int noOfClicks, WebDriver driver) throws InterruptedException {
		List<WebElement> removeElementBtns = driver.findElements(By.cssSelector("button.added-manually"));
		for(int i=0;i<removeElementBtns.size();i++) {
			removeElementBtns.get(i).click();
			test.pass("Delete button clicked");
			Thread.sleep(1000);
		}		
	}


	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("Add Remove Elements Test","items added and removed");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException  {
		//Add/Remove Elements
		WebElement pageLink = driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]"));
		pageLink.click();
		int n=3;
		validateAddElements(n, driver);//Add elements
		test.pass("Added "+n+" Elements");
		validateRemoveElements(n, driver); //Remove elements
		test.pass("Removed "+n+" elements");
		System.out.println("Test executed");

	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}
		

		

	


