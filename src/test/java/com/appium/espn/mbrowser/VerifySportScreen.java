package com.appium.espn.mbrowser;

import org.testng.annotations.Test;

import com.appium.espn.businesslogic.ESPNWebLib;
import com.automation.accelerators.TestEngine;
import com.automation.support.HtmlReportSupport;
import com.automation.utilities.Reporter;

public class VerifySportScreen extends ESPNWebLib {
	
	
	@Test()
	public void VerifySportsScreen() throws Throwable
	{		
		try{			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name,  "User should be able watch a match for few seconds");

			if(!launchUrl()) 
				Reporter.failureReport("Launch the ESPN Web application", gErrMsg);	
			else			
				Reporter.SuccessReport("Launch the ESPN Web application", "ESPN Web application Launched Successfully");
			
			if(!NavigateToSportsAndVerify()) 
				Reporter.failureReport("Select a Sport and Verify Sports screen", gErrMsg);	
			else			
				Reporter.SuccessReport("Select a Sport and Verify Sports screen", "Successfully selected a Sport and Verified Sports screen");					
		}
		catch(Exception e){			
			e.printStackTrace();
		}
	}

}
