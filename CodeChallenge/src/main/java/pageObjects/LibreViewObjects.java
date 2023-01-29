package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LibreViewObjects {
	
public WebDriver driver;//this.driver -> points to driver variable declared here
	
	public LibreViewObjects(WebDriver driver) {
		this.driver=driver;
	}
	
	By header_langSelect=By.xpath("(//span[@id='languageText'])[1]");
	By footer_langSelect=By.xpath("(//span[@id='languageText'])[2]");
	
	By btn_countryDropdown=By.cssSelector("#countryDropdownBtn");
	By btn_languageDropdown=By.cssSelector("#languageDropdownBtn");
	
	By countryDropdownList=By.cssSelector("#countryDropdown");
	By languageDropdownList=By.cssSelector("#languageDropdown");
	
	By btn_Submit=By.cssSelector("#modalSubmit");
	By btn_Cancel= By.xpath("//img[@alt='close banner']");
	
	By link_Patient= By.xpath("//a[@id='patHeaderLink']");
	By link_Professional= By.xpath("//a[@id='proHeaderLink']");
	
	
	public WebElement headerLanguageSelect()
	{
		return driver.findElement(header_langSelect);
	}
	
	public WebElement footerLanguageSelect()
	{
		return driver.findElement(footer_langSelect);
	}
	
	public WebElement country_dropdown()
	{
		return driver.findElement(btn_countryDropdown);
	}
	
	public WebElement country_dropdown_list()
	{
		return driver.findElement(countryDropdownList);
	}
	
	public WebElement language_dropdown()
	{
		return driver.findElement(btn_languageDropdown);
	}
	
	public WebElement language_dropdown_list()
	{
		return driver.findElement(languageDropdownList);
	}
	
	public WebElement submit_button()
	{
		return driver.findElement(btn_Submit);
	}
	
	public WebElement cancelBanner_button()
	{
		return driver.findElement(btn_Cancel);
	}
	
	public WebElement patient_headerLink()
	{
		return driver.findElement(link_Patient);
	}
	
	public WebElement professional_headerLink()
	{
		return driver.findElement(link_Professional);
	}
	

}
