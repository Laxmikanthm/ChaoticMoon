package com.appium.espn.businesslogic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.accelerators.AppiumActionEngine;
import com.taylormade.page.ProductsPage;

public class TaylorMadeWebLib extends AppiumActionEngine{

	public static final int LOWWAITTIME=20000;
	public static final int WAITTIME=50000;
	public static final int LONGWAITTIME=200000;
	public static String gErrMsg="";
	

	
	public static final By mmenu=By.xpath("//*[@id='navigation']/nav/div/div[1]");
	public static final By mproducts=By.xpath("//*[@id='navigation']/nav/ul/li[2]");
	public static final By miron=By.xpath("//*[@href='http://taylormadegolf.com/taylormade-irons/']");
	public static final By mfindtickets =By.xpath("//*[@href='https://www.ticketfly.com/purchase/event/767603/tfly']");
	public static final By mticketarrow=By.xpath("//*[@id='productRequestForms[0].quantity']");
	public static final By mnumberoftickets=By.xpath("//*[@class='android.widget.CheckedTextView' and @index ='2']");
	
	
	
	public static final By moretickets = By.cssSelector("add-more-ticket-types");

	/**
	 * Launches the ESPN Web Application
	 * @return
	 * @throws Throwable
	 */
	public static boolean launchUrl() throws Throwable
	{
		gErrMsg = "";		
		try {
			
			remoteWebDriverForAppium.get("http://taylormadegolf.com/");	
   	        return true;
	}
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Launch Failed";
			return false;
		}
		
	}
	
public static boolean searchProducts() throws Throwable{
		
		try{
			Thread.sleep(2000);
			//AppiumActionEngine.platformOverride = "Mobile";
			ProductsPage.Products_Page();
			if(!waitForElementPresent(ProductsPage.mmenu, "Search Icon", WAITTIME)){
				gErrMsg = "Search Link not visible" ;
				return false;
			}
			
			genclick(ProductsPage.mmenu, "SATURDAY Clearwater Festival");	
			
			//AppiumActionEngine.platformOverride = "Mobile";
			if(!waitForElementPresent(ProductsPage.mproducts, "find tickets", WAITTIME)){
			gErrMsg = "find tickets not visible" ;
			return false;
		    }
			
	      	genclick(ProductsPage.mproducts, "find tickets");
	      	
	    //  	AppiumActionEngine.platformOverride = "Mobile";
	      	if(!waitForElementPresent(ProductsPage.miron, "irons", WAITTIME)){
				gErrMsg = "irons not visible" ;
				return false;
			    }		
		      	genclick(ProductsPage.miron, " irons");
	      	
	    	
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Unable to sign in";
			return false;
		}
}


	public static boolean openFirefoxBrowser() throws Throwable{
		
		try{
			driver.manage().window().maximize();
			driver.get("http://www.taylormadegolf.com/");
			Thread.sleep(2000);
			
			  AppiumActionEngine.platformOverride = "Web";	
			  ProductsPage.Products_Page();
			  if(!waitForElementPresent(ProductsPage.mproducts, "Products", WAITTIME)){
				  gErrMsg = "Search Link not visible" ;
				  return false;
		}
		  	genclick(ProductsPage.mproducts, "searchfield");
		
		  	AppiumActionEngine.platformOverride = "Web";	
			if(!waitForElementPresent(ProductsPage.miron, "search button", WAITTIME)){
			gErrMsg = "search button not visible" ;
			return false;
		}		
		genclick(ProductsPage.miron, "Search Button");
		

		return true;
	} 
	catch (Exception e) {
		e.printStackTrace();
		gErrMsg="Unable to sign in";
		return false;
	}
		
	}
}