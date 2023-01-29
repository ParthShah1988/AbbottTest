package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

Properties pro;
	
	public ReadConfig() 
	{
		File src = new File("./config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("The exception is: "+ e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getBaseUrl()
	{
		String url = pro.getProperty("baseUrl");
		return url;
	}
	
	public String getChromePath()
	{
		String chromePath = pro.getProperty("chromepath");
		return chromePath;
	}
	
	public String setCountry()
	{
		String countrySelected = pro.getProperty("country");
		return countrySelected;
	}
	
	public String setLanguage()
	{
		String languageSelected = pro.getProperty("language");
		return languageSelected;
	}
	
}
