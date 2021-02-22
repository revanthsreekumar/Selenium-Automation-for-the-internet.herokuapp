package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import resources.base;

public class javascriptAlerts extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;
	public static void waitForAlertToPresent(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());		
	}

	public static void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("JQuery UI Menus Test case","");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'JavaScript Alerts')]")).click();
		Thread.sleep(2000);
		//JS Alert
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		waitForAlertToPresent(driver);
		Thread.sleep(3000);
		acceptAlert(driver);

		//JS Confirm
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		waitForAlertToPresent(driver);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);
		alert.dismiss();

		//JS Prompt -- when sendKeys to the alert its not visible but gets recorded, refer this -  https://bugs.chromium.org/p/chromedriver/issues/detail?id=1120
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
		waitForAlertToPresent(driver);
		alert = driver.switchTo().alert();
		alert.sendKeys("hello");
		Thread.sleep(3000);
		alert.accept();
		System.out.println(driver.findElement(By.xpath("//p[@id='result']")).getText());

		test.pass("Javascript Alerts checked");
		Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}