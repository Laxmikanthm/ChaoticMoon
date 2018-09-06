package com.appium.wwu.tests;

import org.testng.annotations.Test;

import com.appium.espn.businesslogic.AppiumChampDriveLib;
import com.appium.espn.businesslogic.AppiumWwuLib;
import com.automation.accelerators.TestEngine;
import com.automation.support.HtmlReportSupport;
import com.automation.utilities.Reporter;

public class EMS extends AppiumWwuLib{	

	
    @Test	
	public void NavigateToEMSLinks() throws Throwable
	{				
		try{	
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name,  "User should be able set alerts for a match");
			
			if(!goToEMS()) {
				Reporter.failureReport("Navigate to EMS Link", gErrMsg);	
				return;
			}
			else			
				Reporter.SuccessReport("Navigate to EMS Link", "Successfully Navigated to EMS Link");

			if(!navigateToalllinks()) {
				Reporter.failureReport("Navigate to all links", gErrMsg);	
				
				return;
			}
			else			
				Reporter.SuccessReport("Navigate to all EMS options", "Successfully Navigated to all EMS options");
			
			if(!exitApp()) 
				Reporter.failureReport("Exit from app", gErrMsg);	
			else			
				Reporter.SuccessReport("Exit from app", "Successfully Exit from app");
	
		}
		catch(Exception e){			
			e.printStackTrace();
		}
	}	

}

