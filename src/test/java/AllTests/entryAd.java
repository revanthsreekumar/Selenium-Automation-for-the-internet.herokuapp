package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
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

public class entryAd extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;


	@BeforeTest
	public static void initialize() throws IOException {
		ExtentHtmlReporter spark = new ExtentHtmlReporter(MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("entry ad Test Case","");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Entry Ad')]")).click();
		Thread.sleep(3000);
		String currentWindow = driver.getWindowHandle();
        for ( String adwindow : driver.getWindowHandles() ) {
            if (adwindow != currentWindow) {
                driver.switchTo().window(adwindow);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".modal-title")));
                System.out.println(driver.findElement(By.cssSelector(".modal-title")).getText());
                Thread.sleep(3000);
                driver.findElement(By.cssSelector(".modal-footer p")).click();
            }
        }
        driver.switchTo().window(currentWindow);
		Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}