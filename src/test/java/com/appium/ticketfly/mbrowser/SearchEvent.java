package com.appium.ticketfly.mbrowser;

import org.testng.annotations.Test;

import com.appium.espn.businesslogic.TaylorMadeWebLib;
import com.appium.espn.businesslogic.TicketFlyWebLib;
import com.automation.accelerators.TestEngine;
import com.automation.support.HtmlReportSupport;
import com.automation.utilities.Reporter;

public class SearchEvent extends TicketFlyWebLib {

    @Test	
	public void findingEvents() throws Throwable
	{				
		try{	
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name,  "User should be able to signin into netflix");
			
			if(!launchUrl()){
				
				Reporter.failureReport("unable to open firefix browser", gErrMsg);					
			}
			else			
				Reporter.SuccessReport(" ", "Successfully changed language in firefox");
	   
			if(!bookTickets()){
				
				Reporter.failureReport("unable to open firefix browser", gErrMsg);	
						
			}
			else			
				Reporter.SuccessReport(" ", "Successfully changed language in firefox");
			
			/*if(!openFirefoxBrowser()){
								
				Reporter.failureReport("unable to open firefix browser", gErrMsg);	
						
			}
			else			
				Reporter.SuccessReport(" ", "Successfully changed language in firefox");*/
		}
			catch(Exception e){			
				e.printStackTrace();
			}
		}

	}



