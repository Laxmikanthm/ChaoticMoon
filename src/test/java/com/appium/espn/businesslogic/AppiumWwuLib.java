package com.appium.espn.businesslogic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.automation.accelerators.AppiumActionEngine;

public class AppiumWwuLib extends AppiumActionEngine{

	public static String gErrMsg="";
	public static int WAITTIME = 60;
	
	public static By EMS = By.xpath("//*[@index='0' and @class='android.widget.LinearLayout']/*[@index='2' and @class='android.widget.LinearLayout']");
	public static By ESCPOST = By.xpath("//*[@text='Downstream Esc Post' and @class='android.widget.TextView']");
	public static By BACK = By.xpath("//*[@index='0' and @class='android.widget.CompoundButton']");
	public static By JUMP = By.xpath("//*[@index='1' and @class='android.widget.CompoundButton']");
	public static By MAINMENU = By.xpath("//*[@text='Main Menu' and @class='android.widget.TextView']");
	public static By OFF = By.xpath("//*[@index='3' and @class='android.widget.LinearLayout']//*[@index='0' and @class='android.widget.CompoundButton']");
	public static By SETTINGS = By.xpath("//*[@text='Settings' and @class='android.widget.TextView']");
	public static By EDIT_ALERT = By.xpath("//*[@text='Edit Alerts' and @class='android.widget.TextView']");
	public static By PLAYTONE_ALERT = By.xpath("//*[@index='1' and @class='android.widget.LinearLayout']//*[@index='1' and @class='android.widget.CheckBox']");
	public static By VIBRATIONON_ALERT = By.xpath("//*[@index='2' and @class='android.widget.LinearLayout']//*[@index='1' and @class='android.widget.CheckBox']");
	public static By PULSE_ALERT = By.xpath("//*[@index='3' and @class='android.widget.LinearLayout']//*[@index='1' and @class='android.widget.CheckBox']");
	public static By COLLEGE_NEWS = By.xpath("//*[@index='1' and @class='android.widget.Switch']");
	public static By SCORES = By.xpath("//*[@text='Scores' and @class='android.widget.TextView']");
	public static By SEE_ALL_FBS_SCORES = By.xpath("//*[@text='See All FBS Scores' and @class='android.widget.TextView']");
	public static By ALERT_ICON = By.xpath("//*[@text='í' and @class='android.widget.TextView']");
	public static By ALERT_OFF = By.xpath("//*[@text='OFF' and @class='android.widget.Switch']");
	public static By ALERT_OK = By.xpath("//*[@text='OK' and @class ='android.widget.Button']");
	public static By SHARE_ICON = By.xpath("//*[@content-desc='Share with' and @class='android.widget.ImageView']");
	public static By SEE_ALL_SHARE_OPTION = By.xpath("//*[@text='See all' and @class='android.widget.TextView']");
	public static By SHARE_VIA_MESSAGING = By.xpath("//*[@text='Messaging' and @class='android.widget.TextView']");
	public static By OK_BUTTON = By.xpath("//*[@text='OK' and @class ='android.widget.Button']");
	
	public static By CHAMP_TOP_NEWS = By.xpath("//*[@text='Top News' and @class ='android.widget.TextView']");

	
	
	public static boolean goToEMS() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(!waitForElementPresent(EMS, "EMS Link", WAITTIME)){
				gErrMsg = "Sign Up Later Link not visible" ;
				return false;
			}
				click(EMS, "EMS Link");
	
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to EMS failed";
			return false;
		}
	}


	public static boolean navigateToalllinks() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(!waitForElementPresent(ESCPOST, "ESC Post Link", WAITTIME)){
				gErrMsg = "ESC Post Link not found" ;
				return false;
			}
			click(ESCPOST, "ESC Post Link");

			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to alerts page failed";
			return false;
		}
	}
	
	public static boolean exitApp() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(!waitForElementPresent(BACK, "Back Button", WAITTIME)){
				gErrMsg = "Back Button not found" ;
				return false;
			}
			click(BACK, "Back Button");
			
			if(!waitForElementPresent(JUMP, "Jump Button", WAITTIME)){
				gErrMsg = "Jump Button not found" ;
				return false;
			}
			click(JUMP, "Jump Button");

			
			if(!waitForElementPresent(MAINMENU, "Main Menu link", WAITTIME)){
				gErrMsg = "Main Menu link not found" ;
				return false;
			}
			click(MAINMENU, "Main Menu link");
			
			if(!waitForElementPresent(OFF, "Off Button", WAITTIME)){
				gErrMsg = "Off Button not found" ;
				return false;
			}
			click(OFF, "Off Button");


			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to alerts page failed";
			return false;
		}
	}


	/*public static boolean changeAlertSettings() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(!waitForElementPresent(ALERT_ICON, "Alert Icon", WAITTIME*2)){
				gErrMsg = "Alert Icon not found" ;
				return false;
			}
			click(ALERT_ICON, "Alert Icon");

			if(!waitForElementPresent(ALERT_OFF, "Alert switch", WAITTIME)){
				gErrMsg = "Alert switch not found" ;
				return false;
			}
			click(ALERT_OFF, "Alert switch");

			if(!waitForElementPresent(ALERT_OK, "OK button", WAITTIME)){
				gErrMsg = "OK button not found" ;
				return false;
			}
			click(ALERT_OK, "OK button");

			remoteWebDriverForAppium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if(!isElementPresent(SIGN_UP_LATER, "Sign Up Later Link")){
				gErrMsg = "Sign Up Later Link not visible" ;
				return false;
			}
			click(SIGN_UP_LATER, "Sign Up Later Link"); 

			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to alerts page failed";
			return false;
		}
	}


	public static boolean openMatch(int n) throws Throwable
	{
		gErrMsg = "";	
		By MATCH_AT_GIVEN_POSITION = By.xpath("//*[@index='"+n+"' and @class='android.widget.LinearLayout']//*[@index='0' and @class='android.widget.LinearLayout']");
		try {

			if(!waitForElementPresent(SCORES, "Scores Link", WAITTIME)){
				gErrMsg = "Scores Link not found" ;
				return false;
			}
			click(SCORES, "Scores link");

			if(!waitForElementPresent(MATCH_AT_GIVEN_POSITION, "Match at position "+n, WAITTIME*3)){
				gErrMsg = "Match at given position not found" ;
				return false;
			}
			click(MATCH_AT_GIVEN_POSITION, "Match at position "+n);	        

			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to alerts page failed";
			return false;
		}
	}


	public static boolean share() throws Throwable
	{
		gErrMsg = "";	
		try {

			if(!waitForElementPresent(SHARE_ICON, "Share Icon", WAITTIME*2)){
				gErrMsg = "Share Icon not found" ;
				return false;
			}
			click(SHARE_ICON, "Share Icon");
					

			if(!waitForElementPresent(SHARE_VIA_MESSAGING, "Share Via Messaging link", WAITTIME)){
				gErrMsg = "Share Via Messaging link not found" ;
				return false;
			}
			click(SHARE_VIA_MESSAGING, "Share Via Messaging link");

			remoteWebDriverForAppium.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			Runtime.getRuntime().exec("cmd /K cd " +"C:/MobileAutomationSetup/adtbundle/sdk/platform-tools");
			Runtime.getRuntime().exec("cmd /C adb shell input keyevent 4");

			remoteWebDriverForAppium.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			Runtime.getRuntime().exec("cmd /K cd " +"C:/MobileAutomationSetup/adtbundle/sdk/platform-tools");
			Runtime.getRuntime().exec("cmd /C adb shell input keyevent 4");

			if(!waitForElementPresent(ALERT_OK, "OK button", WAITTIME)){
				gErrMsg = "OK button not found" ;
				return false;
			}
			click(ALERT_OK, "OK button");     

			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to alerts page failed";
			return false;
		}
	}
	
	public static boolean goToSettings() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(waitForElementPresent(SIGN_UP_LATER, "Sign Up Later Link", WAITTIME)){
	        	//gErrMsg = "Sign Up Later Link not visible" ;
	        	//return false;
				 click(SIGN_UP_LATER, "Sign Up Later Link");
	        }
	       
	        
	        remoteWebDriverForAppium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Runtime.getRuntime().exec("cmd /K cd " +"C:/MobileAutomationSetup/adtbundle/sdk/platform-tools");
			Runtime.getRuntime().exec("cmd /C adb shell input keyevent 82");
	        

		return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to Menu failed";
			return false;
		}
	}
	
	
	public static boolean editAlerts() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(!waitForElementPresent(SETTINGS, "Settings Menu Option", WAITTIME)){
	        	gErrMsg = "Settings Menu Option not found" ;
	        	return false;
	        }
	        click(SETTINGS, "Settings Menu Option");
	        
	        if(!waitForElementPresent(EDIT_ALERT, "Edit Alert Link", WAITTIME)){
	        	gErrMsg = "Edit Alert Link not found" ;
	        	return false;
	        }
	        click(EDIT_ALERT, "Edit Alert Link");
	        
	    	return true;
	       
		} 
	
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to Menu failed";
			return false;
		}
	}
		
		public static boolean editAlertSettings() throws Throwable
		{
			gErrMsg = "";		
			try {
				
			
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
	        
	      	if(!waitForElementPresent(VIBRATIONON_ALERT, "vibration on alert checkbox", WAITTIME)){
		     gErrMsg = "vibration on alert checkbox not found" ;
		     return false;
		     }
		     click(VIBRATIONON_ALERT, "vibration on alert checkbox");
	        
	        if(!waitForElementPresent(PULSE_ALERT, "Pulse Notification Light CheckBox", WAITTIME)){
	        	gErrMsg = "Pulse Notification Light CheckBox not found" ;
	        	return false;
	        }
	        click(PULSE_ALERT, "Pulse Notification Light CheckBox ");
	        
	        if(!waitForElementPresent(COLLEGE_NEWS, "College Football News", WAITTIME)){
	        	gErrMsg = "College Football News not found" ;
	        	return false;
	        }
	        click(COLLEGE_NEWS, "College Football News Toggle Button");
	        
	        if(!waitForElementPresent(ALERT_OK, "OK button", WAITTIME)){
				gErrMsg = "OK button not found" ;
				return false;
			}
			click(ALERT_OK, "OK button");

			remoteWebDriverForAppium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if(!isElementPresent(SIGN_UP_LATER, "Sign Up Later Link")){
				gErrMsg = "Sign Up Later Link not visible" ;
				return false;
			}
			click(SIGN_UP_LATER, "Sign Up Later Link"); 

			return true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Navigation to Menu failed";
			return false;
		}
	}
		*/
		/*public static void backHome() throws Throwable
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
		}*/
			
	
}
