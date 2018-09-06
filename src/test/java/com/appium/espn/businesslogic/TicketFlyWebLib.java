package com.appium.espn.businesslogic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.accelerators.AppiumActionEngine;

public class TicketFlyWebLib extends AppiumActionEngine{

	public static final int LOWWAITTIME=20000;
	public static final int WAITTIME=50000;
	public static final int LONGWAITTIME=200000;
	public static String gErrMsg="";
	
	//public static final By tickets=By.xpath("//*[@id='rsvp-event-685583']/div[2]/div[1]/a");
	
	public static final By msearchfield=By.xpath("//*[@class='textform']");
	public static final By mfindtickets =By.xpath("//*[@href='https://www.ticketfly.com/purchase/event/767603/tfly']");
	public static final By mticketarrow=By.xpath("//*[@id='productRequestForms[0].quantity']");
	public static final By mnumberoftickets=By.xpath("//*[@class='android.widget.CheckedTextView' and @index ='2']");
	
	
	public static final By searchfield=By.xpath("//*[@id='lst-ib']");
	public static final By searchbutton =By.xpath("//*[@id='sblsbb']/button");
	public static final By gettickets=By.xpath("//div[@class='leftcol' and contains(.,'your area')]//a[contains(.,'Get Tickets')]");
	public static final By selectionform=By.xpath("//input[@name='productRequestForms[0].quantity']/following-sibling::button");
	public static final By bestseats=By.cssSelector("#bestSeats");
	public static final By mobile=By.xpath("//p[contains(.,'Mobile')]");
	public static final By continu=By.cssSelector(".continue");
	public static final By cart =By.cssSelector("#cartModal");
	//public static String language1 = "//label[contains(text(),'";
//	public static String language2 = "')]";
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
			
			remoteWebDriverForAppium.get("http://ticketfly.com/");	
   	        return true;
	}
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Launch Failed";
			return false;
		}
		
	}
	
public static boolean bookTickets() throws Throwable{
		
		try{
			Thread.sleep(2000);
			AppiumActionEngine.platformOverride = "Mobile";
			
		
			
			if(!waitForElementPresent(msearchfield, "Search Icon", WAITTIME)){
				gErrMsg = "Search Link not visible" ;
				return false;
			}
			  
			gentype(msearchfield, "SATURDAY Clearwater Festival", "searchfield");			
			
			remoteWebDriverForAppium.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			Runtime.getRuntime().exec("cmd /K cd " +"C:/MobileAutomationSetup/adtbundle/sdk/platform-tools");
			Runtime.getRuntime().exec("cmd /C adb shell input keyevent 66");
			
			remoteWebDriverForAppium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			AppiumActionEngine.platformOverride = "Mobile";
			
			if(!waitForElementPresent(mfindtickets, "find tickets", WAITTIME)){
			gErrMsg = "find tickets not visible" ;
			return false;
		    }		
	      	genclick(mfindtickets, "find tickets");
	      	
	    	remoteWebDriverForAppium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	remoteWebDriverForAppium.swipe(0, 0, 0, 362, 10000);
	    	
	    	AppiumActionEngine.platformOverride = "Mobile";
	    	if(!waitForElementPresent(mticketarrow, "dropdown to choose number of tickets", WAITTIME)){
				gErrMsg = "dropdown to choose number of tickets not visible" ;
				return false;
			    }		
		      	genclick(mticketarrow, "dropdown to choose number of tickets");
		      	
		      	AppiumActionEngine.platformOverride = "Mobile";
		    	if(!waitForElementPresent(mnumberoftickets, "dropdown to choose number of tickets", WAITTIME)){
					gErrMsg = "dropdown to choose number of tickets not visible" ;
					return false;
				    }		
			      	genclick(mnumberoftickets, "dropdown to choose number of tickets");
	    	
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
			driver.get("http://www.google.com/");
			Thread.sleep(2000);
			//  AppiumActionEngine.platformOverride = "Mobile";
			
		if(!waitForElementPresent(searchfield, "Search Icon", WAITTIME)){
			gErrMsg = "Search Link not visible" ;
			return false;
		}
		  
		  	gentype(searchfield, "SATURDAY Clearwater Festival", "searchfield");
		
		 
			if(!waitForElementPresent(searchbutton, "search button", WAITTIME)){
			gErrMsg = "search button not visible" ;
			return false;
		}		
		genclick(searchbutton, "Search Button");
		

		return true;
	} 
	catch (Exception e) {
		e.printStackTrace();
		gErrMsg="Unable to sign in";
		return false;
	}
		
	}
}