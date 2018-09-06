package com.appium.espn.businesslogic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.automation.accelerators.ActionEngine;
import com.automation.accelerators.AppiumActionEngine;

public class AppiumSportsCentreLib extends AppiumActionEngine{

	public static String gErrMsg="";
	public static int WAITTIME = 60;
	
	public static By SIGN_UP_LATER = By.xpath("//*[@text='Sign Up Later' and @class='android.widget.TextView']");
	public static By MENU = By.xpath("//*[@content-desc='Navigate up' and @class='android.widget.FrameLayout']");
	//public static By MENU = By.xpath("//*[(@id='android:id/up' and @class='android.widget.ImageView') or (@id='android:id/home' and @class='android.widget.ImageView') or (@content-desc='Navigate up' and @class='android.widget.FrameLayout')]");
	//public static By MENU = By.id("android:id/home");
	public static By INBOX = By.xpath("//*[@text='Inbox' and @class='android.widget.TextView']");
	public static By ALERT_SETTINGS = By.xpath("//*[@content-desc='Alert Settings' and @class='android.widget.TextView']");
	public static By PLAYTONE_ALERT = By.xpath("//*[@index='1' and @class='android.widget.LinearLayout']//*[@index='1' and @class='android.widget.CheckBox']");
	public static By VIBRATIONON_ALERT = By.xpath("//*[@index='2' and @class='android.widget.LinearLayout']//*[@index='1' and @class='android.widget.CheckBox']");
	public static By PULSE_ALERT = By.xpath("//*[@index='3' and @class='android.widget.LinearLayout']//*[@index='1' and @class='android.widget.CheckBox']");
	public static By BREAKING_NEWS = By.xpath("//*[@index='5' and @class='android.widget.LinearLayout']//*[@index='1' and @class='android.widget.Switch']");
	public static By NFL_NEWS = By.xpath("//*[@index='7' and @class='android.widget.LinearLayout']//*[@index='1' and @class='android.widget.Switch']");
	public static By DISMISS = By.xpath("//*[@text='Dismiss' and @class ='android.widget.Button']");
	
	
	public static boolean goToMenu() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(waitForElementPresent(SIGN_UP_LATER, "Sign Up Later Link", WAITTIME)){
	        	//gErrMsg = "Sign Up Later Link not visible" ;
	        	//return false;
		        click(SIGN_UP_LATER, "Sign Up Later Link");
	        }
	        
	        if(!waitForElementPresent(MENU, "Main Menu Tab", WAITTIME*3)){
	        	gErrMsg = "Main Menu Tab not visible" ;
	        	return false;
	        }
	        
	        click(MENU, "Main Menu Tab");
	        

		return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to Menu failed";
			return false;
		}
	}
	
	
	public static boolean editSettings() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(!waitForElementPresent(INBOX, "Inbox link", WAITTIME)){
	        	gErrMsg = "Inbox Link not found" ;
	        	return false;
	        }
	        click(INBOX, "Inbox link");
	        
	       if(!waitForElementPresent(ALERT_SETTINGS, "Alert settings icon", WAITTIME)){
	        	gErrMsg = "Alert settings icon not found" ;
	        	return false;
	        }
	        click(ALERT_SETTINGS, "Alert settings icon");
	        

	        if(!waitForElementPresent(PLAYTONE_ALERT, "play tone alert checkbox", WAITTIME)){
	        	gErrMsg = "play tone alert checkbox not found" ;
	        	return false;
	        }
	        click(PLAYTONE_ALERT, "play tone alert checkbox");
	        
	        if(!waitForElementPresent(VIBRATIONON_ALERT, "vibration on alert checkbox", WAITTIME)){
	        	gErrMsg = "vibration on alert checkbox not found" ;
	        	return false;
	        }
	        click(VIBRATIONON_ALERT, "vibration on alert checkbox");
	        
	        if(!waitForElementPresent(BREAKING_NEWS, "Breaking News", WAITTIME)){
	        	gErrMsg = "Breaking News ON/OFF Toggle Button not found" ;
	        	return false;
	        }
	        click(BREAKING_NEWS, "Breaking News ON/OFF Toggle Button ");
	        
	        if(!waitForElementPresent(NFL_NEWS, "NFL News", WAITTIME)){
	        	gErrMsg = "NFL News ON/OFF Toggle Button not found" ;
	        	return false;
	        }
	        click(NFL_NEWS, "NFL News ON/OFF Toggle Button");
	        
	        if(!waitForElementPresent(DISMISS, "Dismiss popup button", WAITTIME)){
	        	gErrMsg = "Dismiss popup button not found" ;
	        	return false;
	        }
	        click(DISMISS, "Dismiss popup button ");
	        
	        Thread.sleep(3000);


			Runtime.getRuntime().exec("cmd /K cd " +"C:/MobileAutomationSetup/adtbundle/sdk/platform-tools");
			Runtime.getRuntime().exec("cmd /C adb shell input keyevent 4");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("cmd /K cd " +"C:/MobileAutomationSetup/adtbundle/sdk/platform-tools");
			Runtime.getRuntime().exec("cmd /C adb shell input keyevent 4");
			
		return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to Menu failed";
			return false;
		}
	}
	
	
	public static void backHome() throws Throwable
	{
		gErrMsg = "";		
		try {			
			for(int i=0; i<7; i++){
				if(isElementPresent(MENU, "Menu"))
					return;
				Runtime.getRuntime().exec("cmd /K cd " +"C:/MobileAutomationSetup/adtbundle/sdk/platform-tools");
				Runtime.getRuntime().exec("cmd /C adb shell input keyevent 4");
				Thread.sleep(1000);
			}
		} 
		catch (Exception e) {
			gErrMsg="Back Home operation failed";
			e.printStackTrace();
		}
	}
	

}
