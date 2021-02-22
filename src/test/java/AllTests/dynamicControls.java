package AllTests;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class dynamicControls extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;
	static String urlAddr;
public static void TestTextBox() throws InterruptedException{
	WebElement input = driver.findElement(By.cssSelector("#input-example input"));
    WebElement enableButton = driver.findElement(By.cssSelector("#input-example button"));
    if (input.isEnabled()) {
        input.sendKeys("HI");
    } else {
        enableButton.click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("message")));
        input.sendKeys("Write after click on enable button");
        enableButton.click();
        
    }
}
public static void TestCheckBox() throws InterruptedException{
	WebElement inputCheck = driver.findElement(By.xpath("//input[@type='checkbox']"));
    WebElement removeButtonCheck = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
    if (inputCheck.isDisplayed()) {
    	//System.out.println("inside"+inputCheck.isSelected());
    	inputCheck.click();
    	Thread.sleep(3000);
    	
    }
    //remove check box
    removeButtonCheck.click();
    Thread.sleep(3000);
    WebDriverWait wait = new WebDriverWait(driver, 7);
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("message")));
    //add checkbox
    removeButtonCheck.click();
}

	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("Dynamic Controls Test Case","");
		driver =initializeDriver();
		urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Dynamic Controls')]")).click();
        Thread.sleep(3000);
        //Test Text box
        TestTextBox();
        test.pass("Test Box tested");
        Thread.sleep(3000);
        //Test check box
        TestCheckBox();
        test.pass("check box tested");
        Thread.sleep(3000);
        
	}
	
	
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}