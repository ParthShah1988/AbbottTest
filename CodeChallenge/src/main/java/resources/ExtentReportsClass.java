package resources;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestResult;

import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.InteractiveReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportsClass extends BaseClass{

public ExtentSparkReporter spark;
public ExtentReports extent;
public ExtentTest logger;

@BeforeTest
public void startReport() {
         // Create an object of Extent Reports
	extent = new ExtentReports();
	
	spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Extent-Report/STMExtentReport.html");
	extent.attachReporter(spark);
	extent.setSystemInfo("Company Name", "Abbott");
	extent.setSystemInfo("Product", "LibreView");
	extent.setSystemInfo("Tester Name", "Parth Shah");
	spark.config().setDocumentTitle("Abbott Test");
	                // Name of the report
	spark.config().setReportName("LibreView Test result");
	                // Dark Theme
	spark.config().setTheme(Theme.STANDARD);

}
//This method is to capture the screenshot and return the path of the screenshot.
public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	// after execution, you could see a folder "FailedTestsScreenshots" under src folder
	String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	return destination;
}

@AfterMethod
public void getResult(ITestResult result) throws Exception{

	String screenshotPath = getScreenShot(driver, result.getName());
	if(result.getStatus() == ITestResult.FAILURE){
		//MarkupHelper is used to display the output in different colors
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	
		//To add it in the extent report
		logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
	}
	else if(result.getStatus() == ITestResult.SKIP){
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
	}
	else if(result.getStatus() == ITestResult.SUCCESS)
	{
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		logger.pass("Test Case Passed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));//To add it in the extent report
	}
	driver.quit();
}
 
@AfterTest
public void endReport() {
	extent.flush();
	}
}
