package com.automation.accelerators;       

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.automation.support.ActionEngineSupport;
import com.automation.support.ConfiguratorSupport;
import com.automation.support.ExcelReader;
import com.automation.support.HtmlReportSupport;
import com.automation.support.ReportStampSupport;
import com.automation.utilities.Reporter;
import com.automation.utilities.SendMail;
import com.experitest.client.Client;


public class TestEngine extends SendMail {
	public static Logger logger = Logger.getLogger(TestEngine.class.getName());

	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");

	public static ConfiguratorSupport counterProp = new ConfiguratorSupport(
			configProps.getProperty("counterPath"));

	public static String currentSuite = "";
	public static String method = "";
	//public static String timeStamp = ReportStampSupport.timeStamp().replace(" ", "_").replace(":", "_").replace(".", "_");
	public static boolean flag = false;
	public static WebDriver webDriver = null;
	public static EventFiringWebDriver driver=null;
	public static int stepNum = 0;
	public static int PassNum =0;
	public static int FailNum =0;
	public static int passCounter =0;
	public static int failCounter =0;
	public static String testName = "";
	public static String testCaseExecutionTime = "";
	public static StringBuffer x=new StringBuffer();
	public static String finalTime = "";
	public static boolean isSuiteRunning=false;
	public static Map<String, String> testDescription = new LinkedHashMap<String, String>();
	public static Map<String, String> testResults = new LinkedHashMap<String, String>();
	public static String url=null;
	//static ExcelReader xlsrdr = new ExcelReader(configProps.getProperty("TestData"),configProps.getProperty("sheetName0"));
	/*
	 * public static Screen s; public static String url =
	 * "jdbc:mysql://172.16.6.121/"; public static String dbName = "test";
	 * public static String userName = "root"; public static Connection conn =
	 * null; public static Statement stmt = null; public static
	 * PreparedStatement pstmt = null; public static ResultSet rs = null;
	 */
	public static int countcompare = 0;

	public static String browser = null;
	static int len = 0;
	static int i = 0;
	public static ITestContext itc;
	public static String groupNames =null;
	

	
	//*************************************************APPIUM IMPLEMENTATION****************************************/

	public static AppiumDriver remoteWebDriverForAppium =null;
	//public AppiumDriver mobileDriver=null;
	public DesiredCapabilities capabilities;
	
	
	//*************************************************APPIUM IMPLEMENTATION****************************************/
	
	/**
	 * Initializing browser requirements, Test Results file path and Database
	 * requirements from the configuration file
	 * @throws IOException 
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	
	
	@BeforeSuite(alwaysRun = true)
	public static void setupSuite(ITestContext ctx) throws Throwable {
		
		//*************************************************APPIUM App Launch ****************************************/
		
		if(ctx.getCurrentXmlTest().getParameter("mbrowser")!= null){
			
			String bro = ctx.getCurrentXmlTest().getParameter("mbrowser");
			Runtime.getRuntime().exec("cmd.exe /c start appium --address 127.0.0.1 --port 4723");
			Thread.sleep(15000);
			DesiredCapabilities capabilitiesForAppium = new DesiredCapabilities();
			capabilitiesForAppium.setCapability(CapabilityType.PLATFORM, "Android");
	         capabilitiesForAppium.setCapability("deviceName","Cigniti");		 
	         capabilitiesForAppium.setCapability("platformVersion", "4.4");
	         capabilitiesForAppium.setCapability("platformName","Android");
	         capabilitiesForAppium.setCapability("browserName",bro);
	         capabilitiesForAppium.setCapability("autoWebview", true);
	         capabilitiesForAppium.setCapability("takesScreenshot", true); 
	         
	         remoteWebDriverForAppium = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesForAppium);
	         
	         remoteWebDriverForAppium.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}
		
		
		
		    //if(ctx.getCurrentXmlTest().getParameter("app")!=null){
			//String path = configProps.getProperty(ctx.getCurrentXmlTest().getParameter("app"));
			//Runtime.getRuntime().exec("cmd.exe /c start appium --address 127.0.0.1 --port 4723");
		
		  //Thread.sleep(10000);
			//DesiredCapabilities capabilitiesForAppium = new DesiredCapabilities();
			//capabilitiesForAppium.setCapability(CapabilityType.PLATFORM, "Android");
	        //capabilitiesForAppium.setCapability("newCommandTimeout","300");
			//capabilitiesForAppium.setCapability("deviceName","CignitiTech");
	        // capabilitiesForAppium.setCapability("platformVersion", "4.4.4");
	        // capabilitiesForAppium.setCapability("platformName","Android");
	        // capabilitiesForAppium.setCapability("app", "C:/MobileAutomationSetup/Appium/com.movilizer.client.android.app.wwu.demo-1.apk");
	        //capabilitiesForAppium.setCapability("appActivity", "com.netflix.mediaclient.ui.LaunchActivity");
	        // capabilitiesForAppium.setCapability("noReset", true);
	       //  capabilitiesForAppium.setCapability("app", path);
	      
	       //  remoteWebDriverForAppium = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesForAppium);
	  
	//}
 
		
		//*************************************************APPIUM App Launch End****************************************/
		
	
		//*************************************************APPIUM IMPLEMENTATION****************************************/
		
		
		itc = ctx;
		groupNames = ctx.getCurrentXmlTest().getIncludedGroups().toString();
		String strBrowserType[];

		ReportStampSupport.calculateSuiteStartTime();

		try {
			if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("_chrome")) {
				
				browser = "chrome";

			} else if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("_firefox")) {
				

				browser = "firefox";

			} else if (ctx.getCurrentXmlTest().getIncludedGroups().toString()
					.contains("_ie")) {
				
				browser = "ie";

			}

			

			else {
			/*	logger.info("Starting browser : "
						+ configProps.getProperty("browserType"));*/

				browser = configProps.getProperty("browserType");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1);
		}

		// browser = configProps.getProperty("browserType");
		if (browser.toString().contains(",")) {
			strBrowserType = browser.split("\\,");

		} else {
			strBrowserType = new String[] { browser };
		}
		if (browser.toString().contains(",")) {
			strBrowserType = browser.split("\\,");
		} else {
			strBrowserType = new String[] { browser };
		}
		len = strBrowserType.length;
		while (i < len) {

			if (strBrowserType[i].toString().equalsIgnoreCase("firefox")) {
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile ffprofile = profile.getProfile("Quickflix");
				//System.out.println(ffprofile);

				webDriver = new FirefoxDriver();
				i = i + 1;
				break;

			} else if (strBrowserType[i].toString().equalsIgnoreCase("ie")) {
				File file = new File("Drivers\\IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver",
						file.getAbsolutePath());

				webDriver = new InternetExplorerDriver();
				i = i + 1;
				if (configProps.getProperty("browserType").equals("ie")) {
					Process p = Runtime
							.getRuntime()
							.exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
					p.waitFor();
					Thread.sleep(1000);
				}
				break;

			} else if (strBrowserType[i].toString().equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"Drivers\\chromedriver.exe");
				
			    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			    ChromeOptions options = new ChromeOptions();
			    options.addArguments("test-type");
			    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			    webDriver = new ChromeDriver(capabilities);
				i = i + 1;
				break;

			}

		}
		driver = new EventFiringWebDriver(webDriver);

		ActionEngineSupport myListener = new ActionEngineSupport();
		driver.register(myListener);

		try {
			
			
				url = (configProps.getProperty("URL"));
			
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1);
		}
		//driver.manage().window().maximize();
		driver.manage().window().setPosition(new Point(-2000, 0));
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
		groupNames = ctx.getCurrentXmlTest().getIncludedGroups().toString();
		 browser = configProps.getProperty("browserType");
		Reporter.reportCreater();
		HtmlReportSupport.currentSuit = ctx.getCurrentXmlTest().getSuite()
				.getName();
	}

	

	/**
	 * De-Initializing and closing all the connections
	 * 
	 * @throws Throwable
	 */
	

	@AfterSuite(alwaysRun = true)
	public void tearDown(ITestContext ctx) throws Throwable {
		
		ReportStampSupport.calculateSuiteExecutionTime();
		
		if (groupNames.equals("")) {
			HtmlReportSupport.createHtmlSummaryReport();
		} else {
			HtmlReportSupport.createHtmlSummaryReport(browser, url);
		}

		driver.quit();
		closeSummaryReport();
		
	}
	

	/**
	 * Write results to Browser specific path
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	// @Parameters({"browserType"})
	public static String filePath() {
		String strDirectoy = "";
		
		if(groupNames.equals("")){
			
		if (configProps.getProperty("browserType").equalsIgnoreCase("ie")) {
			strDirectoy = "IE\\IE";

		} else if (configProps.getProperty("browserType").equalsIgnoreCase("firefox")) {
			strDirectoy = "Firefox\\Firefox";

		} else {
			strDirectoy = "Chrome\\Chrome";
			
		}
		
		}else{
			if (browser.equalsIgnoreCase("ie")) {
				strDirectoy = "IE\\IE";

			} else if (browser.equalsIgnoreCase("firefox")) {
				strDirectoy = "Firefox\\Firefox";

			} else {
				strDirectoy = "Chrome\\Chrome";
				
			}
		}

		if (strDirectoy != "") {
			new File(configProps.getProperty("screenShotPath") + strDirectoy
					).mkdirs();
		}
	
		File results = new File(configProps.getProperty("screenShotPath") + strDirectoy
			+"\\"+"Screenshots");
		if(!results.exists())
		{
			results.mkdir();
			HtmlReportSupport.copyLogos();
		}

		return configProps.getProperty("screenShotPath") + strDirectoy  + "\\";

	}

	/**
	 * Browser type prefix for Run ID
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	public static String result_browser() {
		if (groupNames.equals("")) {
			if (configProps.getProperty("browserType").equals("ie")) {
				return "IE";
			} else if (configProps.getProperty("browserType").equals("firefox")) {
				return "Firefox";
			} else {
				return "Chrome";
			}
		} else {
			if (browser.equalsIgnoreCase("ie")) {
				return "IE";

			} else if (browser.equalsIgnoreCase("firefox")) {
				return "Firefox";

			} else {
				return "Chrome";

			}
		}
	}

	/**
	 * Related to Xpath
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */
	public static String methodName() {
		if (groupNames.equals("")) {
			if (configProps.getProperty("browserType").equals("ie")) {
				return "post";
			} else {
				return "POST";
			}
		} else {
			if (browser.equals("ie")) {
				return "post";
			} else {
				return "POST";
			}
		}
	}
	@BeforeMethod(alwaysRun = true)
	public void reportHeader(Method method, ITestContext ctx) throws Throwable {
		itc = ctx;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		//String formattedDate = sdf.format(date);
		ReportStampSupport.calculateTestCaseStartTime();

		flag = false;
		
		HtmlReportSupport.tc_name = method.getName().toString() + "-"
				/*+ formattedDate*/;
		String[] ts_Name = this.getClass().getName().toString().split("\\.");
		HtmlReportSupport.packageName = ts_Name[0] + "." + ts_Name[1] + "."
				+ ts_Name[2];

		if (groupNames.equals("")) {
			HtmlReportSupport.testHeader(HtmlReportSupport.tc_name);
		} else {
			HtmlReportSupport.testHeader(HtmlReportSupport.tc_name, browser);
		}
		stepNum = 0;
		PassNum = 0;
		FailNum = 0;
		testName = method.getName();

	}
	
	@AfterMethod(alwaysRun = true)
	public static void tearDown()
	{
		ReportStampSupport.calculateTestCaseExecutionTime();
		closeDetailedReport();
		if(FailNum!=0)
		{
			failCounter=failCounter+1;
			testResults.put(HtmlReportSupport.tc_name, "FAIL");
		}
		else
		{
			testResults.put(HtmlReportSupport.tc_name, "PASS");
			passCounter=passCounter+1;
		}
	}
	
	
}
