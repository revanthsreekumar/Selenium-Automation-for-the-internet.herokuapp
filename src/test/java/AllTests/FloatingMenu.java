package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class FloatingMenu extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;
	private static final String SCRIPT = "arguments[0].scrollIntoView();";

	@BeforeTest
	public static void initialize() throws IOException {
		ExtentHtmlReporter spark = new ExtentHtmlReporter(MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("Floating Menu Test case","");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Floating Menu')]")).click();
		Thread.sleep(2000);
        WebElement menu=driver.findElement(By.id("menu"));

        List<WebElement> p= driver.findElements(By.cssSelector(".scroll.large-10.columns.large-centered p"));
        for ( WebElement pNumber: p ) {
            ((JavascriptExecutor) driver).executeScript(SCRIPT, pNumber);
            if(menu.isDisplayed() ==true)
            test.pass("Menu is Floating");
            else
            	test.fail("Menu is not Floating");
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