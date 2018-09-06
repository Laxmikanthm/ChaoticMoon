package com.appium.espn.businesslogic;

import org.openqa.selenium.By;

import com.automation.accelerators.AppiumActionEngine;




public class ESPNWebLib extends AppiumActionEngine{

	public static final int LOWWAITTIME=20000;
	public static final int WAITTIME=50000;
	public static final int LONGWAITTIME=200000;
	public static String gErrMsg="";

	/**
	 * Launches the ESPN Web Application
	 * @return
	 * @throws Throwable
	 */
	public static boolean launchUrl() throws Throwable
	{
		gErrMsg = "";		
		try {
			
			remoteWebDriverForAppium.get("http://espn.go.com/");	
						
		} 
		catch (Exception e) {
			e.printStackTrace();
			gErrMsg="Launch Failed";
			return false;
		}
		return true;
	}


	/**
	 * Login to ESPN Web Application
	 * @return
	 * @throws Throwable
	 */
	/*public static boolean loginToESPNWeb(String userName, String password) throws Throwable
	{
		gErrMsg = "";		
		try {
			//client.swipe("Down", 345, 1056);
			mClick("default", "WEB_MYESPNTAB", "MyESPN Tab", 0, 1);
			mWaitForElement("default", "WEB_USERNAME", "UserName field", 0, WAITTIME);
			mElementSendText("default", "WEB_USERNAME", "UserName field", 0, userName);

			mElementSendText("default", "WEB_PASSWORD", "Password field", 0, password);

			mClick("default", "WEB_LOGIN", "Login Button", 0, 1);

			if(mWaitForElement("NATIVE", "xpath=//*[@text='Not now']", "Not Now button", 0, 2000)){
				mClick("NATIVE", "xpath=//*[@text='Not now']", "Not Now button", 0, 1);
			}
			if(!mWaitForElement("default", "FF_HomeScreen", "Fantasy Football screen", 0, WAITTIME)){
				gErrMsg="Login not successful as User menu not found.";
				return false;
			}

			return true;
		} 
		catch (Exception e) {
			gErrMsg="LOGIN FAILED";
			e.printStackTrace();
			return false;
		}
	}

	*//**
	 * Select an option
	 * @return
	 * @throws Throwable
	 *//*
	public static boolean SelectOptionAndVerify(String clickElement, String verifyElement) throws Throwable
	{
		gErrMsg = "";		
		try {							
			if(!mIsElementFound("default", clickElement, "Select element", 0)){
				gErrMsg = "Failed to display" + clickElement;
				return false;
			}
			else
			{
				mClick("default", clickElement, "Verify Element", 0, 1);
				if(mWaitForElement("default", verifyElement, "Verify Element", 0, 2000)){
					gErrMsg = "Failed to display" + verifyElement;
					return false;
				}
			}				
			return true;
		} 
		catch (Exception e) {
			gErrMsg="LOGIN FAILED";
			e.printStackTrace();
			return false;
		}
	}
	
	*//**
	 * Select Sports tab
	 * @return
	 * @throws Throwable
	 *//*
	public static boolean VerifySportsScreen() throws Throwable
	{
		gErrMsg = "";		
		try {			
			if(!mWaitForElement("default", "WEB_Scores", "Scores Tab", 0, WAITTIME)){
				gErrMsg = "Failed to display Scores Tab";
				return false;
			}
			else
			{
				mClick("default", "WEB_Scores", "Scores Tab", 0, 1);
			}
			if(!mWaitForElement("default", "WEB_ScoresScreen", "Scores Screen", 0, WAITTIME)){
				gErrMsg = "Failed to display Scores Screen";
				return false;
			}
			if(!mWaitForElement("default", "WEB_Standings", "Standings Tab", 0, WAITTIME)){
				gErrMsg = "Failed to display Standings Tab";
				return false;
			}
			else
			{
				mClick("default", "WEB_Standings", "Standings Tab", 0, 1);
			}
			if(!mWaitForElement("default", "WEB_StandingsScreen", "Standings Screen", 0, WAITTIME)){
				gErrMsg = "Failed to display Standings Screen";
				return false;
			}
			if(!mWaitForElement("default", "WEB_News", "News Tab", 0, WAITTIME)){
				gErrMsg = "Failed to display News Tab";
				return false;
			}
			else
			{
				mClick("default", "WEB_News", "News Tab", 0, 1);
			}
			if(!mWaitForElement("default", "WEB_NewsScreen", "News Screen", 0, WAITTIME)){
				gErrMsg = "Failed to display News Screen";
				return false;
			}
			return true;
		} 
		catch (Exception e) {
			gErrMsg="LOGIN FAILED";
			e.printStackTrace();
			return false;
		}
	}*/

	/**
	 * Select Sports tab
	 * @return
	 * @throws Throwable
	 */
	public static boolean NavigateToSportsAndVerify() throws Throwable
	{
		gErrMsg = "";		
		try {
			click(By.xpath("//*[@id='navsports-link']"),"Sports Tab");
			waitForElementPresent(By.xpath("//*[@id='navsports-link']"),"Sports Tab");
			Thread.sleep(4000);
			
				click(By.xpath("//*[@id='navmore-sports-links']/li[1]/a"),"NFL Option");
				Thread.sleep(4000);
			
			if(!waitForElementPresent(By.xpath("//*[@id='subnav-links']/li[1]/a"),"Scores Tab")){
				gErrMsg = "Failed to display Scores Tab";
				return false;
			}
			else
			{
				click(By.xpath("//*[@id='subnav-links']/li[1]/a"),"Scores Tab");
			}
			Thread.sleep(4000);
			/*if(driver.getTitle().equals("NFL Scores - ESPN")){
				gErrMsg = "Failed to display Scores Screen";
				return false;
			}*/
			if(!waitForElementPresent(By.xpath("//*[@id='subnav-links']/li[2]/a"), "Standings Tab")){
				gErrMsg = "Failed to display Standings Tab";
				return false;
			}
			else
			{
				click(By.xpath("//*[@id='subnav-links']/li[2]/a"), "Standings Tab");
			}
			Thread.sleep(4000);
			/*if(driver.getTitle().equals("NFL Standings - ESPN")){
				gErrMsg = "Failed to display Standings Screen";
				return false;
			}*/
			if(!waitForElementPresent(By.xpath("//*[@id='subnav-links']/li[3]/a"), "News Tab")){
				gErrMsg = "Failed to display News Tab";
				return false;
			}
			else
			{
				click(By.xpath("//*[@id='subnav-links']/li[3]/a"), "News Tab");
			}
			Thread.sleep(4000);
			/*if(driver.getTitle().equals("NFL News - ESPN")){
				gErrMsg = "Failed to display News Screen";
				return false;
			}*/
			if(!waitForElementPresent(By.xpath("//*[@id='nav-bd']//h1"), "ESPN Logo")){
				gErrMsg = "Failed to display News Tab";
				return false;
			}
			else
			{
				click(By.xpath("//*[@id='nav-bd']//h1"), "ESPN Logo");
			}
			return true;
		} 
		catch (Exception e) {
			gErrMsg="LOGIN FAILED";
			e.printStackTrace();
			return false;
		}
	}
}
