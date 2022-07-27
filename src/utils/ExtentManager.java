package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;
	
	    public static ExtentReports createInstance(String fileName) {
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
	       
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("Automation Tester", System.getProperty("user.name"));
	        extent.setSystemInfo("Organization", "Optum");
	        extent.setSystemInfo("Test Run", "Regression");
	        
	        
	        return extent;
	    }

	    
	    public static String screenshotPath;
		public static String screenshotName;
		
		public static void captureScreenshot() {

			
			File scrFile = ((TakesScreenshot) Browser.driver).getScreenshotAs(OutputType.FILE);

			Date d = new Date();
			screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

			try {
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\ExtentReports\\" + screenshotName));
			} catch (IOException e) {
				e.printStackTrace();
			}

		
		}
		
		public static String addScreenshot() {
		    File scrFile = ((TakesScreenshot) Browser.driver).getScreenshotAs(OutputType.FILE);
		    String encodedBase64 = null;
		    FileInputStream fileInputStreamReader = null;
		    try {
		        fileInputStreamReader = new FileInputStream(scrFile);
		        byte[] bytes = new byte[(int)scrFile.length()];
		        fileInputStreamReader.read(bytes);
		        encodedBase64 = new String(Base64.getEncoder().encode(bytes));
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return "data:image/png;base64,"+encodedBase64;
		}

	}
