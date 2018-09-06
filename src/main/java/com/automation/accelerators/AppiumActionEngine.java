/**
 * com.ctaf is a group of Selenium accelerators
 */
package com.automation.accelerators;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utilities.Reporter;

/**
 *  ActionEngine is a wrapper class of Selenium actions
 */
public class AppiumActionEngine extends TestEngine {
	public static WebDriverWait wait;

	static String bool = configProps.getProperty("OnSuccessReports");
	public static String platform = null;
	static boolean b = true; // /Boolean.parseBoolean(bool);
	public static String platformOverride = null;
	
	
	// public static boolean flag=false;

	/**
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable
	 */
	public static boolean click(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.findElement(locator).click();
			flag = true;
		} catch (Exception e) {
			return flag;
		} finally {
			
			System.out.println("Click on element : "+locator);
			System.out.println(String.valueOf(flag).toUpperCase());
			
			if (!flag) 
				Reporter.failureReport("Tap on " + locatorName, "Unable to click on " + locatorName);				
			else 
				Reporter.SuccessReport("Tap on " + locatorName, "Successfully click on " + locatorName);

			}		
		return flag;
	}
	
	public static boolean genclick(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		
		
		if(platformOverride != null)
		{	
		  // platform = System.getProperty("Platform_overide") ;
			platform = platformOverride;
		}
		else
		{
		 platform=configProps.getProperty("platformType");
		}
		
		try {
			if(platform.equalsIgnoreCase("Mobile"))
			{
			remoteWebDriverForAppium.findElement(locator).click();
			flag = true;
			}
			
			else if(platform.equalsIgnoreCase("Web"))
			{
			driver.findElement(locator).click();
			flag = true;
			}
			platformOverride = null;
		} catch (Exception e) {
			platformOverride = null;
			return flag;
		} finally {
			
			System.out.println("Click on element : "+locator);
			System.out.println(String.valueOf(flag).toUpperCase());
			
			if (!flag) 
				Reporter.failureReport("Tap on " + locatorName, "Unable to click on " + locatorName);				
			else 
				Reporter.SuccessReport("Tap on " + locatorName, "Successfully click on " + locatorName);

			}		
		return flag;
	}


	/**
	 * This method returns check existence of element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox, checkbox etc)
	 * @return: Boolean value(True or False)
	 * @throws NoSuchElementException
	 */
	public static boolean isElementPresent(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.findElement(by);

			flag = true;
			return true;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} 
		finally{
			System.out.println("Check if element present : "+by);
			System.out.println(String.valueOf(flag).toUpperCase());
		}
/*		finally {
			if (!flag) {
				Reporter.failureReport("Check IsElementPresent", locatorName 						+ " Element is not present on the page");
			} else if (b && flag) {
				Reporter.SuccessReport("IsElementPresent ",
						"Able to locate element" + locatorName);
			}

		}*/
	}

	public static boolean isPopUpElementPresent(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			if (remoteWebDriverForAppium.findElement(by).isDisplayed())
				flag = true;
			else
				flag = false;
			return flag;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (!flag) {
				// Reporter.failureReport("check IsElementPresent", locatorName
				// + " Element is not present on the page");
			} else if (b && flag) {
				Reporter.SuccessReport("IsElementPresent ",
						"Able to locate element" + locatorName);
			}

		}
	}

	/**
	 * This method used type value in to text box or text area
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param testdata
	 *            : Value wish to type in text box / text area
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public static boolean type(By locator, String testdata, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.findElement(locator).clear();
			remoteWebDriverForAppium.findElement(locator).sendKeys(testdata);
			flag = true;

		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("Type ",
						"Data typing action is not perform on " + locatorName
						+ " with data is " + testdata);
				return true;
			} else if (b && flag) {

				Reporter.SuccessReport("Type ",
						"Data typing action is performed on" + locatorName
						+ " with data is " + testdata);

			}
		}
		return flag;
	}
	
	public static boolean gentype(By locator, String testdata, String locatorName)
			throws Throwable {
		boolean flag = false;
		if( platformOverride != null)	
		{	
			platform = platformOverride;
		   //platform = System.getProperty("Platform_overide") ;
		}
		else
		{
		 platform=configProps.getProperty("platformType");
		}
		
		try {
			if(platform.equalsIgnoreCase("Mobile"))
			{
			  remoteWebDriverForAppium.findElement(locator).click();
			  remoteWebDriverForAppium.findElement(locator).sendKeys(testdata);
		      flag = true;
			}
			
			else if(platform.equalsIgnoreCase("Web"))
			{
				driver.findElement(locator).clear();
				driver.findElement(locator).sendKeys(testdata);
		    	flag = true;
			}
		platformOverride = null;

		} catch (Exception e) {
			platformOverride = null;

		} finally {
			if (!flag) {
				Reporter.failureReport("Type ",
						"Data typing action is not perform on " + locatorName
						+ " with data is " + testdata);
				return true;
			} else if (b && flag) {

				Reporter.SuccessReport("Type ",
						"Data typing action is performed on" + locatorName
						+ " with data is " + testdata);

			}
		}
		return flag;
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link,menus etc..)
	 * 
	 */
	public static boolean mouseover(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = remoteWebDriverForAppium.findElement(locator);
			new Actions(remoteWebDriverForAppium).moveToElement(mo).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("MouseOver",
						"MouseOver action is not perform on" + locatorName);

			} else if (b && flag) {

				Reporter.SuccessReport("MouseOver",
						"MouserOver Action is Done on" + locatorName);
			}
		}
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves by a given offset, then releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param xOffset
	 *            : Horizontal move offset.
	 * 
	 * @param yOffset
	 *            : Vertical move offset.
	 * 
	 */
	public static boolean draggable(By source, int x, int y, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {

			WebElement dragitem = remoteWebDriverForAppium.findElement(source);
			new Actions(remoteWebDriverForAppium).dragAndDropBy(dragitem, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Draggable",
						"Draggable action is not performed on" + locatorName);

			} else if (b && flag) {

				Reporter.SuccessReport("Draggable",
						"Draggable Action is Done on" + locatorName);
			}
		}
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves to the location of the target element, then
	 * releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param target
	 *            : Element to move to and release the mouse at.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Button,image etc..)
	 * 
	 */
	public static boolean draganddrop(By source, By target, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement from = remoteWebDriverForAppium.findElement(source);
			WebElement to = remoteWebDriverForAppium.findElement(target);
			new Actions(remoteWebDriverForAppium).dragAndDrop(from, to).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("DragAndDrop",
						"DragAndDrop action is not perform on" + locatorName);

			} else if (b && flag) {

				Reporter.SuccessReport("DragAndDrop",
						"DragAndDrop Action is Done on" + locatorName);
			}
		}
	}

	/**
	 * To slide an object to some distance
	 * 
	 * @param slider
	 *            : Action to be performed on element
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public static boolean slider(By slider, int x, int y, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement dragitem = remoteWebDriverForAppium.findElement(slider);
			// new Actions(remoteWebDriverForAppium).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(remoteWebDriverForAppium).dragAndDropBy(dragitem, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Slider",
						"Slider action is not perform on" + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.SuccessReport("Slider", "Slider Action is Done on"
						+ locatorName);
			}
		}
	}

	/**
	 * To right click on an element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @throws Throwable
	 */

	public static boolean rightclick(By by, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement elementToRightClick = remoteWebDriverForAppium.findElement(by);
			Actions clicker = new Actions(remoteWebDriverForAppium);
			clicker.contextClick(elementToRightClick).perform();
			flag = true;
			return true;
			// remoteWebDriverForAppium.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("RightClick",
						"RightClick action is not perform on" + locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("RightClick",
						"RightClick Action is Done on" + locatorName);
			}
		}
	}

	/**
	 * Wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */

	public static boolean waitForTitlePresent(By locator) throws Throwable {

		boolean flag = false;
		boolean bValue = false;

		try {
			for (int i = 0; i < 200; i++) {
				if (remoteWebDriverForAppium.findElements(locator).size() > 0) {
					flag = true;
					bValue = true;
					break;
				} else {
					remoteWebDriverForAppium.wait(50);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForTitlePresent", "Title is wrong");

			} else if (b && flag) {
				Reporter.SuccessReport("WaitForTitlePresent",
						"Launched successfully expected Title");
			}
		}
		return bValue;
	}

	/**
	 * Wait for an ElementPresent
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return Whether or not the element is displayed
	 */
	public static boolean waitForElementPresent(By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
			for (int i = 0; i < 60; i++) {
				if (remoteWebDriverForAppium.findElement(by).isDisplayed()) {
					flag = true;
					return true;

				} else {
					Thread.sleep(1000);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		finally {
			if (!flag) {
				Reporter.failureReport("WaitForElementPresent",
						"Falied to locate element " + locator);
			} else if (b && flag) {
				Reporter.SuccessReport("WaitForElementPresent",
						"Successfullly located element " + locator);
			}
		}

		return flag;

	}
	
	
	
	public static boolean waitForElementPresent(By by, String locator, int secs)
			throws Throwable {
		boolean flag = false;
		if( platformOverride != null)	
		{	
			platform = platformOverride;
		   //platform = System.getProperty("Platform_overide") ;
		}
		else
		{
		 platform=configProps.getProperty("platformType");
		}
		
		try {
			if(platform.equalsIgnoreCase("Mobile"))
			{
			for (int i = 0; i < secs/2; i++) {
				List<WebElement> elements = remoteWebDriverForAppium.findElements(by);
				if (elements.size()>0) {
					flag = true;
					return true;

				} 
				else {
					remoteWebDriverForAppium.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				}
			}
	        }
			else if(platform.equalsIgnoreCase("Web"))
			{
				for (int i = 0; i < secs/2; i++) {
					List<WebElement> elements = driver.findElements(by);
					if (elements.size()>0) {
						flag = true;
						return true;

					} 
					else {
							driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					}
				}
				
			}
			
		//	platformOverride = null;
		} 
		catch (Exception e) {
			//platformOverride = null;
			//e.printStackTrace();
			return false;
		} 
		/*finally{
			System.out.println("Wait for element : "+by);
			System.out.println(String.valueOf(flag).toUpperCase());
		}*/
		finally {
			if (!flag) {
				Reporter.failureReport("ClickAndWaitForElementPresent",
						"Failed to perform clickAndWaitForElementPresent action");
			} else if (b && flag) {
				Reporter.SuccessReport("ClickAndWaitForElementPresent",
						"successfully performed clickAndWaitForElementPresent action");
			}
		}
		return flag;
		
	}

	/**
	 * This method Click on element and wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param waitElement
	 *            : Element name wish to wait for that (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 */
	public static boolean clickAndWaitForElementPresent(By locator,
			By waitElement, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			click(locator, locatorName);
			waitForElementPresent(waitElement, locatorName);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("ClickAndWaitForElementPresent",
						"Failed to perform clickAndWaitForElementPresent action");
			} else if (b && flag) {
				Reporter.SuccessReport("ClickAndWaitForElementPresent",
						"successfully performed clickAndWaitForElementPresent action");
			}
		}
	}

	/**
	 * Select a value from Dropdown using send keys
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish type in dropdown list
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public static boolean selectBySendkeys(By locator, String value,
			String locatorName) throws Throwable {

		boolean flag = false;
		try {
			remoteWebDriverForAppium.findElement(locator).sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select", value
						+ "is Not Select from the DropDown " + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.SuccessReport("Select", value
						+ " is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param index
	 *            : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public static boolean selectByIndex(By locator, int index,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(remoteWebDriverForAppium.findElement(locator));
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select", "Option at index " + index
						+ " is Not Select from the DropDown" + locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("Select", "Option at index " + index
						+ "is Selected from the DropDown" + locatorName);
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public static boolean selectByValue(By locator, String value,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(remoteWebDriverForAppium.findElement(locator));
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select",
						"Option with value attribute" + value
						+ " is Not Select from the DropDown"
						+ locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("Select",
						"Option with value attribute" + value
						+ " is  Selected from the DropDown"
						+ locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param visibletext
	 *            : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public static boolean selectByVisibleText(By locator, String visibletext,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(remoteWebDriverForAppium.findElement(locator));
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select", visibletext
						+ " is Not Select from the DropDown" + locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("Select", visibletext
						+ "  is Selected from the DropDown" + locatorName);
			}
		}
	}

	/**
	 * SWITCH TO WINDOW BY USING TITLE
	 * 
	 * @param windowTitle
	 *            : Title of window wish to switch
	 * 
	 * @param count
	 *            : Selenium launched Window id (integer no)
	 * 
	 * @return: Boolean value(true or false)
	 * 
	 */
	//
	public static boolean switchWindowByTitle(String windowTitle, int count)
			throws Throwable {
		boolean flag = false;
		try {
			//			Set<String> windowList = remoteWebDriverForAppium.getWindowHandles();
			//			int windowCount = windowList.size();
			// Calendar calendar = new GregorianCalendar();
			// int second = calendar.get(Calendar.SECOND); // /to get current
			// time
			// int timeout = second + 40;
			/*
			 * while (windowCount != count && second < timeout) {
			 * Thread.sleep(500); windowList = remoteWebDriverForAppium.getWindowHandles();
			 * windowCount = windowList.size();
			 * 
			 * }
			 */

			//			String[] array = windowList.toArray(new String[0]);

			//			for (int i = 0; i <= windowCount; i++) {
			//
			//				remoteWebDriverForAppium.switchTo().window(array[count - 1]);
			//
			//				// if (remoteWebDriverForAppium.getTitle().contains(windowTitle))
			//				flag = true;
			//				return true;
			//			}
			return false;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectWindow",
						"The Window with title" + windowTitle
						+ " is not Selected");

			} else if (b && flag) {
				Reporter.SuccessReport("SelectWindow",
						"Focus navigated to the window with title"
								+ windowTitle);
			}
		}
	}

	/**
	 * Function To get column count and print data in Columns
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: Returns no of columns.
	 * 
	 */
	public static int getColumncount(By locator) throws Exception {

		WebElement tr = remoteWebDriverForAppium.findElement(locator);
		List<WebElement> columns = tr.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;

	}

	/**
	 * Function To get row count and print data in rows
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: returns no of rows.
	 */
	public static int getRowCount(By locator) throws Exception {

		WebElement table = remoteWebDriverForAppium.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	public static boolean Alert() throws Throwable {
		boolean flag = false;
		boolean presentFlag = false;
		Alert alert = null;

		try {

			// Check the presence of alert
			alert = remoteWebDriverForAppium.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (presentFlag) {
				Reporter.failureReport("Alert", "There was no alert to handle");
			} else if (b && flag) {
				Reporter.SuccessReport("Alert",
						"The Alert is handled successfully");
			}
		}

		return presentFlag;
	}

	/**
	 * To launch URL
	 * 
	 * @param url
	 *            : url value want to launch
	 * @throws Throwable
	 * 
	 */
	public static boolean launchUrl(String url) throws Throwable {
		boolean flag = false;
		try {

			// getResponseCode(url);
			remoteWebDriverForAppium.manage().deleteAllCookies();
			remoteWebDriverForAppium.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Launching URL ", "Failed to launch"
						+ url);
			} else if (b && flag) {
				Reporter.SuccessReport("Launching URL ",
						"Successfully launched" + url);
			}
		}
	}

	/*
	 * public static int getResponseCode(String url) { try { return
	 * Request.Get(url).execute().returnResponse().getStatusLine()
	 * .getStatusCode(); } catch (Exception e) { throw new RuntimeException(e);
	 * } }
	 */
	/**
	 * This method verify check box is checked or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:sign in Checkbox etc..)
	 * 
	 * @return: boolean value(True: if it is checked, False: if not checked)
	 * 
	 */
	public static boolean isChecked(By locator, String locatorName)
			throws Throwable {
		boolean bvalue = false;
		boolean flag = false;
		try {
			if (remoteWebDriverForAppium.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}

		} catch (NoSuchElementException e) {

			bvalue = false;
		} finally {
			if (!flag) {
				Reporter.SuccessReport("IsChecked", locatorName
						+ " is Selected");
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.failureReport("IsChecked", locatorName
						+ " is not Select");
			}
		}
		return bvalue;
	}

	/**
	 * Element is enable or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, UserName
	 *            Textbox etc..)
	 * 
	 * @return: boolean value (True: if the element is enabled, false: if it not
	 *          enabled).
	 * 
	 */

	public static boolean isEnabled(By locator, String locatorName)
			throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {
			if (remoteWebDriverForAppium.findElement(locator).isEnabled()) {
				flag = true;
				value = true;
			}

		} catch (Exception e) {

			flag = false;

		} finally {
			if (!flag) {
				Reporter.failureReport("IsEnabled", locatorName
						+ " is not Enabled");

			} else if (b && flag) {
				Reporter.SuccessReport("IsEnabled", locatorName + " is Enable");
			}
		}
		return value;
	}

	/**
	 * Element visible or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @return: boolean value(True: if the element is visible, false: If element
	 *          not visible)
	 * 
	 */

	public static boolean isVisible(By locator, String locatorName)
			throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {

			value = remoteWebDriverForAppium.findElement(locator).isDisplayed();
			value = true;
			flag = true;
		} catch (Exception e) {
			flag = false;
			value = false;

		} finally {
			if (!flag) {
				Reporter.failureReport("IsVisible", locatorName
						+ " Element is Not Visible");
			} else if (b && flag) {
				Reporter.SuccessReport("IsVisible", locatorName
						+ " Element is Visible");

			}
		}
		return value;
	}

	/**
	 * Get the CSS value of an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, label color
	 *            etc..)
	 * 
	 * @param cssattribute
	 *            : CSS attribute name wish to verify the value (id, name,
	 *            etc..)
	 * 
	 * @return: String CSS value of the element
	 * 
	 */

	public static String getCssValue(By locator, String cssattribute,
			String locatorName) throws Throwable {
		String value = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, "locatorName")) {
				value = remoteWebDriverForAppium.findElement(locator).getCssValue(cssattribute);
				flag = true;
			}
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("GetCssValue",
						"Was able to get Css value from" + locatorName);

			} else if (b & flag) {
				Reporter.SuccessReport("GetCssValue",
						"Was not able to get Css value from" + locatorName);
			}
		}
		return value;
	}

	/**
	 * Check the expected value is available or not
	 * 
	 * @param expvalue
	 *            : Expected value of attribute
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name of element wish to assert
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public static boolean assertValue(String expvalue, By locator,
			String attribute, String locatorName) throws Throwable {

		boolean flag = false;
		try {
			Assert.assertEquals(expvalue,
					getAttribute(locator, attribute, locatorName));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("AssertValue", locatorName
						+ " present in the page");
				return false;
			} else if (b & flag) {
				Reporter.SuccessReport("AssertValue", locatorName
						+ " is not present in the page");
			}
		}
		return flag;
	}

	/**
	 * Check the text is presnt or not
	 * 
	 * @param text
	 *            : Text wish to assert on the page.
	 * 
	 */
	public static boolean assertTextPresent(String text) throws Throwable {
		boolean flag = false;
		try {
			Assert.assertTrue(isTextPresent(text));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("AssertTextPresent", text
						+ " present in the page");
				return false;
			} else if (b & flag) {
				Reporter.SuccessReport("AssertTextPresent", text
						+ " is not present in the page");
			}
		}
		return flag;
	}

	/**
	 * Assert element present or not
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public static boolean assertElementPresent(By by, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			Assert.assertTrue(isElementPresent(by, locatorName));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("AssertElementPresent", locatorName
						+ " present in the page");
				return false;
			} else if (b & flag) {
				Reporter.SuccessReport("AssertElementPresent", locatorName
						+ " is not present in the page");
			}
		}
		return flag;

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 */

	public static boolean assertText(By by, String text) throws Throwable {
		boolean flag = false;
		try {
			Assert.assertEquals(getText(by, text).trim(), text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("AssertText", text
						+ " is not present in the element ");
				return false;

			} else if (b && flag) {
				Reporter.SuccessReport("AssertText", text
						+ " is  present in the element ");
			}
		}

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public static boolean verifyText(By by, String text, String locatorName)
			throws Throwable {
		boolean flag = false;

		try {

			String vtxt = getText(by, locatorName).trim();
			vtxt.equals(text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("VerifyText", text
						+ " is not present in the location" + locatorName);
				flag = true;
			} else if (b && flag) {
				Reporter.SuccessReport("VerifyText", text
						+ " is present in the location" + locatorName);
				flag = false;
			}
		}
	}

	/**
	 * @return: return title of current page.
	 * 
	 * @throws Throwable
	 */

	public static String getTitle() throws Throwable {

		String text = remoteWebDriverForAppium.getTitle();
		if (b) {
			Reporter.SuccessReport("Title", "Title of the page is" + text);
		}
		return text;
	}

	/**
	 * Assert Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public static boolean asserTitle(String title) throws Throwable {
		boolean flag = false;

		try {
			By windowTitle = By.xpath("//title[contains(text(),'" + title
					+ "')]");
			if (waitForTitlePresent(windowTitle)) {
				Assert.assertEquals(getTitle(), title);
				flag = true;
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {

			if (!flag) {
				Reporter.failureReport("AsserTitle",
						"Page title is not matched with" + title);
				return false;
			} else if (b && flag) {
				Reporter.SuccessReport("AsserTitle",
						" Page title is verified with" + title);
			}
		}

	}

	/**
	 * Verify Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public static boolean verifyTitle(String title) throws Throwable {

		boolean flag = false;

		try {
			getTitle().equals(title);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		}

		finally {
			if (!flag) {
				Reporter.failureReport("VerifyTitle",
						"Page title is not matched with" + title);

			} else if (b && flag) {
				Reporter.SuccessReport("VerifyTitle",
						" Page title is verified with" + title);

			}
		}
	}

	/**
	 * Verify text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on the current page.
	 * 
	 */
	public static boolean verifyTextPresent(String text) throws Throwable {
		boolean flag = false;
		;
		if (!(remoteWebDriverForAppium.getPageSource()).contains(text)) {

			Reporter.failureReport("VerifyTextPresent", text
					+ " is not present in the page");
			flag = false;
		} else if (b && flag) {
			Reporter.SuccessReport("VerifyTextPresent", text
					+ " is present in the page");
			flag = true;

		}
		return flag;
	}

	/**
	 * Get the value of a the given attribute of the element.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name wish to assert the value.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:label, SignIn Link etc..)
	 * 
	 * @return: String attribute value
	 * 
	 */

	public static String getAttribute(By by, String attribute,
			String locatorName) throws Throwable {
		String value = "";
		if (isElementPresent(by, locatorName)) {
			value = remoteWebDriverForAppium.findElement(by).getAttribute(attribute);
		}
		return value;
	}

	/**
	 * Text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on current page
	 * 
	 * @return: boolean value(true: if Text present, false: if text not present)
	 */

	public static boolean isTextPresent(String text) throws Throwable {

		boolean value = remoteWebDriverForAppium.getPageSource().contains(text);
		System.out.println("is text "+text+" present  " + value);
		boolean flag = false;
		if (!value) {
			Reporter.failureReport("IsTextPresent", text
					+ " is  not present in the page");

			return false;
		} else if (b && flag) {
			Reporter.SuccessReport("IsTextPresent", "'" + text + "'"
					+ " is present in the page");

			return true;
		}
		return value;
	}

	/**
	 * The innerText of this element.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:label text, SignIn Link
	 *            etc..)
	 * 
	 * @return: String return text on element
	 * 
	 */

	public static String getText(By locator, String locatorName)
			throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, locatorName)) {
				text = remoteWebDriverForAppium.findElement(locator).getText();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.warningReport("GetText", "Unable to get Text from"
						+ locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("GetText", "Able to get Text from"
						+ locatorName);
			}
		}
		return text;
	}

	public static String getValue(String locator, String locatorName)
			throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (remoteWebDriverForAppium.findElement(By.xpath(locator)).isDisplayed()) {
				text = remoteWebDriverForAppium.findElement(By.xpath(locator)).getAttribute(
						"value");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("GetValue", "Unable to get Text from"
						+ locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("GetValue", "Able to get Text from"
						+ locatorName);
			}
		}
		return text;
	}

	public static int getElementsSize(By locator, String locatorName)
			throws Throwable {
		int text = 0;
		try {
			if (remoteWebDriverForAppium.findElement(locator).isDisplayed()) {
				text = remoteWebDriverForAppium.findElements(locator).size();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return text;
	}

	/**
	 * Capture Screenshot
	 * 
	 * @param fileName
	 *            : FileName screenshot save in local directory
	 * 
	 */
	
	
	public static void screenShot1(String fileName) 
	{
		 File scrFile = ((TakesScreenshot) remoteWebDriverForAppium).getScreenshotAs(OutputType.FILE);
		try 
		{			
			FileUtils.copyFile(scrFile, new File(fileName));
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	} 



	/**
	 * Click on the Link
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, menu's
	 *            etc..)
	 */

	public static boolean mouseHoverByJavaScript(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = remoteWebDriverForAppium.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) remoteWebDriverForAppium;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("MouseOver",
						"MouseOver action is not perform on" + locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("MouseOver",
						"MouserOver Action is Done on" + locatorName);
			}
		}
	}

	public static boolean JSClick(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement element = remoteWebDriverForAppium.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) remoteWebDriverForAppium;
			executor.executeScript("arguments[0].click();", element);
			// remoteWebDriverForAppium.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {


		} finally {
			if (!flag) {
				Reporter.failureReport("MouseOver",
						"MouseOver action is not perform on" + locatorName);
				return flag;
			} else if (b && flag) {
				Reporter.SuccessReport("MouseOver",
						"MouserOver Action is Done on" + locatorName);
				return flag;
			}
		}
		return flag;
	}

	/**
	 * This method switch the focus to selected frame using frame index
	 * 
	 * @param index
	 *            : Index of frame wish to switch
	 * 
	 */
	public static boolean switchToFrameByIndex(int index) throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame", "Frame with index "
						+ index + " is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame", "Frame with index "
						+ index + " is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue
	 *            : Frame ID wish to switch
	 * 
	 */
	public static boolean switchToFrameById(String idValue) throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame", "Frame with Id "
						+ idValue + " is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame", "Frame with Id "
						+ idValue + " is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 */
	public static boolean switchToFrameByName(String nameValue)
			throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame", "Frame with Name "
						+ nameValue + " is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame", "Frame with Name "
						+ nameValue + " is selected");
			}
		}
	}

	/**
	 * This method switch the to Default Frame.
	 * 
	 * @throws Throwable
	 */
	public static boolean switchToDefaultFrame() throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame",
						"The Frame is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame",
						"Frame with Name is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * 
	 */
	public static boolean switchToFrameByLocator(By lacator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.switchTo().frame(remoteWebDriverForAppium.findElement(lacator));
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame", "The Frame "
						+ locatorName + " is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame", "Frame with Name "
						+ locatorName + " is selected");
			}
		}
	}

	/**
	 * This method wait selenium until element present on web page.
	 */
	public static void ImplicitWait() {

		remoteWebDriverForAppium.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void waitUntilTextPresents(By by) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(by,
				"Time left: 7 seconds"));
	}

	/**
	 * Click on Element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * @throws StaleElementReferenceException
	 *             - If the element no longer exists as initially defined
	 */

	public static boolean click1(WebElement locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Click ", "Unable to click on "
						+ locatorName);
				return false;
			} else if (b && flag) {
				Reporter.SuccessReport("Click ", "able to click on "
						+ locatorName);
				return true;
			}
		}

	}

	/**
	 * 
	 * This method wait driver until given driver time.
	 * 
	 */
	public static WebDriverWait driverwait() {

		WebDriverWait wait = new WebDriverWait(remoteWebDriverForAppium, 30);
		return wait;
	}

	/**
	 * This method wait selenium until visibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @throws Throwable
	 * 
	 */

	public static boolean waitForVisibilityOfElement(By by, String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(remoteWebDriverForAppium, 15);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForVisibilityOfElement", "Element "
						+ locator + " is not visible");
			} else if (b && flag) {
				Reporter.SuccessReport("WaitForVisibilityOfElement", "Element "
						+ locator + "  is visible");
			}
		}
	}

	/**
	 * This method wait driver until Invisibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */
	public static boolean waitForInVisibilityOfElement(By by, String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(remoteWebDriverForAppium, 30);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForInVisibilityOfElement",
						"Element  " + locator + " is  visible");
			} else if (b && flag) {
				Reporter.SuccessReport("WaitForInVisibilityOfElement",
						"Element  " + locator + " is not visible");
			}
		}

	}

	public static List<WebElement> getElements(By locator) {

		List<WebElement> ele = remoteWebDriverForAppium.findElements(locator);

		return ele;
	}

	public static boolean assertTextMatching(By by, String text,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			String ActualText = getText(by, text).trim();
			if (ActualText.contains(text)) {
				flag = true;
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Verify " + locatorName, text
						+ " is not present in the element");
				return false;

			} else if (b && flag) {
				Reporter.SuccessReport("Verify " + locatorName, text
						+ " is  present in the element ");
			}
		}

	}

	// QuickFlix Funcations added

	public static boolean isElementDisplayed(WebElement element)
			throws Throwable {
		boolean flag = false;
		for (int i = 0; i < 200; i++) {
			if (element.isDisplayed()) {
				flag = true;
				break;
			} else {
				Thread.sleep(50);
			}
		}
		return flag;
	}

	public static void executeJavaScriptOnElement(String script) {
		((JavascriptExecutor) remoteWebDriverForAppium).executeScript(script);
	}

	public static void closeBrowser() {
		remoteWebDriverForAppium.close();
		remoteWebDriverForAppium.quit();
	}

	public static boolean hitKey(By locator, Keys keyStroke, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			remoteWebDriverForAppium.findElement(locator).sendKeys(keyStroke);
			flag = true;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			if (flag) {
				// Reporter.SuccessReport("Type ","Data typing action is performed on"
				// + locatorName+" with data is "+testdata);

			} else {
				Reporter.failureReport("Type ",
						"Data typing action is not perform on" + locatorName
						+ " with data is " + keyStroke);

			}
		}
	}

	public static Collection<WebElement> getWebElementsByTagInsideLocator(
			By locator, String tagName, String locatorName) throws Throwable {
		boolean flag = false;
		Collection<WebElement> elements;
		try {
			WebElement element = remoteWebDriverForAppium.findElement(locator);
			elements = element.findElements(By.tagName(tagName));
			flag = true;
		} catch (NoSuchElementException e) {
			throw e;
		} finally {
			if (!flag) {
				Reporter.failureReport("Type ",
						"Data typing action is not perform on" + locatorName
						+ " with data is " + tagName);
			}
		}
		return elements;
	}
	
	
	public static void mouseOverElement(WebElement element, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			new Actions(remoteWebDriverForAppium).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("MouseOver",
						"MouseOver action is not perform on" + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else {
				// Reporter.SuccessReport("MouseOver",
				// "MouserOver Action is Done on" + locatorName);
			}
		}
	}
	
	
}
