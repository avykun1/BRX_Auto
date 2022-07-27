
package utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.codehaus.plexus.util.dag.TopologicalSorter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import configuration.Common;


public class CommonMethods {
	public static Select dropdown;
	public WebDriver driver;
	static WebElement element;
	public static String caseid;
	public static String ui, currentWindow;
	public static ArrayList<String> names2 = new ArrayList<String>();
	public static ArrayList<String> names3 = new ArrayList<String>();
	// Adding static variable for tables .
	public static List<WebElement> trRowHolder;


	public static void fnAlertPopUpValidate(String strTextToVerify) throws InterruptedException {
		String strGetPopUpText = null;
		try {
			if (fnWaitForAlert(30)) {
				Alert objAlert = Browser.driver.switchTo().alert();
				strGetPopUpText = objAlert.getText().trim();
				System.out.println("UI Text is--" + strGetPopUpText);
				objAlert.accept();
				if (strGetPopUpText.contains(strTextToVerify)) {
					System.out.println(strTextToVerify + " PopUp Text Validated Successfully");
				} else {
					System.out.println(strTextToVerify + " PopUp Text Not Validated Successfully");
					CommonMethods.fnTestResult("fnAlertPopUpValidate","PopUp Text Not Validated Successfully Actual [" + strGetPopUpText
							+ "] Vs expected [" + strTextToVerify + "]", "failed");
				}
			} else {
				System.out.println("Popup is missing.");
				CommonMethods.fnTestResult("fnAlertPopUpValidate","PopUp is missing to validated-->" + strGetPopUpText, "failed");
			}

		} catch (Exception e) {
			System.out
					.println("Exception while validating PopUp Text-->" + strTextToVerify + ". Exception Details" + e);
			e.printStackTrace();
			CommonMethods.fnTestResult("fnAlertPopUpValidate","Exception while validating PopUp Text-->" + strTextToVerify + ".", "failed");
		}
	}

	
	public static void fnAlertPopUpValidateCancel(String strTextToVerify) throws InterruptedException {
		String strGetPopUpText = null;
		try {
			/*
			 * fnWaitForAlert(20000); if (CommonMethods.fnIsAlertPresent()) {
			 */
			if (fnWaitForAlert(30)) {
				Alert alert = Browser.driver.switchTo().alert();
				strGetPopUpText = alert.getText().trim();
				System.out.println(strGetPopUpText);
				alert.dismiss();
				if (strGetPopUpText.contains(strTextToVerify)) {
					System.out.println(strGetPopUpText + " PopUp Text Validated Successfully");
				} else {
					System.out.println(strGetPopUpText + " PopUp Text Not Validated Successfully");
					CommonMethods.fnTestResult("fnAlertPopUpValidate","PopUp Text Not Validated Successfully Actual [" + strGetPopUpText
							+ "] Vs expected [" + strTextToVerify + "]", "failed");
				}
			} else {
				System.out.println("Popup is missing.");
				CommonMethods.fnTestResult("fnAlertPopUpValidateCancel","PopUp is missing to validated-->" + strGetPopUpText, "failed");
			}
		} catch (Exception e) {
			System.out
					.println("Exception while validating PopUp Text-->" + strGetPopUpText + ". Exception Details" + e);
			e.printStackTrace();
			CommonMethods.fnTestResult("fnAlertPopUpValidateCancel","Exception occured while validate the popup value : " + strTextToVerify + ".",
					"failed");
		}
	}

	
	public static void fnAlertPopUpSave() throws InterruptedException {
		String strPopUpText = null;
		try {
			/*
			 * fnWaitForAlert(20000); if (CommonMethods.fnIsAlertPresent()) {
			 */
			if (fnWaitForAlert(30)) {
				Alert objAlert = Browser.driver.switchTo().alert();
				strPopUpText = objAlert.getText().trim();
				objAlert.accept();
				System.out.println(strPopUpText + "-->OK Button Clicked on POP");
			if(CommonMethods.fnIsAlertPresent()){
				objAlert.accept();	//NEWLY ADDED
			}
		//objAlert.accept();
			} else {
				System.out.println("Popup is missing.");
				CommonMethods.fnTestResult("fnAlertPopUpSave","PopUp is missing to validated-->" + strPopUpText, "failed");
			}
		} catch (Exception e) {
			System.out.println("Exception occur while Clicking on POP-->" + strPopUpText + ". Exception Details" + e);
			CommonMethods.fnTestResult("fnAlertPopUpSave",
					"Exception occur while Clicking on POP-->" + strPopUpText + ". Exception Details", "failed");
		}
	}

	public static boolean fnWaitForAlert(int intMaxWaitTime) {
		boolean foundAlert = false;
		try {
			WebDriverWait wait = new WebDriverWait(Browser.driver, intMaxWaitTime);
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException e) {
			System.out.println("Wait for alert is not working" + e.getMessage());
			foundAlert = false;
		}
		return foundAlert;
	}

	public static String fnGetCurrentUrl() {
		String str = null;
		try {
			str=Browser.driver.getCurrentUrl();
			
		} catch (TimeoutException e) {
			System.out.println("Not able to get current URL" + e.getMessage());
			
		}
		return str;
	}

	
	public static void fnAlertPopUpCancel() throws InterruptedException {
		String strPopUpText = null;
		try {
			/*
			 * fnWaitForAlert(20000); if (CommonMethods.fnIsAlertPresent()) {
			 */
			if (fnWaitForAlert(30)) {
				Alert objAlert = Browser.driver.switchTo().alert();
				strPopUpText = objAlert.getText().trim();
				objAlert.dismiss();
				System.out.println(strPopUpText + "-->Cancel Button Clicked on POP");
			} else {
				System.out.println("Popup is missing.");
				CommonMethods.fnTestResult("fnAlertPopUpCancel","PopUp is missing to validated-->" + strPopUpText, "failed");
			}
		} catch (Exception e) {
			System.out.println("Exception occur while Clicking on POP-->" + strPopUpText + ". Exception Details" + e);
			CommonMethods.fnTestResult("fnAlertPopUpCancel", "Exception occur while Clicking on POP-->" + strPopUpText + ". Exception Details", "failed");
		}
	}

	
	public static void fnClickOnButton(String strLocatorType, String strLocatorInfo) throws InterruptedException {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			element.click();
			fnWaitForPageToLoad(30);
			System.out.println("Clicked on Button-->" + strLocatorInfo);
		} catch (Exception e) {
			System.out.println("Exception occur while clicking on button-->" + strLocatorInfo);
			e.printStackTrace();
			CommonMethods.fnTestResult("fnClickOnButton","Exception occur while Click on button", "failed");
		}
	}
	
	public static void fnClickButton_Enabled(String strLocatorType, String strLocatorInfo) throws InterruptedException {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			element.click();
			fnWaitForPageToLoad(30);
			System.out.println("Clicked on Button-->" + strLocatorInfo);
		} catch (Exception e) {
			System.out.println("Exception occur while clicking on button-->" + strLocatorInfo);
			e.printStackTrace();
			CommonMethods.fnTestResult("fnClickOnButton","Exception occur while Click on button", "failed");
		}
	}

	
	public static void fnClickOnLink(String strLocatorType, String strLocatorInfo) throws InterruptedException {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			element.click();
			System.out.println("Clicked on Link-->" + strLocatorInfo);
		} catch (Exception e) {
			System.out.println("Exception occur while clicking on Link-->" + strLocatorInfo);
			e.printStackTrace();
			CommonMethods.fnTestResult("fnClickOnLink","Exception occur while Clicking on Link.", "failed");
		}
	}

	
	public static void fnEnterText(String strLocatorType, String strLocatorInfo, String strText)
			throws InterruptedException {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			element.clear();
			element.sendKeys(strText);
			System.out.println("Text-->" + strText + " Entered in -->" + strLocatorInfo + " Field");
		} catch (Exception e) {
			System.out.println("Exception occur while entring Text-->" + strText + " in-->" + strLocatorInfo
					+ " Field. Exception Details" + e);
			CommonMethods.fnTestResult("fnEnterText","Exception occur while Enter Text", "failed");
		}
	}

	
	public static void fnClearText(String strLocatorType, String strLocatorInfo) throws InterruptedException {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			element.clear();
			System.out.println("Textbox-->" + strLocatorInfo + " Cleared");
		} catch (Exception e) {
			System.out.println("Exception occur while Clearing Textbox-->" + strLocatorInfo + " Exception Details" + e);
			e.printStackTrace();
			CommonMethods.fnTestResult("fnClearText","Exception occur while Clear Text.", "failed");
		}
	}

	
	public static void fnVerifyTextOnPage(String strLocatorType, String strText) throws Exception {
		try {
			boolean bolTextStatus = false;
			switch (strLocatorType) {
			case "title":
				bolTextStatus = Browser.driver.getTitle().equals(strText);
				break;
			case "text":
				bolTextStatus = Browser.driver.findElement(By.cssSelector("body")).getText().contains(strText);
				;
				break;
			case "id":
				bolTextStatus = Browser.driver.findElement(By.id(strText)).isDisplayed();
				break;
			case "name":
				bolTextStatus = Browser.driver.findElement(By.name(strText)).isDisplayed();
				break;
			case "linkText":
				bolTextStatus = Browser.driver.findElement(By.linkText(strText)).isDisplayed();
				break;
			case "linkValue":
				bolTextStatus = Browser.driver.findElement(By.partialLinkText(strText)).isDisplayed();
				break;
			case "css":
				bolTextStatus = Browser.driver.findElement(By.cssSelector(strText)).isDisplayed();
				break;
			case "class":
				bolTextStatus = Browser.driver.findElement(By.className(strText)).isDisplayed();
				break;
			case "xpath":
				bolTextStatus = Browser.driver.findElement(By.xpath(strText)).isDisplayed();
				break;
			}
			if (bolTextStatus == true) {
				System.out.println("Text-->" + strText + " Validated Successfully");
			} else {
				System.out.println("Text-->" + strText + " Not Validated Successfully");
				CommonMethods.fnTestResult("fnVerifyTextOnPage","Text-->" + strText + " Not Validated Successfully", "failed");
			}
		} catch (Exception e) {
			System.out.println("Exception occur while verifying Text-->" + strText + " . Exception Details" + e);
			CommonMethods.fnTestResult("fnVerifyTextOnPage","Exception occur while verifying Text--> " + strText + " ", "failed");
		}
	}

	/**
	 * @author vtarigop
	 * @param strLocatorType
	 * @param strText
	 * @description Function to verify Text Not on Page
	 * @creation 17-12-2016|Vikas Sahni|Reusable Function to verify Text Not on
	 *           Page
	 */
	public static void fnVerifyTextNotOnPage(String strLocatorType, String strText) {
		try {
			boolean bolTextStatus = false;
			// look for the requested element
			switch (strLocatorType) {
			case "title":
				bolTextStatus = Browser.driver.getTitle().equals(strText);
				break;
			case "text":
				bolTextStatus = Browser.driver.findElement(By.cssSelector("body")).getText().contains(strText);
				break;
			case "id":
				bolTextStatus = Browser.driver.findElement(By.id(strText)).isDisplayed();
				break;
			case "name":
				bolTextStatus = Browser.driver.findElement(By.name(strText)).isDisplayed();
				break;
			case "linkText":
				bolTextStatus = Browser.driver.findElement(By.linkText(strText)).isDisplayed();
				break;
			case "linkValue":
				bolTextStatus = Browser.driver.findElement(By.partialLinkText(strText)).isDisplayed();
				break;
			case "css":
				bolTextStatus = Browser.driver.findElement(By.cssSelector(strText)).isDisplayed();
				break;
			case "class":
				bolTextStatus = Browser.driver.findElement(By.className(strText)).isDisplayed();
				break;
			case "xpath":
				bolTextStatus = Browser.driver.findElement(By.xpath(strText)).isDisplayed();
				break;
			}
			if (bolTextStatus == false) {
				System.out.println("Text-->" + strText + " Not Exist on Page");
			} else {
				System.out.println("Text-->" + strText + " Exist on Page");
				CommonMethods.fnTestResult("fnVerifyTextNotOnPage"," Text matches on the Page.", "failed");
			}
		} catch (Exception e) {
			System.out.println("Exception occur while verifying Text-->" + strText + " . Exception Details" + e);
			CommonMethods.fnTestResult("fnVerifyTextNotOnPage","Exception occur while verify text not on page.", "failed");
		}
	}

	
	public static int fngenrateRandomNumber(int maxNum, int minNum) {
		int randomNumber;
		Random rn = new Random();
		int range = (maxNum - minNum) + 1;
		randomNumber = rn.nextInt(range) + minNum;

		return randomNumber;
	}

	
	public static String fngenrateRandomNumberDate() {
		// int randomNumber;
		/*
		 * Random rn = new Random(); int range = (maxNum - minNum)+ 1;
		 * randomNumber= rn.nextInt(range) + minNum;
		 */
		String strDate = fngenrateRandomNumber(12, 1) + "/" + fngenrateRandomNumber(28, 1) + "/"
				+ CommonMethods.fngenrateRandomNumber(2017, 2015);
		return strDate;
	}

	
	public static List<String> fnDropDowngetallOptions(String strLocatorType, String strLocatorName) {
		dropdown = null;
		String x = "";
		List<WebElement> allOptions = null;
		ArrayList<String> strallOptions = new ArrayList<String>();

		switch (strLocatorType) {
		case "id":
			dropdown = new Select(Browser.driver.findElement(By.id(strLocatorName)));
			break;
		case "name":
			dropdown = new Select(Browser.driver.findElement(By.name(strLocatorName)));
			break;
		case "xpath":
			dropdown = new Select(Browser.driver.findElement(By.xpath(strLocatorName)));
			break;
		}

		if (dropdown != null) {
			allOptions = dropdown.getOptions();

			System.out.println("-----" + allOptions.size());
			for (int i = 0; i < allOptions.size(); i++) {
				x = allOptions.get(i).getText();
				strallOptions.add(x);
			}

		}
		return strallOptions;
	}

	
	public static String fntextboxDefalutValue(String strLocatorType, String strLocatorName) {

		String txtdefault = "";
		switch (strLocatorType) {
		case "id":
			txtdefault = Browser.driver.findElement(By.id(strLocatorName)).getAttribute("value");
			break;
		case "name":
			txtdefault = Browser.driver.findElement(By.name(strLocatorName)).getAttribute("value");
			break;
		case "xpath":
			txtdefault = Browser.driver.findElement(By.xpath(strLocatorName)).getAttribute("value");
			break;
		}
		return txtdefault;
	}

	
	//Modified
	public static boolean fnDropDownDefalutValue(String strLocatorType, String strLocatorName, String Expvalue) {
		boolean bolStatus = true;
		dropdown = null;
		String actValue = "";
		switch (strLocatorType) {
		case "id":
			dropdown = new Select(Browser.driver.findElement(By.id(strLocatorName)));
			break;
		case "name":
			dropdown = new Select(Browser.driver.findElement(By.name(strLocatorName)));
			break;
		case "xpath":
			dropdown = new Select(Browser.driver.findElement(By.xpath(strLocatorName)));
			break;
		}
		if (dropdown != null) {
			actValue = dropdown.getFirstSelectedOption().getText();
			if (actValue.equalsIgnoreCase(Expvalue)) {
				System.out.println("Expected default value-- " + Expvalue + "-- is matching with actual default value--"
						+ actValue + "---");

			bolStatus = true;

			} else {
				System.out.println("Expected default value-- " + Expvalue
						+ "-- is not matching with actual default value--" + actValue + "---");

				bolStatus = false;
			}
		
		} else {
			bolStatus = false;
		}
		return bolStatus;
	}

	
	public static boolean fnDropDownSelectReturnStatus(String strLocatorType, String strLocatorName,
			String strDropDownPropertyType, String strDropDownValue) {
		boolean bolStatus = true;
		dropdown = null;
		try {
			switch (strLocatorType) {
			case "id":
				dropdown = new Select(Browser.driver.findElement(By.id(strLocatorName)));
				break;
			case "name":
				dropdown = new Select(Browser.driver.findElement(By.name(strLocatorName)));
				break;
			case "xpath":
				dropdown = new Select(Browser.driver.findElement(By.xpath(strLocatorName)));
				break;
			}
			if (dropdown != null) {
				switch (strDropDownPropertyType.toLowerCase()) {
				case "text":
					dropdown.selectByVisibleText(strDropDownValue);
					System.out.println("Drop Down Value Selected-->" + strDropDownValue);
					break;
				case "value":
					dropdown.selectByValue(strDropDownValue);
					System.out.println("Drop Down Value Selected-->" + strDropDownValue);
					break;
				case "index":
					dropdown.selectByIndex(Integer.parseInt(strDropDownValue));
					System.out.println("Drop Down Value Selected-->" + strDropDownValue);
					break;
				}
			} else {
				System.out.println("Drop Down Value Not Selected-->" + strDropDownValue);
				bolStatus = false;
			}
		} catch (Exception e) {
			System.out.println("[ERROR] occurred while seleting the dorpdown.");
			bolStatus = false;
		}
		return bolStatus;
	}

	// Select Drop Down Value if not exist Fail Test case
	public static void fnDropDownSelect(String strLocatorType, String strLocatorName, String strDropDownPropertyType,
			String strDropDownValue) {
		dropdown = null;
		try {
			switch (strLocatorType) {
			case "id":
				dropdown = new Select(Browser.driver.findElement(By.id(strLocatorName)));
				break;
			case "name":
				dropdown = new Select(Browser.driver.findElement(By.name(strLocatorName)));
				break;
			case "xpath":
				dropdown = new Select(Browser.driver.findElement(By.xpath(strLocatorName)));
				break;
			}
		
			if (dropdown != null) {
				switch (strDropDownPropertyType.toLowerCase()) {
				case "text":					
					dropdown.selectByVisibleText(strDropDownValue);					
					switch (strLocatorType) {
					case "id":
						dropdown = new Select(Browser.driver.findElement(By.id(strLocatorName)));
						break;
					case "name":
						dropdown = new Select(Browser.driver.findElement(By.name(strLocatorName)));
						break;
					case "xpath":
						dropdown = new Select(Browser.driver.findElement(By.xpath(strLocatorName)));
						break;
					}
					
					String temp = dropdown.getFirstSelectedOption().getText();
					if(temp.equals(strDropDownValue))
					  System.out.println("Drop Down Value Selected-->" + strDropDownValue);
					else
						dropdown.selectByVisibleText(strDropDownValue);
					break;
				case "value":
					dropdown.selectByValue(strDropDownValue);
					System.out.println("Drop Down Value Selected-->" + strDropDownValue);
					break;
				case "index":
					dropdown.selectByIndex(Integer.parseInt(strDropDownValue));
					System.out.println("Drop Down Value Selected-->" + strDropDownValue);
					break;
				}
				fnWaitForPageToLoad(60);
				Thread.sleep(2000);
			} else {
				System.out.println("Drop Down Value Not Selected-->" + strDropDownValue);
				//CommonMethods.fnTestResult("[ERROR] occurred while selecting from dorpdown.", "failed");
			}

		} catch (Exception e) {
			System.out.println("[ERROR] occurred while seleting the dorpdown-->" + strDropDownValue);
			e.printStackTrace();
			//CommonMethods.fnTestResult("[ERROR] occurred while selecting from dorpdown.", "failed");
		}

	}

	
	public static void fnUploadDocument(String strLocatorType, String strLocatorInfo, String strPath)
			throws InterruptedException {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			Thread.sleep(2000);
			element.sendKeys(strPath);
			System.out.println("Path Entered");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("Exceeption occurrend and Exception is: \n" + e.getMessage());
			//CommonMethods.fnTestResult("Exception occur while upload document.", "failed");
		}
	}


	public static void fnCompareDropDownValues(String strLocatorType, String strLocatorInfo)
			throws InterruptedException {
		switch (strLocatorType) {
		case "id":
			element = Browser.driver.findElement(By.id(strLocatorInfo));
			break;
		case "name":
			element = Browser.driver.findElement(By.name(strLocatorInfo));
			break;
		case "linkText":
			element = Browser.driver.findElement(By.linkText(strLocatorInfo));
			break;
		case "linkValue":
			element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
			break;
		case "css":
			element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
			break;
		case "class":
			element = Browser.driver.findElement(By.className(strLocatorInfo));
			break;
		case "xpath":
			element = Browser.driver.findElement(By.xpath(strLocatorInfo));
			break;
		}
		Select select = new Select(element);
		List<WebElement> objDropDown = select.getOptions();
		List<String> listDropDownValues = new ArrayList<String>();
		for (WebElement exp : objDropDown) {
			listDropDownValues.add(exp.getText());
		}
		List<String> listDropDownValuesSorted = listDropDownValues;
		Collections.sort(listDropDownValuesSorted);
		System.out.println("size" + listDropDownValues.size());
		System.out.println(listDropDownValues);
		System.out.println(listDropDownValuesSorted);
		System.out.println("Result Of Comparision" + listDropDownValues.equals(listDropDownValuesSorted));
	}


	public static void fnElementPresent(String strLocatorType, String strLocatorInfo) {
		try {
			switch (strLocatorType) {
			case "title":
				Browser.driver.getTitle().equals(strLocatorInfo);
				System.out.println("Element Present--->" + strLocatorInfo);
				break;
			case "text":
				boolean b = Browser.driver.getPageSource().contains(strLocatorInfo);
				if (b == true) {
					System.out.println("Element Present--->" + strLocatorInfo);
				}
				break;
			case "id":
				Browser.driver.findElement(By.id(strLocatorInfo));
				System.out.println("Element Present--->" + strLocatorInfo);
				break;
			case "name":
				Browser.driver.findElement(By.name(strLocatorInfo));
				System.out.println("Element Present--->" + strLocatorInfo);
				break;
			case "linkText":
				Browser.driver.findElement(By.linkText(strLocatorInfo));
				System.out.println("Element Present--->" + strLocatorInfo);
				break;
			case "linkValue":
				Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				System.out.println("Element Present--->" + strLocatorInfo);
				break;
			case "css":
				Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				System.out.println("Element Present--->" + strLocatorInfo);
				break;
			case "class":
				Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				Browser.driver.findElement(By.xpath(strLocatorInfo));
				System.out.println("Element Present--->" + strLocatorInfo);
				break;
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("ElementPresent","Exception occur while verify Element Present", "failed");
		}
	}

	
	public static void fnTestResult(String methodname, String strExcepted, String strResult) {
		try {
			Common.intTestStep += 1;

			String userMust = "	User must able to ";
			fnTestResult1(methodname,strExcepted, strResult);
			if (strResult.equalsIgnoreCase("passed")) 
			{
				CommonMethods.fnTakeScreenShot(strResult);
				System.out.println("Step:" + fnStrNumber(Common.intTestStep));
				System.out.println("Expected:" + userMust + strExcepted);
				System.out.println("Actual:" + "		User is able to " + strExcepted);
				System.out.println("Result:" + "		Passed");
				fnHtmlReport(strExcepted, strResult);				
				Reporter.log(strExcepted);
				try
				{
					ExtentListeners.testReport.get().log(Status.PASS, strExcepted);
				}
				catch(Exception ex)
				{
					
				}	
				
			} else {
				
				System.out.println("Step:" + fnStrNumber(Common.intTestStep) + userMust + strExcepted
						+ "		User is not able to " + strExcepted + "		Failed");
				fnHtmlReport(strExcepted, strResult);
				Reporter.log(strExcepted);
				CommonMethods.fnFailTest();
				try
				{
					ExtentListeners.testReport.get().log(Status.FAIL, "Step:" + fnStrNumber(Common.intTestStep) + userMust + strExcepted
								+ "		User is not able to " + strExcepted + "		Failed");
				}
				catch(Exception ex)
				{
					
				}
				Assert.fail("Step:" + fnStrNumber(Common.intTestStep) + userMust + strExcepted
						+ "		User is not able to " + strExcepted + "		Failed");
			}
		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			e.printStackTrace();
		}
		Common.intInternalCount = 0; // To Reset internal Step Counter.
	}

	public static String fnStrNumber(int intNumber) {
		if (intNumber < 10)
			return "0" + intNumber;
		else
			return "" + intNumber;
	}

	
	

	public static void fnConstructor() {
		try {
			System.out.println("Test Case : " + Common.className);
			System.out.println("\n");
			CommonMethods.fnMakedir(Common.className);
			Common.ExecTime = getcurrdate("5");
			System.out.println(Common.ExecTime);
		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("fnConstructor","Exception occur while fnConstructuor ", "failed");
		}
	}

	public static void fnMakedir(String strClassName) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			// get current date time with Date()
			Date date = new Date();
			
			String date2 = dateFormat.format(date).trim();
			
			
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();
			
			
			
			
			Common.StrScreenShotsFolder = strClassName + "_" + date2;
			String path = System.getProperty("user.dir").concat("/ScreenShots/");
			File dir = new File(path + Common.StrScreenShotsFolder);
			if (dir.exists()) {
				System.out.println("A folder with name 'new folder' is already exist in the path " + path);
			} else {
				dir.mkdir();
				Common.Path2 = dir.getAbsolutePath();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("fnMakedir","Exception occur while fnMakedir.", "failed");
		}
	}

	
	public static void fnTakeScreenShot(String strResult) {
		try {
			File snapshort_file = ((TakesScreenshot) Browser.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(snapshort_file, new File(
					Common.Path2 + "/Step" + fnStrNumber(Common.intTestStep) + "_" + strResult.toUpperCase() + ".png"));
		} catch (Exception e) {
			System.out.println("Exception Occured while Capture Screen Shot");
			e.printStackTrace();
		}
	}


	public static void fnCurrentScreenShot(String strDescription) {
		Common.intInternalCount += 1;
		String strTemp = "";
		try {
			if (strDescription.length() > 30)
				strTemp = strDescription.substring(0, 30);
			else
				strTemp = strDescription;

			File snapshort_file = ((TakesScreenshot) Browser.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(snapshort_file, new File(Common.Path2 + "/Step" + fnStrNumber(Common.intTestStep + 1)
					+ "_" + fnStrNumber(Common.intInternalCount) + "[" + strTemp + "].png"));
		} catch (Exception e) {
			System.out.println("Exception Occured while Capture Screen Shot");
			e.printStackTrace();
		}
	}

	
	public static void fnAlertScreenShot(String strDescription) {
		Common.intInternalCount += 1;
		String strTemp = "";
		try {
			if (strDescription.length() > 30)
				strTemp = strDescription.substring(0, 30);
			else
				strTemp = strDescription;

			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png",
					new File(Common.Path2 + "/Step" + CommonMethods.fnStrNumber(Common.intTestStep + 1) + "_"
							+ CommonMethods.fnStrNumber(Common.intInternalCount) + "[" + strTemp + "].png"));
		} catch (Exception e) {
			System.out.println("Exception Occured while Alert Capture Screen Shot");
			e.printStackTrace();
		}
	}


	public static String fnReturnDropDownSelectedValue(String strLocatorType, String strValue, String strProperty) {
		String status = null;
		try {
			dropdown = null;
			switch (strLocatorType) {
			case "id":
				dropdown = new Select(Browser.driver.findElement(By.id(strValue)));
				break;

			case "name":
				dropdown = new Select(Browser.driver.findElement(By.name(strValue)));
				break;

			case "xpath":
				dropdown = new Select(Browser.driver.findElement(By.xpath(strValue)));
				break;
			}
			Browser.driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			if (dropdown != null) {
				switch (strProperty.toLowerCase()) {
				case "selected":
					status = dropdown.getFirstSelectedOption().getAttribute("value");
					break;
				case "text":
					status = dropdown.getAllSelectedOptions().get(0).getText();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Wait for element is not not working" + e.getMessage());
		}
		return status;
	}

	
	public static String fnGetOnClickValue(String strLocatorType, String strLocatorInfo) {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
		} catch (Exception e) {
			System.out.println("Wait for element is not not working" + e.getMessage());
		}
		return element.getAttribute("onclick");

	}


	public static boolean fnIsElementChecked(String strLocatorType, String strLocatorInfo) {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;

			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;

			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;

			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;

			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;

			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;

			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
		} catch (Exception e) {
			System.out.println("Wait for element is not working" + e.getMessage());
		}
		return element.isSelected();
	}

	
	public static boolean fnDropDownAllSelect(String strLocatorType, String strLocatorInfo) {
		boolean bolSelect = false;
		try {
			dropdown = null;
			switch (strLocatorType) {
			case "id":
				dropdown = new Select(Browser.driver.findElement(By.id(strLocatorInfo)));
				break;
			case "name":
				dropdown = new Select(Browser.driver.findElement(By.name(strLocatorInfo)));
				break;
			case "xpath":
				dropdown = new Select(Browser.driver.findElement(By.xpath(strLocatorInfo)));
				break;
			}
			if (dropdown.getOptions().size() > 0) {
				for (int i = 0; i < dropdown.getOptions().size(); i++) {
					dropdown.getOptions().get(i).click();
					bolSelect = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Wait for element is not working" + e.getMessage());
		}
		return bolSelect;
	}

	
	public static boolean fnDropDownPositionCheck(String strLocatorType, String strLocatorInfo, int intPosition) {
		boolean bolStatus = true;
		dropdown = null;
		switch (strLocatorType) {
		case "id":
			dropdown = new Select(Browser.driver.findElement(By.id(strLocatorInfo)));
			break;

		case "name":
			dropdown = new Select(Browser.driver.findElement(By.name(strLocatorInfo)));
			break;

		case "xpath":
			dropdown = new Select(Browser.driver.findElement(By.xpath(strLocatorInfo)));
			break;
		}
		if (dropdown.getOptions().size() > intPosition) {
			bolStatus = true;
		} else {
			bolStatus = false;
		}
		return bolStatus;
	}

	
	public static void fnFailTest() {
		try {
			Common.strTestResult = "FAIL";
			
			CommonMethods.fnAlertScreenShot("ScreenCaptureOnFailed");
			CommonMethods.fnCurrentScreenShot("BrowserCaptureOnFailed");
			Browser.driver.quit();
			Assert.fail();
			fnTestResult1("fnFailTest","Failed in between", "failed");
		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			e.printStackTrace();
		}
	}

	public static void fnNavigateToURL(String strUrl) {
		try {
			Browser.driver.get(strUrl);
			CommonMethods.fnWaitForPageLoad();
			fnTestResult1("fnNavigateToURL","Navigated to the given URL", "Passed");
		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			e.printStackTrace();
		}
	}
	
	public static boolean fnIsAlertPresent() {
		try {
			Browser.driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	
	public static String fnReturnElementValue(String strLocatorType, String strLocatorInfo) {

		String status = null;
		// look for the requested element
		switch (strLocatorType) {
		case "id":
			status = Browser.driver.findElement(By.id(strLocatorInfo)).getAttribute("value");
			break;
		case "name":
			status = Browser.driver.findElement(By.name(strLocatorInfo)).getAttribute("value");
			break;
		case "linkText":
			status = Browser.driver.findElement(By.linkText(strLocatorInfo)).getAttribute("value");
			break;
		case "linkValue":
			status = Browser.driver.findElement(By.partialLinkText(strLocatorInfo)).getAttribute("value");
			break;
		case "css":
			status = Browser.driver.findElement(By.cssSelector(strLocatorInfo)).getAttribute("value");
			break;
		case "class":
			status = Browser.driver.findElement(By.className(strLocatorInfo)).getAttribute("value");
			break;
		case "xpath":
			status = Browser.driver.findElement(By.xpath(strLocatorInfo)).getAttribute("value");
			break;
		}
		return status.trim();
	}

	
	public static boolean fnSwitchWindow(String strType, String strTitleUrl) throws InterruptedException {
		currentWindow = Browser.driver.getWindowHandle();
		Set<String> availableWindows = Browser.driver.getWindowHandles();
		WebDriverWait wait = new WebDriverWait(Browser.driver, 20);
		switch (strType) {
		case "title":
			if (!availableWindows.isEmpty()) {
				for (String windowId : availableWindows) {
					if (Browser.driver.switchTo().window(windowId).getTitle().equals(strTitleUrl)) {
						if (!(windowId.equals(currentWindow)))
							return true;
					} else {
						Browser.driver.switchTo().window(currentWindow);
					}
				}
			}
			break;
		case "url":
			if (!availableWindows.isEmpty()) {
				for (String windowId : availableWindows) {
					if (Browser.driver.switchTo().window(windowId).getCurrentUrl().contains(strTitleUrl)) {
						//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("img")));
						Thread.sleep(5000);
						return true;
					} else {
						Browser.driver.switchTo().window(currentWindow);
					}
				}
			}
			break;
		}
		return false;
	}

	
	public static boolean fnIsElementExist(String strLocatorType, String strLocatorInfo) {
		int intStatus = 0;
		try {
			switch (strLocatorType) {
			case "id":
				intStatus = Browser.driver.findElements(By.id(strLocatorInfo)).size();
				break;
			case "name":
				intStatus = Browser.driver.findElements(By.name(strLocatorInfo)).size();
				break;
			case "linkText":
				intStatus = Browser.driver.findElements(By.linkText(strLocatorInfo)).size();
				break;
			case "linkValue":
				intStatus = Browser.driver.findElements(By.partialLinkText(strLocatorInfo)).size();
				break;
			case "css":
				intStatus = Browser.driver.findElements(By.cssSelector(strLocatorInfo)).size();
				break;
			case "class":
				intStatus = Browser.driver.findElements(By.className(strLocatorInfo)).size();
				break;
			case "xpath":
				intStatus = Browser.driver.findElements(By.xpath(strLocatorInfo)).size();
				break;
			}
			if (intStatus != 0)
				return true;
			else {
				System.out.println("Element ---> " + strLocatorInfo + " is not present on the page.");
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}


	public static String fnReadFilePath(String Path) {
		String a = null;
		try {
			File f = new File(Path);
			a = f.getAbsolutePath();
		} catch (Exception e) {
			System.out.println("Wait for element is not working" + e.getMessage());
		}
		return a;
	}

	
	public static boolean fnWaitForElement(int intSec, String strLocatorType, String strLocatorInfo) {
		try {
			WebDriverWait wait = new WebDriverWait(Browser.driver, intSec);
			// Identify the Elements by different Identifications
			switch (strLocatorType) {
			case "id":
				// Identify the Elements by ID
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(strLocatorInfo)));
				break;
			case "name":
				// Identify the Elements by name
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(strLocatorInfo)));
				break;
			case "linkText":
				// Identify the Elements by LinkText
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(strLocatorInfo)));
				break;
			case "linkValue":
				// Identify the Elements by PartialLinkValue
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(strLocatorInfo)));
				break;
			case "css":
				// Identify the Elements by Css
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(strLocatorInfo)));
				break;
			case "class":
				// Identify the Elements by Class
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(strLocatorInfo)));
				break;
			case "xpath":
				// Identify the Elements by Xpath
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(strLocatorInfo)));
				break;
			}
			return true;
		} catch (Exception e) {
			System.out.println("Wait for element is not working" + e.getMessage());
		}
		return false;
	}

	
	public static String fnReturnTextOfElementByXpath(String strLocatorInfo) {
		WebElement objWebElement = null;
		try {
			objWebElement = Browser.driver.findElement(By.xpath(strLocatorInfo));
		} catch (Exception e) {
			System.out.println("Element is not working:" + e.getMessage());
		}
		return objWebElement.getText().trim();
	}

	
	public static void fnClickByJSExecutor(String strLocatorType, String strLocatorInfo) {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			JavascriptExecutor executor = (JavascriptExecutor) Browser.driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			System.out.println("Exception Occur:--" + e.getStackTrace());
			CommonMethods.fnTestResult("fnClickByJSExecutor","Exception Occur on click on link or button", "failed");
		}
	}

	public static void fnInitializeEnvironment(String strClassName) {
		try {
			Common.className = strClassName;
			CommonMethods.fnConstructor();
			Common.strTestResult = "";
			// Reset Step Count
			Common.intTestStep = 0;
			//fnGenerateHtmlReportTable(strClassName);
			Common.Tests.add(strClassName + "_PASS");
			System.out.println("Commontests");
			// Set the Environment Variables
			fnEnvironmentPropertiesSetter();

			
		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			// Fail Test if exception occurs
			e.printStackTrace();
			//CommonMethods.fnTestResult("Exception Occur on initialize env methods.", "failed");
		}
	}

	
	public static void fnVerifyElementPresence(String strLocatorType, String strLocatorInfo, boolean bolStatus) {
		boolean bolFlag = false;
		try {
			// look for the requested element
			switch (strLocatorType) {
			case "title":
				bolFlag = Browser.driver.getTitle().contains(strLocatorInfo);
				break;
			case "text":
				bolFlag = Browser.driver.findElement(By.cssSelector("body")).getText().contains(strLocatorInfo);
				break;
			case "id":
				bolFlag = Browser.driver.findElement(By.id(strLocatorInfo)).isDisplayed();
				break;
			case "name":
				bolFlag = Browser.driver.findElement(By.name(strLocatorInfo)).isDisplayed();
				break;
			case "linkText":
				bolFlag = Browser.driver.findElement(By.linkText(strLocatorInfo)).isDisplayed();
				break;
			case "linkValue":
				bolFlag = Browser.driver.findElement(By.partialLinkText(strLocatorInfo)).isDisplayed();
				break;
			case "css":
				bolFlag = Browser.driver.findElement(By.cssSelector(strLocatorInfo)).isDisplayed();
				break;
			case "class":
				bolFlag = Browser.driver.findElement(By.className(strLocatorInfo)).isDisplayed();
				break;
			case "xpath":
				bolFlag = Browser.driver.findElement(By.xpath(strLocatorInfo)).isDisplayed();
				break;
			}
			if (bolStatus == true && bolFlag == true) 
				System.out.println("Element Presence " + strLocatorInfo);
			else if (bolStatus == false && bolFlag == false)
				System.out.println("Element Not Presence " + strLocatorInfo);
			else
				CommonMethods.fnTestResult("fnVerifyElementPresence","Element Not Presence/Absence--> " + strLocatorInfo, "failed");
			
		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			CommonMethods.fnTestResult("fnVerifyElementPresence"," exception occurred  while finding the element  " + strLocatorInfo, "failed");
		}
	}

	public static int fnReturnRowNumberByColumnValue(String strLocatorType, String strLocatorInfo,
			int intStartRowNumber, int intColumnNumber, String strSearchValue) {
		// look for the requested element
		switch (strLocatorType) {
		case "id":
			element = Browser.driver.findElement(By.id(strLocatorInfo)); // browser.driver.findElement(By.id(byID)).isDisplayed();
			break;
		case "name":
			element = Browser.driver.findElement(By.name(strLocatorInfo));
			break;
		case "class":
			element = Browser.driver.findElement(By.className(strLocatorInfo));
			break;
		case "xpath":
			element = Browser.driver.findElement(By.xpath(strLocatorInfo));
			break;
		}
		System.out.println("Table by Id : " + strLocatorInfo);
		trRowHolder = element.findElements(By.tagName("tr"));
		for (int i = intStartRowNumber; i < trRowHolder.size(); i++) {
			List<WebElement> tdColHolder = trRowHolder.get(i).findElements(By.tagName("td"));
			System.out.println(i + " TR Contains: " + tdColHolder.size() + " : td[" + intColumnNumber + "] : "
					+ tdColHolder.get(intColumnNumber).getText());
			String tdvalue = tdColHolder.get(intColumnNumber).getText().trim();
			if (tdvalue.equalsIgnoreCase(strSearchValue)) {
				return i;
			}
		}
		return -1;
	}

	
	public static String fnReturnAlertMessage() throws InterruptedException {
		String strPopupText = null;
		try {
			/*
			 * fnWaitForAlert(20000); if (CommonMethods.fnIsAlertPresent()) {
			 */
			if (fnWaitForAlert(30)) {
				Alert alert = Browser.driver.switchTo().alert();
				strPopupText = alert.getText().trim();
				System.out.println(strPopupText);
				alert.accept();
			} else {
				System.out.println("Popup is missing.");
				CommonMethods.fnTestResult("fnReturnAlertMessage","PopUp is missing to validated-->" + strPopupText, "failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("fnReturnAlertMessage","Exception while retriveing message from alert popup", "failed");
			return "";
		}
		return strPopupText;
	}

	
	
	
	public static void fnWaitForPageLoad() {
		String s = "";
		int i = 0;
		while (!s.equals("complete")) {
			s = (String) ((ChromeDriver) Browser.driver).executeScript("return document.readyState");
			try {
				Thread.sleep(5000);
				if (i == 10)
					break;
				i = i + 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
				CommonMethods.fnTestResult("fnWaitForPageLoad","Exception occured on wait for page load", "failed");
			}
		}
	}

	
	public static NodeList fnGetXMLDocumentNode(String TagName) {
		try {
			File fXmlFile = new File(Common.XML_PATH);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(TagName);
			return nList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while get xml document node");
		}
		return null;
	}

	
	public static void fnEnvironmentPropertiesSetter() {
		try {
			// Proceed with Parsing for Environment Assignment only if the
			// Environment is null
			Thread.sleep(2000);
			if (Common.Environment==null) {
				NodeList nList = fnGetXMLDocumentNode("CONFIGURATION");
				Node nNode = nList.item(0);
				Element eElement = (Element) nNode;
				if (eElement.getAttribute("id").equals("TEST")) {
					Common.Environment = "TEST";
				} else if (eElement.getAttribute("id").equals("DEV")) {
					Common.Environment = "DEV";
				} else if (eElement.getAttribute("id").equals("STAGE")) {
					Common.Environment = "STAGE";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//CommonMethods.fnTestResult("Exception while Set the Environment properties ", "failed");
		}
	}

	
	public static String fnGetEnvironmentDataElementValue(String TagName) {
		try {
			NodeList nList11 = fnGetXMLDocumentNode("IDENTIFICATION");
			for (int temp = 0; temp < nList11.getLength(); temp++) {
				Node nNode1 = nList11.item(temp);
				if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement1 = (Element) nNode1;
					if (eElement1.getAttribute("en").equals(Common.Environment)) {
						return eElement1.getElementsByTagName(TagName).item(0).getTextContent();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("fnGetEnvironmentDataElementValue","Error while get environment data element value", "failed");
		}
		return null;
	}

	
	public static String fnGetNonEnvDataEle(String strDocNode, String strTagName) {
		try {
			NodeList nList11 = fnGetXMLDocumentNode(strDocNode);
			for (int temp = 0; temp < nList11.getLength(); temp++) {
				Node nNode1 = nList11.item(temp);
				if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement1 = (Element) nNode1;
					return eElement1.getElementsByTagName(strTagName).item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("fnGetNonEnvDataEle","Error while get Non env data element ", "failed");
		}
		return null;
	}

	
	public static void fnHtmlReport(String strExceptedResult, String strResultStatus) {
		String userMust = "	User must able to ";
		if ((strResultStatus.equalsIgnoreCase("Passed"))) {
			Reporter.log("Step:" + fnStrNumber(Common.intTestStep));
			Reporter.log("Expected:" + userMust + strExceptedResult);
			Reporter.log("Actual:" + "		User is able to " + strExceptedResult);
			Reporter.log("Result:" + "		Passed");
			if (CommonMethods.fnGetNonEnvDataEle("GENERIC", "CAPTURE_SCREEN_ON_SUCCESS").toString().equals("true")) {
				fnTakeScreenShot(strResultStatus);
				String hrefString = Common.Path2 + "/Step" + fnStrNumber(Common.intTestStep) + "_" + strResultStatus
						+ ".png";
				Reporter.log("<a href = " + hrefString + ">Screenshot</a>");
			}
		} else {
			Reporter.log("Step:" + fnStrNumber(Common.intTestStep) + userMust + strExceptedResult
					+ "		User is not able to " + strExceptedResult + "		Failed");
			fnTakeScreenShot(strResultStatus);
			String hrefString = Common.Path2 + "/Step" + fnStrNumber(Common.intTestStep) + "_" + strResultStatus
					+ ".png";
			Reporter.log("<a href = " + hrefString + ">Screenshot</a>");
		}
	}

	
	public static void fnGenerateHtmlReportTable(String strTestName) throws Exception {
		try {
			String header = "<html><head><title>Detailed Report</title></head><body link='orange'><table border='0' cellspacing='1' cellpadding='1' width='100%'><tr><td align='left'><img src='"
					+ System.getProperty("user.dir")
					+ "\\Optum.PNG' alt='Logo'height='50' width='100'/></td></tr><tr><td align='center'><h4 align='center'><font face='arial'  color='#153e7e' size='5'><b><center>Summary Report</center></b></font></h4></td></tr></table>";
					/*+ System.getProperty("user.dir")
					+ "\\TestResult\\Optum.PNG' alt='Logo'height='50' width='125'/></td><td align='center'><h4 align='center'><font face='arial'  color='#153e7e' size='5'><b>Detailed Report</b></font></h4></td>";*/
			String table = "<td align='right'></td></tr></table><div id='Script1'><table border='1' cellspacing='1' cellpadding='1' width='80%'><tr><td colspan='3'><h4 align='center'><font color='black' size='4' face='Arial'><b>"
					+ strTestName
					+ "</b></font></h4></td></tr><tr><td bgcolor='#153e7e' width='30%' align='center' valign='middle'><font color='#e0e0e0' size='2' face='Arial'><b>Step</b></td><td bgcolor='#153e7e' align='center' valign='middle'><font color='#e0e0e0' size='2' face='Arial'><b>Description</b></font></td><td bgcolor='#153e7e' align='center' valign='middle' width='10%'><font color='#e0e0e0' size='2' face='Arial'><b>Status</b></font></td></tr>";
			String template;
			File reportfile = new File(System.getProperty("user.dir") + "\\TestResult\\DetailedTestReport.html");
			FileWriter fw;
			if (Common.reportfilePath == null) {
				reportfile.createNewFile();
				Common.reportfilePath = reportfile.getAbsolutePath();
				template = header + table;
				fw = new FileWriter(reportfile.getAbsoluteFile());
			} else {
				template = table;
				fw = new FileWriter(reportfile.getAbsoluteFile(), true);
			}
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(template);
			bw.flush();
			bw.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
			//CommonMethods.fnTestResult("Error while generating the HTML report ", "failed");
		}
	}

	
	public static void fnTestResult1(String MethodName, String strExceptedResult, String strResultStatus) throws IOException {
		String template;		
		if (strResultStatus.equalsIgnoreCase("Passed")) {
			if (CommonMethods.fnGetNonEnvDataEle("GENERIC", "CAPTURE_SCREEN_ON_SUCCESS").toString().equals("true")) {
				CommonMethods.fnTakeScreenShot(strResultStatus);
				String hrefString = Common.Path2 + "/Step" + fnStrNumber(Common.intTestStep) + "_" + strResultStatus
						+ ".png";
				template = "<tr><td width='30%'><font color='#153e7e' size='1' face='Arial'><b>" + MethodName
						+ "</b></font></td><td><font color='#153e7e' size='1' face='Arial'><b>" + strExceptedResult
						+ "</b></font></td><td width='10%' bgcolor='green' align='center'><font color='white' size='1' face='Arial'><b><a href='"
						+ hrefString + "'>Passed</a></b></font></td></tr>";
			} else
				template = "<tr><td width='30%'><font color='#153e7e' size='1' face='Arial'><b>" + MethodName
						+ "</b></font></td><td><font color='#153e7e' size='1' face='Arial'><b>" + strExceptedResult
						+ "</b></font></td><td width='10%' bgcolor='green' align='center'><font color='white' size='1' face='Arial'><b>Passed</b></font></td></tr>";

			File reportfile = new File(Common.reportfilePath);
			FileWriter fw;
			fw = new FileWriter(reportfile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(template);
			bw.flush();
			bw.close();
		} else {
			// Common.FailedTests.add(Common.className);
			CommonMethods.fnTakeScreenShot(strResultStatus);
			String hrefString = Common.Path2 + "/Step" + fnStrNumber(Common.intTestStep) + "_" + strResultStatus
					+ ".png";
			template = "<tr><td width='30%'><font color='#153e7e' size='1' face='Arial'><b>" + MethodName
					+ "</b></font></td><td><font color='#153e7e' size='1' face='Arial'><b> User is not able to "
					+ strExceptedResult
					+ " </b></font></td><td width='10%' bgcolor='red' align='center'><font color='white' size='1' face='Arial'><b><a href='"
					+ hrefString + "'>Failed</a></b></font></td></tr>";
			File reportfile = new File(Common.reportfilePath);
			FileWriter fw;
			fw = new FileWriter(reportfile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(template);
			bw.flush();
			bw.close();
			Common.Tests.remove(Common.Tests.size() - 1);
			Common.Tests.add(Common.className + "_FAIL");
		}
	}


	
	public static int fnCountofElements(String strLocatorType, String strLocatorInfo) {
		int actCount = 0;
		// look for the requested element
		try {
			switch (strLocatorType) {
			case "id":
				actCount = Browser.driver.findElements(By.id(strLocatorInfo)).size();
				break;
			case "name":
				actCount = Browser.driver.findElements(By.name(strLocatorInfo)).size();
				break;
			case "linkText":
				actCount = Browser.driver.findElements(By.linkText(strLocatorInfo)).size();
				break;
			case "linkValue":
				actCount = Browser.driver.findElements(By.partialLinkText(strLocatorInfo)).size();
				break;
			case "css":
				actCount = Browser.driver.findElements(By.cssSelector(strLocatorInfo)).size();
				break;
			case "class":
				actCount = Browser.driver.findElements(By.className(strLocatorInfo)).size();
				break;
			case "xpath":
				actCount = Browser.driver.findElements(By.xpath(strLocatorInfo)).size();
				break;
			}

		} catch (Exception e) {
			CommonMethods.fnTestResult("fnCountofElements","Exception while size the element", "failed");
		}
		return actCount;
	}


	public static String fnRandomNumber(int intRange, boolean bolIsCurrency) {
		Random objRandNum = new Random();
		String strTemp = null;
		if (bolIsCurrency) {
			strTemp = "" + objRandNum.nextInt(99);
			if (strTemp.length() == 1)
				strTemp = strTemp + "0";
			return "" + objRandNum.nextInt(intRange) + "." + strTemp;
		} else {
			return "" + objRandNum.nextInt(intRange);
		}
	}

	public static String fnReturnDate(int intDays) throws Exception {
		DateFormat objDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, intDays);
		return objDateFormat.format(cal.getTime());
	}

	
	public static boolean fnIsNotEmptyOrNull(String objString) {
		return (objString != null && !objString.isEmpty());
	}

	
	public static String fnAttribleValue(String strLocatorType, String strLocatorInfo, String strAttributeName) {
		String strAttributeValue = null;
		// look for the requested element
		try {
			switch (strLocatorType) {
			case "id":
				strAttributeValue = Browser.driver.findElement(By.id(strLocatorInfo)).getAttribute(strAttributeName);
				break;
			case "name":
				strAttributeValue = Browser.driver.findElement(By.name(strLocatorInfo)).getAttribute(strAttributeName);
				break;
			case "linkText":
				strAttributeValue = Browser.driver.findElement(By.linkText(strLocatorInfo))
						.getAttribute(strAttributeName);
				break;
			case "linkValue":
				strAttributeValue = Browser.driver.findElement(By.partialLinkText(strLocatorInfo))
						.getAttribute(strAttributeName);
				break;
			case "css":
				strAttributeValue = Browser.driver.findElement(By.cssSelector(strLocatorInfo))
						.getAttribute(strAttributeName);
				break;
			case "class":
				strAttributeValue = Browser.driver.findElement(By.className(strLocatorInfo))
						.getAttribute(strAttributeName);
				break;
			case "xpath":
				strAttributeValue = Browser.driver.findElement(By.xpath(strLocatorInfo)).getAttribute(strAttributeName);
				break;
			}
			return strAttributeValue;
		} catch (Exception e) {
			return null;
		}
	}


	public static void fnVerifySorting(String strGridXPath, int intColNumber, int intRowNumber) {
		try {
			String TableStore = strGridXPath;

			String strFirstPostionBeforSorting = null;
			String strLastPostionBeforSorting = null;
			String strFirstPostionAfterSorting = null;
			String strLastPostionAfterSorting = null;

			String strLinkText = null;
			int intSize = 0;

			intSize = CommonMethods.fnCountofElements("xpath", TableStore + "/tr");

			strLinkText = CommonMethods
					.fnReturnTextOfElementByXpath(TableStore + "/tr[" + intRowNumber + "]/td[" + intColNumber + "]");
			// Click on the column Name to sort initial
			System.out.println("Column Link Text is :" + strLinkText);

			// CommonMethods.fnClickByJSExecutor("linkText", strLinkText);
			CommonMethods.fnClickByJSExecutor("xpath",
					TableStore + "/tr[" + intRowNumber + "]/td[" + intColNumber + "]/a");

			CommonMethods.fnWaitForPageLoad();
			CommonMethods.fnCurrentScreenShot("SortingBefore_Clik_" + strLinkText);
			strFirstPostionBeforSorting = CommonMethods
					.fnReturnTextOfElementByXpath(TableStore + "/tr[" + 3 + " ]/td[" + intColNumber + "]");
			strLastPostionBeforSorting = CommonMethods
					.fnReturnTextOfElementByXpath(TableStore + "/tr[" + intSize + "]/td[" + intColNumber + "]");

			// Click on the column Name to sort initial
			// CommonMethods.fnClickByJSExecutor("linkText", strLinkText);
			CommonMethods.fnClickByJSExecutor("xpath",
					TableStore + "/tr[" + intRowNumber + "]/td[" + intColNumber + "]/a");

			CommonMethods.fnWaitForPageLoad();
			CommonMethods.fnCurrentScreenShot("SortingAfter_" + strLinkText);
			strFirstPostionAfterSorting = CommonMethods
					.fnReturnTextOfElementByXpath(TableStore + "/tr[" + 3 + "]/td[" + intColNumber + "]");
			strLastPostionAfterSorting = CommonMethods
					.fnReturnTextOfElementByXpath(TableStore + "/tr[" + intSize + "]/td[" + intColNumber + "]");

			if (strFirstPostionBeforSorting.equals(strLastPostionAfterSorting)
					&& strLastPostionBeforSorting.equals(strFirstPostionAfterSorting)) {
				System.out.println("[INFO] -- > Sorting on '" + strLinkText + "' is performed and values are matched");
				System.out.println("\tBefor Sorting at First [" + strFirstPostionBeforSorting
						+ "] vs After Sorting at Last [" + strLastPostionAfterSorting + "].");
				System.out.println("\tBefor Sorting at Last  [" + strLastPostionBeforSorting
						+ "] vs After Sorting at First [" + strFirstPostionAfterSorting + "].");
			} else {
				System.out.println("[ERROR] -- > Sorting on '" + strLinkText + "' failed, values are:");
				System.out.println("\tBefor Sorting at First [" + strFirstPostionBeforSorting
						+ "] vs After Sorting at Last [" + strLastPostionAfterSorting + "].");
				System.out.println("\tBefor Sorting at Last  [" + strLastPostionBeforSorting
						+ "] vs After Sorting at First [" + strFirstPostionAfterSorting + "].");
				CommonMethods.fnTestResult("fnVerifySorting","Sorting on Column strLinkText is performed, values are not matched.",
						"failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] exception occured to retrive the grid.");
			CommonMethods.fnTestResult("fnVerifySorting","Sorting on Column strLinkText is performed [due ot exception]", "failed");
		}
	}

	
	
	public static void fnVerifyElemementPresence(String strType, String strText) {
		try {
			boolean strStatus = false;
			// look for the requested element
			switch (strType) {
			case "title":
				strStatus = Browser.driver.getTitle().contains(strText);
				break;
			case "text":
				strStatus = Browser.driver.findElement(By.cssSelector("body")).getText().contains(strText);
				break;
			case "id":
				strStatus = Browser.driver.findElement(By.id(strText)).isDisplayed();
				break;
			case "name":
				strStatus = Browser.driver.findElement(By.name(strText)).isDisplayed();
				break;
			case "linkText":
				strStatus = Browser.driver.findElement(By.linkText(strText)).isDisplayed();
				break;
			case "linkValue":
				strStatus = Browser.driver.findElement(By.partialLinkText(strText)).isDisplayed();
				break;
			case "css":
				strStatus = Browser.driver.findElement(By.cssSelector(strText)).isDisplayed();
				break;
			case "class":
				strStatus = Browser.driver.findElement(By.className(strText)).isDisplayed();
				break;
			case "xpath":
				strStatus = Browser.driver.findElement(By.xpath(strText)).isDisplayed();
				break;
			}
			System.out.println("Element Name-->" + strText);
			if (!strStatus) {
				// Forcefully Quit if Validation fails
				CommonMethods.fnTestResult("VerifyElementPresent","Element Presence---> " + strText, "failed");
			}
			Assert.assertTrue(strStatus, strText);
		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			e.printStackTrace();
			CommonMethods.fnTestResult("VerifyElementPresent","Element Presence---> " + strText, "failed");
		}
	}

	public static boolean fnSelectRequiredValuesFromList(String strLocatorType, String strLocatorInfo,
			int strValuesToSelect) {
		boolean bolSelect = false;
		try {
			dropdown = null;
			switch (strLocatorType) {
			case "id":
				dropdown = new Select(Browser.driver.findElement(By.id(strLocatorInfo)));
				break;
			case "name":
				dropdown = new Select(Browser.driver.findElement(By.name(strLocatorInfo)));
				break;
			case "xpath":
				dropdown = new Select(Browser.driver.findElement(By.xpath(strLocatorInfo)));
				break;
			}
			if (dropdown.getOptions().size() > 0) {
				for (int i = 0; i <= strValuesToSelect - 1; i++) { // Select
																	// required
																	// values.
																	// Index
																	// start
																	// from 0
					dropdown.getOptions().get(i).click();
					bolSelect = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Wait for element is not working" + e.getMessage());
		}
		return bolSelect;
	}

	public static void fnWaitForPageToLoad(int intMaxWaitTime, String strLocator) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(Browser.driver, intMaxWaitTime);
			wait.until(ExpectedConditions.titleContains(strLocator));
			CommonMethods.fnVerifyTextOnPage("text", "Attorney Telephone Number:");

		} catch (TimeoutException e) {
			System.out.println("Page is not loaded in specified time Internal" + e.getMessage());
			CommonMethods.fnTestResult("fnWaitForPageToLoad","Page is not loaded in specified time Internal", "failed");
		}
	}

	/*
	 * public static void fnWaitForPageToLoad(int intMaxWaitTime) throws
	 * Exception { try { new WebDriverWait(browser.driver,
	 * intMaxWaitTime).until((ExpectedCondition<Boolean>) wd ->
	 * ((JavascriptExecutor) wd).executeScript("return document.readyState"
	 * ).equals("complete")); } catch (TimeoutException e) { System.out.println(
	 * "Page is not loaded in specified time Internal" + e.getMessage());
	 * CommonMethods.fnTestResult(
	 * "Page is not loaded in specified time Internal", "failed"); } }
	 */

	// Wait for page load untill status is complete
	public static void fnWaitForPageToLoad(int intMaxWaitTime) throws Exception {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(Browser.driver, intMaxWaitTime);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Page is not loaded in specified time Internal" + error.getMessage());
			CommonMethods.fnTestResult("fnWaitForPageToLoad","Page is not loaded in specified time Internal", "failed");
		}
	}

	
	public static String getcurrdate(String Format) throws Exception {
		DateFormat dateFormat = null;
		TimeZone cdtTime = TimeZone.getTimeZone("CST");
		if (Format.equals("1"))
			dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		else if (Format.equals("2"))
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		else if (Format.equals("3"))
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		else if (Format.equals("4"))
			dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		else if (Format.equals("5"))
			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		else if (Format.equals("6"))
			dateFormat = new SimpleDateFormat("dd MMMMMMM yyyy");
		else if (Format.equals("7"))
			dateFormat = new SimpleDateFormat("EEEEEE, dd MMMMMMM yyyy");
		dateFormat.setTimeZone(cdtTime);
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	// Wait for page load untill status is complete
	public static void fnSorting(String strXpathofTable, String strNumber, String strAscOrDesc) throws Exception {
		try {
			int intRowCount = CommonMethods.fnCountofElements("xpath", strXpathofTable+"/tr");
			ArrayList<String> obtainedList = new ArrayList<>();

			for (int i = 2; i <= intRowCount; i++)
				obtainedList.add(Browser.driver.findElement(By.xpath(strXpathofTable + "/tr["+i +"]/td[" + strNumber + "]")).getText().trim());
			
			ArrayList<String> sortedList = new ArrayList<>();
			for (String s : obtainedList) 
				sortedList.add(s);
			
			
			if(strAscOrDesc.equalsIgnoreCase("Desc"))
			Collections.reverse(sortedList);
			if(strAscOrDesc.equalsIgnoreCase("Asc"))
			Collections.sort(sortedList);
			
			for(int i=0;i<sortedList.size();i++)
			System.out.println(sortedList.get(i) +"==="+obtainedList.get(i));

			Assert.assertTrue(sortedList.equals(obtainedList),"Had a match");

		} catch (Throwable error) {
			System.out.println("Page is not loaded in specified time Internal" + error.getMessage());
			CommonMethods.fnTestResult("fnSorting","Page is not loaded in specified time Internal", "failed");
		}
	}

	public static void fnMouseHover(String strLocatorType, String strLocatorInfo) throws InterruptedException {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			
	        Actions action = new Actions(Browser.driver);
	        action.moveToElement(element).build().perform();
			System.out.println("MOUSE HOVER  ON ELEMENT-->" + strLocatorInfo);
		} catch (Exception e) {
			System.out.println("[FAIL] Exception occur while while MouseHover -->" + strLocatorInfo);
			e.printStackTrace();
			CommonMethods.fnTestResult("fnMouseHover","Exception occur while MouseHover ", "failed");
		}
	}
	
	
	public static void fnDoubleClick(String strLocatorType, String strLocatorInfo) throws InterruptedException {
		try {
			switch (strLocatorType) {
			case "id":
				element = Browser.driver.findElement(By.id(strLocatorInfo));
				break;
			case "name":
				element = Browser.driver.findElement(By.name(strLocatorInfo));
				break;
			case "linkText":
				element = Browser.driver.findElement(By.linkText(strLocatorInfo));
				break;
			case "linkValue":
				element = Browser.driver.findElement(By.partialLinkText(strLocatorInfo));
				break;
			case "css":
				element = Browser.driver.findElement(By.cssSelector(strLocatorInfo));
				break;
			case "class":
				element = Browser.driver.findElement(By.className(strLocatorInfo));
				break;
			case "xpath":
				element = Browser.driver.findElement(By.xpath(strLocatorInfo));
				break;
			}
			
	        Actions action = new Actions(Browser.driver);
	        
	        action.moveToElement(element).build().perform();
			
            //Here I used JavascriptExecutor interface to scroll down to the targeted element
	        ((JavascriptExecutor) Browser.driver).executeScript("arguments[0].scrollIntoView();", element);
	        //used doubleClick(element) method to do double click action
	        action.doubleClick(element).build().perform();
	        
	        
	        System.out.println("Double Click On Element-->" + strLocatorInfo);
		} catch (Exception e) {
			System.out.println("[FAIL] Exception occur while Double Click-->" + strLocatorInfo);
			e.printStackTrace();
			CommonMethods.fnTestResult("fnDoubleClick","Exception occur while Double Click ", "failed");
		}
	}
	
	public static void fnSwitchtoParentFrame() throws Exception
	{
			Browser.driver.switchTo().parentFrame();
					
	}
	public static void fnSwitchtodefault() throws Exception
	{
		Browser.driver.switchTo().defaultContent();
					
	}
	
	public static void fnSwitchToFrame(String propertyname, String propertyValue) throws Exception
	{
			if(propertyname.toUpperCase().equals("PARENT"))
			{
				fnSwitchtoParentFrame();
				return;
			}
			if(propertyname.toUpperCase().equals("DEFAULT"))
			{
				fnSwitchtodefault();
				return;				
			}
		
			switch (propertyname.toUpperCase()) {
			case "ID":			
				Browser.driver.switchTo().frame(Integer.parseInt(propertyValue));
				break;
			case "NAME":
				Browser.driver.switchTo().frame(propertyValue);
				break;
			default:
				break;			
			}	
	}
	public static void fnSwitchToFrameForControl(String propertyname, String propertyVal) throws Exception
	{
		List<WebElement> frmes = Browser.driver.findElements(By.tagName("iframe"));
		for (WebElement fme : frmes) 
		{
			try{
				Browser.driver.switchTo().frame(fme);					
				WebElement we = findControl(propertyname,propertyVal);
				if(we.isDisplayed())
				{
					break;
				}
				else
				{
					Browser.driver.switchTo().defaultContent();
				}
			}
			catch(Exception ex)
			{
				Browser.driver.switchTo().defaultContent();
			}
			
		}				
	}
	
	public static WebElement findControl(String propertyname, String propertyValue) throws Exception
	{
		WebElement we = null;
		Browser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		switch (propertyname.toUpperCase()) {
			case "ID":
				we = Browser.driver.findElement(By.id(propertyValue));
				break;
			case "NAME":
				we=Browser.driver.findElement(By.name(propertyValue));
				break;
			case "XPATH":
				we = Browser.driver.findElement(By.xpath(propertyValue));
				break;
			case "LINK":
				we = Browser.driver.findElement(By.linkText(propertyValue));
				break;
			default:
				break;
				
			}
		Browser.driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		return we;
	}
	
	public static void fnGenerateHtmlReportsummaryTable() throws Exception {
		try {
			
			String header = "<html><head><title>TestReport</title></head><body link='orange'>"
					+ "<table border='0' cellspacing='1' cellpadding='1' width='100%'><tr><td align='left'><img src='"
					+ System.getProperty("user.dir")
					+ "\\Optum.PNG' alt='Logo'height='50' width='100'/></td></tr><tr><td align='center'><h4 align='center'><font face='arial'  color='#153e7e' size='5'><b><center>Summary Report</center></b></font></h4></td></tr></table>"
					+ "<table border='1' cellspacing='1' cellpadding='1' width='60%'><tr><td>Total Executed</td><td>Totalvalue</td></tr><tr><td>Total Pass</td><td>Totalpassvalue</td></tr><tr><td>Total Fail</td><td>Totalfailvalue</td></tr><tr><td>Total Skipped</td><td>Totalskipvalue</td></tr></table>";
			
			//String table = "<br><table border='1' cellspacing='1' cellpadding='1' width='80%'>"
			//		+ "	<tr><td colspan='3'><h4 align='center'><font color='black' size='4' face='Arial'><b></td><td colspan='3'><h4 align='center'><font color='black' size='4' face='Arial'><b></td>";
			
			String template;
			File reportfile = new File(System.getProperty("user.dir") + "\\DetailedTestReport.html");
			FileWriter fw;
			reportfile.createNewFile();
			Common.reportfilePath = reportfile.getAbsolutePath();
			template = header;
			fw = new FileWriter(reportfile.getAbsoluteFile());
			
			/*if (reportfilePath == null) {
				reportfile.createNewFile();
				reportfilePath = reportfile.getAbsolutePath();
				template = header + table;
				fw = new FileWriter(reportfile.getAbsoluteFile());
			} else {
				template = table;
				fw = new FileWriter(reportfile.getAbsoluteFile(), true);
			}*/
			
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(template);
			bw.flush();
			bw.close();
			
		} catch (Exception ex) {
			System.out.println(ex.toString());		
		}
	}
	
	public static String fnreadExcel(int row1)
	{
		  try
	        {
	            FileInputStream file = new FileInputStream(new File("C:/BEX/TestData/Surveys.xls"));
	 
	           //Create Workbook instance holding reference to .xlsx file
	            HSSFWorkbook workbook  = new HSSFWorkbook(new POIFSFileSystem(file));	 
	            //Get first/desired sheet from the workbook
	            HSSFSheet sheet = workbook.getSheetAt(0);
	            Cell cell = sheet.getRow(row1).getCell(0);
	            String cellval = cell.getStringCellValue();            
	            file.close();	            
	            return cellval;
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            return "";
	        }
		
		  
		  
	}
	public static void sendEmail(Session session, String toEmail, String subject, String body, String Filename){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      msg.setFrom(new InternetAddress("vlnmurthy.tarigoppala@optum.com", "Test"));
	      msg.setReplyTo(InternetAddress.parse(toEmail, false));
	      msg.setSubject(subject, "UTF-8");
	      //msg.setContent(body, "text/html");
	      	Multipart multipart = new MimeMultipart();
	        MimeBodyPart textBodyPart = new MimeBodyPart();
	        textBodyPart.setContent(body, "text/html");
	        
	        if(Filename.equals(""))
	        {
	        	MimeBodyPart attachmentBodyPart= new MimeBodyPart();
	        	 String resultfile = System.getProperty("user.dir")+ "\\TestResult\\DetailedTestReport.html";
	 	        FileDataSource source = new FileDataSource(resultfile); // ex : "C:\\test.pdf"
	 	        attachmentBodyPart.setDataHandler(new DataHandler(source));
	 	        attachmentBodyPart.setFileName(resultfile); // ex : "test.pdf"
	 	       // add the text part
		        multipart.addBodyPart(attachmentBodyPart); // add the attachement part

	        }
	        multipart.addBodyPart(textBodyPart);       
	        //msg.setContent(multipart);

	            
	      msg.setContent(multipart);
	      msg.setSentDate(new Date());
	      
	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	public static void sendStartEmail()
	{
		String smtpHostServer = "mailinbound.uhc.com";                
        Properties props = System.getProperties();
        Session session = Session.getInstance(props, null);
        props.put("mail.smtp.host", smtpHostServer);
        String header = "Execution Started";       
        sendEmail(session, "vlnmurthy.tarigoppala@optum.com","Automation Execution Started",header,"");

	}
	public static void sendFinalEmail(int totalpass, int totalfail, int totalskip)
	{ 
		int TotalExecuted=totalpass+totalfail+totalskip;
	    int ipass=totalpass;
	    int ifail= totalfail;
	    int iskip= totalskip;
	    
		String smtpHostServer = "mailinbound.uhc.com";                
        Properties props = System.getProperties();
        Session session = Session.getInstance(props, null);
        props.put("mail.smtp.host", smtpHostServer);
        String header = "Hi Team,<br> Please Find the Automation Results As below <br><br>";
        String bodymsg = "<html><head><style>table,th,td{border: 1px solid black;background-color: #f1f1c1;border-collapse: collapse;}</style></head><body><br>"+header+"<table style='width:50%''><tbody><tr><td>TotalExecuted</td><td>"
        				 +TotalExecuted+"</td></tr><tr><td>PASS</td><td>"
        				 		+ ipass+"</td></tr></tr><td>FAIL</td><td>"
        				 				+ ifail+"</td></tr><tr><td>Skip</td><td>"
        				 						+ iskip+"</td></tr></tbody></body><br><br>Thanks,<br>AutomationTest</html>";
       
        sendEmail(session, "vlnmurthy.tarigoppala@optum.com","Automation Execution Completed",bodymsg,"");

	}
	
	public static String fnreadExcel(String fileName, int sheetid, int row1) 
	{
		  try
	        {
	            FileInputStream file = new FileInputStream(new File(fileName));	 
	           //Create Workbook instance holding reference to .xlsx file
	            HSSFWorkbook workbook  = new HSSFWorkbook(new POIFSFileSystem(file));	 
	            //Get first/desired sheet from the workbook
	            HSSFSheet sheet = workbook.getSheetAt(sheetid);
	            Cell cell = sheet.getRow(row1).getCell(1);
	            String cellval = cell.getStringCellValue();            
	            file.close();	
	            workbook.close();
	            return cellval;
	        }
		    catch(FileNotFoundException e)
			  {
			    	e.printStackTrace();
				    return "";	
			  }
	        catch (Exception e)
	        {
	            return "";
	        }
	}
	
	public static String fnreadExcel(String fileName, int sheetid, int row1, int col1) 
	{
		  try
	        {
	            FileInputStream file = new FileInputStream(new File(fileName));	 
	           //Create Workbook instance holding reference to .xlsx file
	            HSSFWorkbook workbook  = new HSSFWorkbook(new POIFSFileSystem(file));	 
	            //Get first/desired sheet from the workbook
	            HSSFSheet sheet = workbook.getSheetAt(sheetid);
	            Cell cell = sheet.getRow(row1).getCell(col1);
	            String cellval = cell.getStringCellValue();            
	            file.close();	
	            workbook.close();
	            return cellval;
	        }
		    catch(FileNotFoundException e)
			  {
			    	e.printStackTrace();
				    return "";	
			  }
	        catch (Exception e)
	        {
	            return "";
	        }
	}
	
	public static void fnwriteExcel(String fileName, int sheetid, int row1,String valuetowrite)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(fileName));	 
			
			HSSFWorkbook workbook  = new HSSFWorkbook(file);	 
			HSSFSheet sheet = workbook.getSheetAt(sheetid);
			HSSFRow row = sheet.getRow(row1); 
			Cell name = row.createCell(2); 
			name.setCellValue(valuetowrite);
			file.close();
			
			workbook.write(new FileOutputStream(new File(fileName)));
	        workbook.close();
		
		}
		catch (Exception e)
        {
            e.printStackTrace();
           
        }
		
		
		
	}
	public static void fnwriteExcel(String fileName, int sheetid, int row1,int col1,String valuetowrite)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(fileName));	 
			
			HSSFWorkbook workbook  = new HSSFWorkbook(file);	 
			HSSFSheet sheet = workbook.getSheetAt(sheetid);
			HSSFRow row = sheet.getRow(row1); 
			Cell name = row.createCell(col1); 
			name.setCellValue(valuetowrite);
			file.close();
			
			workbook.write(new FileOutputStream(new File(fileName)));
	        workbook.close();
		
		}
		catch (Exception e)
        {
            e.printStackTrace();
           
        }
		
		
		
	}
}