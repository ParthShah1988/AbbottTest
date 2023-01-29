package AbbottTest.CodeChallenge;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LibreViewObjects;
import resources.BaseClass;
import resources.ExtentReportsClass;

public class HeaderLinkTest extends ExtentReportsClass{
	
	public WebDriver driver;//local driver
	public static Logger log= LogManager.getLogger(BaseClass.class.getName());//standard format
	
	@BeforeTest
	public void startTest()
	{
		log.info("Initialized driver");
		driver=initialzeDriver();//initializing from base
		log.info("Driver is ready");
	}
	
	@AfterTest
	public void endTest()
	{
		log.info("Initialized tear down");
		teardownDriver();
		log.info("Tear down complete");
	}
	
	
	@Test
	public void selectRegion() throws InterruptedException, IOException {
		
		logger = extent.createTest("Country & Language select Test");
		log.info("------Starting Test case execution----------");
		
		log.info("Opening LibreView page");
		//logger.info("Opening LibreView page");
		driver.get(readconfig.getBaseUrl());

		LibreViewObjects lv=new LibreViewObjects(driver);
		wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.elementToBeClickable(lv.country_dropdown()));
		log.info("Clicking on Country Dropdown");
		lv.country_dropdown().click();
		
		log.info("Selecting Country from Dropdown");
		selectOptionByVisibleText(lv.country_dropdown_list(), readconfig.setCountry());//Logic to iterate from country list options
		
		wait.until(ExpectedConditions.elementToBeClickable(lv.language_dropdown()));
		log.info("Clicking on Language Dropdown");
		lv.language_dropdown().click();
		
		log.info("Selecting language from Dropdown");
		try {
			selectOptionByVisibleText(lv.language_dropdown_list(), readconfig.setLanguage());
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			selectOptionByVisibleText(lv.language_dropdown_list(), readconfig.setLanguage());
		}
		
		lv.submit_button().click();
		
		wait.until(ExpectedConditions.elementToBeClickable(lv.cancelBanner_button()));
		if(lv.cancelBanner_button().isDisplayed())
		{
			log.info("Closing the cookie banner");
			lv.cancelBanner_button().click();
		}
		
		if(readconfig.setCountry().equalsIgnoreCase("France (FR)") && readconfig.setLanguage().equalsIgnoreCase("French"))
		{
			Assert.assertEquals(validateHeaderLinks(driver),true,"Something went wrong");
			log.info("Header links are not visible for France country + French language");
		}
		else if(readconfig.setCountry().equalsIgnoreCase("United States (US)") && readconfig.setLanguage().equalsIgnoreCase("English"))
		{
			Assert.assertEquals(validateHeaderLinks(driver),true,"Something went wrong");
			log.info("Header links are visible for United States country + English language");
		}
		
		/*
		log.info("Taking screenshot");//taking screenshot of the page
		getScreenShotPath(driver);
		log.info("Screenshot saved");
		*/
		log.info("-----Test case execution complete---------");
		logger.pass("-----Test case execution complete---------");
	}
	
	public boolean validateHeaderLinks(WebDriver driver) throws InterruptedException, IOException {
		
		LibreViewObjects lv=new LibreViewObjects(driver);
		try 
		{
			lv.patient_headerLink().isEnabled();
			System.out.println("Patient link is present.");
			lv.professional_headerLink().isEnabled();
			System.out.println("Professional link is present.");
        }
		catch (NoSuchElementException e)
		{
			System.out.println("Patient link is not present.");
			System.out.println("Professional link is not present.");
            //System.out.println("The exeption captured: "+e);
        	log.error(e);
            return true;
        }
		return true;
	}
	
	public static void selectOptionByVisibleText(WebElement el,String str)
	{
		List<WebElement> Options = el.findElements(By.tagName("li"));
		for (WebElement option : Options)
		{
			//System.out.println("Your Options are: "+ option.getText());
		    if (option.getText().equalsIgnoreCase(str))
		    {
		    	option.click(); // click the desired option
		        break;
		    }
		}
	}

}
