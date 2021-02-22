package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



import resources.base;

public class contextMenu extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;
	

	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("context Menu Test Case","");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		WebElement contextMenu = driver.findElement(By.xpath("//a[contains(text(),'Context Menu')]"));
		contextMenu.click();
		Thread.sleep(2000);
		WebElement contextBox = driver.findElement(By.id("hot-spot"));
		Actions actions = new Actions(driver);
		actions.moveToElement(contextBox);
		actions.contextClick(contextBox).perform();
		Thread.sleep(2000);
		Alert popup=driver.switchTo().alert();
        popup.accept();
		Thread.sleep(2000);	
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}