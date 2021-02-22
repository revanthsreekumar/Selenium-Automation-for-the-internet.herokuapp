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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import resources.base;

public class BrokenImage extends base{
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;
	private static int InvalidImageCount=0;
	
	private static void validateImage(WebElement image) throws ClientProtocolException, IOException {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(image.getAttribute("src"));
		HttpResponse response = client.execute(getRequest);
		if(response.getStatusLine().getStatusCode()!=200) {
			InvalidImageCount++;
		}		
	}

	@BeforeTest
	public static void initialize() throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExtentHtmlReporter spark = new ExtentHtmlReporter(projectPath+"\\extentReports\\"+MethodHandles.lookup().lookupClass()+"ExtentReports.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		test =  extent.createTest("Broken Image Test","gives count of broken images");
		driver =initializeDriver();
		String urlAddr = prop.getProperty("url");
		//System.out.println(urlAddr);
		test.log(Status.INFO, "Starting Test Case");
		driver.get(urlAddr); //URL loaded
		test.pass("navigated to URL");
	}
	@Test
	public static void runTest() throws InterruptedException, ClientProtocolException, IOException  {
		driver.findElement(By.xpath("//a[contains(text(),'Broken Images')]")).click();
		List<WebElement> imageElements = driver.findElements(By.cssSelector("div.example img"));
		for(WebElement image:imageElements) {
			validateImage(image);			
		}

		System.out.println("No. of broken images are : "+InvalidImageCount);
	}
	@AfterTest
	public void tearDown(){
		test.log(Status.INFO, "Test Case Ended");
		extent.flush();
		driver.close();
	}
}