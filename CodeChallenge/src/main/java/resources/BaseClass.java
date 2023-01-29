package resources;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilities.ReadConfig;

public class BaseClass {
	
	public ReadConfig readconfig = new ReadConfig();
    public WebDriver driver;
    public WebDriverWait wait;
    
    public WebDriver initialzeDriver()
    {
    	
		System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		ChromeOptions options = new ChromeOptions();    
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);
    	
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
   
    	return driver;
    }

    public void teardownDriver()
    {
    	driver.quit();
    }
   /* 
    public String getScreenShotPath(WebDriver driver) throws IOException
    {
    	TakesScreenshot ts=(TakesScreenshot) driver;
    	File source =ts.getScreenshotAs(OutputType.FILE);
    	String destinationFile = System.getProperty("user.dir")+"\\screenshot\\Screenshot.png";
    	FileUtils.copyFile(source,new File(destinationFile));
    	return destinationFile;
    }*/
    
}
