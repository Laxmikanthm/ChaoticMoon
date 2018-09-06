package com.taylormade.page;

import org.openqa.selenium.By;

import com.automation.accelerators.AppiumActionEngine;
import com.automation.support.ConfiguratorSupport;


public class ProductsPage extends AppiumActionEngine {

	public static By mmenu ;
	public static By mproducts ;
	public static By miron ;
	
	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");

	public static void  Products_Page()
	
	{
		if( AppiumActionEngine.platformOverride != null)
		{
			if(AppiumActionEngine.platformOverride.equalsIgnoreCase("Mobile"))
			{
				 mmenu = By.xpath("//*[@id='navigation']/nav/div/div[1]");
				 mproducts = By.xpath("//*[@id='navigation']/nav/ul/li[2]");
				 miron = By.xpath("//*[@href='http://taylormadegolf.com/taylormade-irons/']");
			}
			else if(AppiumActionEngine.platformOverride.equalsIgnoreCase("Web"))
			{
				 mproducts = By.xpath(".//*[@id='navigation']/nav/ul/li[2]/a/span");
				    miron = By.xpath(".//*[@id='header']/header/div[3]/div[1]/div/ul/li[4]/div/a/span");
			}
		}
		else
		{
			if(configProps.getProperty("platformType").equalsIgnoreCase("Mobile"))
			{
				 mmenu = By.xpath("//*[@id='navigation']/nav/div/div[1]");
				 mproducts = By.xpath("//*[@id='navigation']/nav/ul/li[2]");
				 miron = By.xpath("//*[@href='http://taylormadegolf.com/taylormade-irons/']");
			}
			else if(configProps.getProperty("platformType").equalsIgnoreCase("Web"))
			{
				// mmenu = By.xpath("//*[@id='navigation']/nav/div/div[1]");
				 mproducts = By.xpath(".//*[@id='navigation']/nav/ul/li[2]/a/span");
			    miron = By.xpath(".//*[@id='header']/header/div[3]/div[1]/div/ul/li[4]/div/a/span");
				
			}
		}
	}
	
}