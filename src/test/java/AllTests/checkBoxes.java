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

public class checkBoxes extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;


	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("checkBox Test Case","");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Checkboxes')]")).click();
		List<WebElement> checkboxElements = driver.findElements(By.cssSelector("#checkboxes input[type='checkbox']"));
		//To select the checkbox which is not selected
		System.out.println("no of checkboxes are : "+checkboxElements.size());
		Thread.sleep(1000);
		test.pass("No of checkboxes are "+checkboxElements.size());
		for(WebElement checkbox:checkboxElements) {
			System.out.println("is this selected : "+checkbox.isSelected());
			if(!checkbox.isSelected()) {
				checkbox.click();
				Thread.sleep(1000);
				test.pass("checkbox checked");
			}
		}		
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}