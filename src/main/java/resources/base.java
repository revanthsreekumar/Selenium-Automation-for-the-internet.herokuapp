package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class base {

	public static WebDriver driver;
	public static Properties prop;
	public static String projectPath = System.getProperty("user.dir");
	public static WebDriver initializeDriver() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(projectPath+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			//chrome
			
			//System.out.println("ProjectPath:"+projectPath);
			System.setProperty("webdriver.chrome.driver", projectPath+"\\Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			//driver.get("https://www.technopark.org/");
		}
		else if(browserName.equals("firefox"))
		{
			//firefox
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	public void getScreenShotPath(String testName, WebDriver driver) throws IOException{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile= System.getProperty("user.dir")+"\\reports\\"+testName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		
	}
}