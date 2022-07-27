package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UnUsedCommonMethods {
	static WebElement element;
	
	public static void fnAlertPopUpSaveMove() throws InterruptedException {
		Thread.sleep(10000);
		Thread.sleep(10000);
		Alert alert = Browser.driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		// String t2= text.replace("New case ", "");
		String t3 = text.replace(" was successfully created.", "");
		String caseid = t3.replace("New Case ", "");
		System.out.println(caseid);
		alert.accept();

		// System.out.print("Alert Successfully saveed");
		Thread.sleep(2000);
	}
	
	public static void fnreplaceAsString(String text, String textToRemove, String textToReplace) {
		text = text.replace(textToRemove, textToReplace);

	}
	
	
	public static void fnclickToOpen(String type, String typeValue) {

		switch (type) {
		case "id":
			element = Browser.driver.findElement(By.id(typeValue));
			break;

		case "name":
			element = Browser.driver.findElement(By.name(typeValue));
			break;

		case "linkText":
			element = Browser.driver.findElement(By.linkText(typeValue));
			break;

		case "linkValue":
			element = Browser.driver.findElement(By.partialLinkText(typeValue));
			break;

		case "css":
			element = Browser.driver.findElement(By.cssSelector(typeValue));
			break;

		case "class":
			element = Browser.driver.findElement(By.className(typeValue));
			break;

		case "xpath":
			element = Browser.driver.findElement(By.xpath(typeValue));
			break;

		}
		element.sendKeys("");
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			// wait for the modal dialog to open
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ALT);
			// press s key to save
			robot.keyPress(KeyEvent.VK_O);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_O);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.delay(1000);

			robot.keyPress(KeyEvent.VK_ENTER);

			robot.keyRelease(KeyEvent.VK_ENTER);

			robot.keyPress(KeyEvent.VK_ALT);
			robot.delay(1000);
			robot.delay(10000);
			robot.delay(10000);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_F4);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_ALT);

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			System.out.println("not working");
		}

		System.out.println("++ passed");
	}


	public void fnclickToSave(String type, String typeValue) throws InterruptedException {

		switch (type) {
		case "id":
			element = Browser.driver.findElement(By.id(typeValue));
			break;

		case "name":
			element = Browser.driver.findElement(By.name(typeValue));
			break;

		case "linkText":
			element = Browser.driver.findElement(By.linkText(typeValue));
			break;

		case "linkValue":
			element = Browser.driver.findElement(By.partialLinkText(typeValue));
			break;

		case "css":
			element = Browser.driver.findElement(By.cssSelector(typeValue));
			break;

		case "class":
			element = Browser.driver.findElement(By.className(typeValue));
			break;

		case "xpath":
			element = Browser.driver.findElement(By.xpath(typeValue));
			break;

		}
		element.sendKeys("");
		try {
			Robot robot = new Robot();

			System.out.println("robot:" + robot);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			// wait for the modal dialog to open
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ALT);
			// press s key to save
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_ALT);
			Thread.sleep(2000);
			// press enter to save the file with default name and in default
			// location
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			System.out.println("not working");
		}

		System.out.println("working");
		System.out.println("++ passed");
	}
	
	public static void fnuserLogout() throws InterruptedException {
		String ele = "Log out";
		element = Browser.driver.findElement(By.partialLinkText(ele));
		element.sendKeys("");
		try {
			Robot robot = new Robot();

			// System.out.println("robot:" + robot);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			// wait for the modal dialog to open
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ALT);
			// press s key to save
			robot.keyPress(KeyEvent.VK_Y);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_Y);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_ALT);

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			System.out.println("not working");
		}

	}
	
	
	public static void fnWaitforElement(WebElement element, int maxWaitTime)
	{
		try
		{ 
			WebDriverWait wait = new WebDriverWait(Browser.driver, maxWaitTime);
			wait.ignoring(Exception.class).until(ExpectedConditions.elementToBeClickable(element));
		}
		catch(Exception e)
		{
			System.out.println("Wait for element is not not working" + e.getMessage());
			CommonMethods.fnFailTest();
		}
	}
	
	public static void testResult(int intNumber, String strExcepted, String strResult) {
		try {
			// CommonMethods.fnTestResult (strExcepted, strResult);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
