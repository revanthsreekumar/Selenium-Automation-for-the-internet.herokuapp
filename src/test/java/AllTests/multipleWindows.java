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

public class multipleWindows extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;

	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("Multiple Windows Test case","");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Multiple Windows')]")).click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String hrefUrl = driver.findElement(By.cssSelector(".example a")).getAttribute("href");
        driver.findElement(By.cssSelector(".example a")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        String firstWindow = driver.getWindowHandle();
        for ( String newWindow : driver.getWindowHandles() ) {
            driver.switchTo().window(newWindow);
        }
        driver.getCurrentUrl();
        driver.switchTo().window(firstWindow);
		test.pass("Multiple Windows checked");
		Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.quit();
	}
}