package utils;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pageRepository.LoginPage;

public class Browser {
	public static WebDriver driver;
	
	public static void fnOpeningBrowser(String URL) throws Exception {
	
		try {
			String headless_true = CommonMethods.fnGetNonEnvDataEle("GENERIC", "HEADLESS").toString();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").concat("/SeleniumServers/chromedriver.exe"));
			ChromeOptions options_chrome = new ChromeOptions();
			
			options_chrome.addArguments("enable-automation");
			if(headless_true.toUpperCase().equals("TRUE"))
			{
				options_chrome.addArguments("--headless");
			}
			options_chrome.addArguments("--window-size=1920,1080");
			options_chrome.addArguments("--no-sandbox");
			options_chrome.addArguments("--disable-extensions");
			options_chrome.addArguments("--dns-prefetch-disable");
			options_chrome.addArguments("--disable-gpu");
			options_chrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		    options_chrome.setExperimentalOption("useAutomationExtension", false); 
		    driver = new ChromeDriver(options_chrome);		    
		    driver.navigate().to(URL);
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	
	public static WebDriver fnOpeningBrowser1(String URL) throws Exception {
		
		try {
			String headless_true = CommonMethods.fnGetNonEnvDataEle("GENERIC", "HEADLESS").toString();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").concat("/SeleniumServers/chromedriver.exe"));
			ChromeOptions options_chrome = new ChromeOptions();
			
			options_chrome.addArguments("enable-automation");
			if(headless_true.toUpperCase().equals("TRUE"))
			{
				options_chrome.addArguments("--headless");
			}
			options_chrome.addArguments("--window-size=1920,1080");
			options_chrome.addArguments("--no-sandbox");
			options_chrome.addArguments("--disable-extensions");
			options_chrome.addArguments("--dns-prefetch-disable");
			options_chrome.addArguments("--disable-gpu");
			options_chrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		    options_chrome.setExperimentalOption("useAutomationExtension", false); 
		    driver = new ChromeDriver(options_chrome);		    
		    driver.navigate().to(URL);
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		    return driver;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
	}

	public static void loginapp() {
		try 
		{
			//Launch browser			
			String url = CommonMethods.fnGetEnvironmentDataElementValue("APP_URL");
			Browser.fnOpeningBrowser(url);
			CommonMethods.fnTestResult("LoginToAPP", "Launch Browser", "passed");
			
			
			CommonMethods.fnWaitForPageToLoad(30);
			Thread.sleep(5000);
			CommonMethods.fnEnterText("xpath", pageRepository.LoginPage.username_xpath, CommonMethods.fnGetEnvironmentDataElementValue("APP_USER_NAME"));
			CommonMethods.fnEnterText("xpath", pageRepository.LoginPage.password_xpath, CommonMethods.fnGetEnvironmentDataElementValue("APP_PASSWORD"));
			String locator = pageRepository.LoginPage.loginBtn_xpath;
			CommonMethods.fnClickOnButton("xpath", locator);
			CommonMethods.fnWaitForPageLoad();
			CommonMethods.fnClickOnButton("xpath", LoginPage.finish_xpath);
			CommonMethods.fnWaitForPageLoad();
			
		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("Loginapp","Exception while logout from the application", "failed");
		}
	}
	
	public static String fnGetTitle() {
		return driver.getTitle();
	}
}