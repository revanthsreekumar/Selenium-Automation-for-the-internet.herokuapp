package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class jqueryUI extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;

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
		driver.findElement(By.xpath("//a[contains(text(),'JQuery UI Menus')]")).click();
		Thread.sleep(2000);
        Actions hover = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> enableButton = new ArrayList<>(driver.findElements(By.cssSelector(".ui-menu-icon.ui-icon.ui-icon-carat-1-e")));
        WebElement element = enableButton.get(1);

        hover.moveToElement(element).perform();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ui-id-4")));
        WebElement download = driver.findElement(By.cssSelector("#ui-id-4"));
        hover.moveToElement(download).perform();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ui-id-8")));
        WebElement pdf = driver.findElement(By.cssSelector("#ui-id-8"));
        hover.moveToElement(pdf).click().perform();
		test.pass("JQuery UI Menus checked");
		Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}