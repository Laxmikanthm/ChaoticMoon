package com.appium.espn.businesslogic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.automation.accelerators.AppiumActionEngine;

public class AppiumCricInfoLib extends AppiumActionEngine{

	public static String gErrMsg="";
	public static int WAITTIME = 60;
	
	public static By APP = By.xpath("//*[@text='GO TO APP' and @class='android.widget.Button']");
	public static By MENU = By.id("com.july.cricinfo:id/menu_button");
	
	public static By SEARCH = By.id("com.july.cricinfo:id/linMenuSearchBtn_bg");
	public static By SEARCH_BAR = By.xpath("//*[@text='SEARCH' and @class='android.widget.EditText']");
	public static By SEARCH_BUTTON = By.id("com.july.cricinfo:id/searchBtn");
	public static By PLAYER_QUICKPROFILE = By.xpath("//*[@index='1' and @class='android.widget.LinearLayout']//*[@index='3' and @class='android.widget.ImageButton']");
	
	public static By COMPLETE_PROFILE = By.xpath("//*[@text='VIEW COMPLETE PROFILE' and @class='android.widget.Button']");
	public static By SEE_ALL_FBS_SCORES = By.xpath("//*[@text='See All FBS Scores' and @class='android.widget.TextView']");
	public static By ALERT_ICON = By.xpath("//*[@text='í' and @class='android.widget.TextView']");
	public static By ALERT_OFF = By.xpath("//*[@text='OFF' and @class='android.widget.Switch']");
	public static By ALERT_OK = By.xpath("//*[@text='OK' and @class ='android.widget.Button']");
	public static By SHARE_ICON = By.xpath("//*[@content-desc='Share with' and @class='android.widget.ImageView']");
	public static By SEE_ALL_SHARE_OPTION = By.xpath("//*[@text='See all' and @class='android.widget.TextView']");
	public static By SHARE_VIA_MESSAGING = By.xpath("//*[@text='Messaging' and @class='android.widget.TextView']");
	public static By OK_BUTTON = By.xpath("//*[@text='OK' and @class ='android.widget.Button']");
	
	public static By CHAMP_TOP_NEWS = By.xpath("//*[@text='Top News' and @class ='android.widget.TextView']");

	

	public static boolean goToMenu() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(waitForElementPresent(APP, "Go to App Link", WAITTIME)){
				//gErrMsg = "Sign Up Later Link not visible" ;
				//return false;
				click(APP, "Go to App Link");
			}


			if(!waitForElementPresent(MENU, "Main Menu Tab", WAITTIME*2)){
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


	public static boolean searchPlayer() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(!waitForElementPresent(SEARCH, "Search Button", WAITTIME)){
				gErrMsg = "Search Button not found" ;
				return false;
			}
			click(SEARCH, "Search Button");

			if(!waitForElementPresent(SEARCH_BAR, "Search field", WAITTIME)){
				gErrMsg = "Search field not found" ;
				return false;
			}
			type(SEARCH_BAR, "Sachin", "Search field");
			
			if(!waitForElementPresent(SEARCH_BUTTON, "Search Button", WAITTIME)){
				gErrMsg = "Search Button not found" ;
				return false;
			}
			click(SEARCH_BUTTON, "Search Button");

			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Search Operation Failed";
			return false;
		}
	}


	public static boolean profileView() throws Throwable
	{
		gErrMsg = "";		
		try {

			if(!waitForElementPresent(PLAYER_QUICKPROFILE, "Quick Profile View Button", WAITTIME*2)){
				gErrMsg = "Quick Profile View Button not found" ;
				return false;
			}
			click(PLAYER_QUICKPROFILE, "Quick Profile View Button");

			if(!waitForElementPresent(COMPLETE_PROFILE, "Complete Profile View Button", WAITTIME)){
				gErrMsg = "Complete Profile View Button not found" ;
				return false;
			}
			click(COMPLETE_PROFILE, "Complete Profile View Button");


			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Profile View gets Failed";
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
