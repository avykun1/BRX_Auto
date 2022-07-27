package regression;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import configuration.Common;
import pageRepository.HomePage;
import pageRepository.NewDataMaintenanceRequestPage;
import utils.Browser;
import utils.CommonMethods;
import utils.ExcelUtil;

public class Add_Credentialed_Clinician_to_New_Practice_TIN 
{

    int intBeforeCount = 0, intAfterCount = 0;
	String strPatientName = "LNAutomation";
	int i;
	
	@BeforeClass
	public void LogintoSalesforce() throws Exception
	{
		CommonMethods.fnGenerateHtmlReportTable("LogintoSalesforce");
		CommonMethods.fnInitializeEnvironment(this.getClass().getSimpleName());	
		//Login
		Browser.loginapp();
		CommonMethods.fnTestResult("LoginToAPP","Login Page Displayed", "passed");
		//Click All Tabs
		CommonMethods.fnClickOnButton("xpath", HomePage.allTabs_xpath);
		//Click Data Maintenance Requests
		CommonMethods.fnClickOnButton("xpath", HomePage.datamaintenanceReq_xpath);
		//Click New
		CommonMethods.fnClickOnButton("xpath", HomePage.newBtn_xpath);
		
		//Select Clinician - TIN Add/Update
		CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.recordType_xpath, "text", "Clinician - TIN Add/Update");
		//Click on "Continue"
		CommonMethods.fnClickOnButton("xpath", NewDataMaintenanceRequestPage.continueBtn_xpath);			
		
		
	}
	
	@BeforeMethod
	public  void createTestResultsTable(Method testMethod) throws Exception
	{
		CommonMethods.fnGenerateHtmlReportTable(testMethod.getName());
						
	}

	@DataProvider(name="TC_01_TestData")
	public Iterator<Object[]> getData() throws IOException {
		ArrayList<Object[]> al1=ExcelUtil.getSheetData("TC_01");
		return al1.iterator();
	}
	
	@DataProvider(name="TC_02_TestData")
	public Iterator<Object[]> getData1() throws IOException {
		ArrayList<Object[]> al1=ExcelUtil.getSheetData("TC_02");
		return al1.iterator();
	}
	
	
	/*@AfterSuite
	public void Report(ITestContext context)
	{
		
		int ipass = context.getPassedTests().size();
		int ifail = context.getFailedTests().size();
		int iskip = context.getSkippedTests().size();
		//CommonMethods.sendFinalEmail(ipass,ifail,iskip);
	}*/
	
	
	@AfterMethod
	public  void fnExecuteYesNo(ITestResult result)	{
		if (Common.strTestResult=="FAIL"){	
			result.setStatus(ITestResult.SKIP);			
			throw new SkipException("Test execution SKIPPED as the previous test FAILED");
		}
	}	
	
	
	@Test(dataProvider="TC_01_TestData")
	public void TC_01_VerifyMandatoryFields(String errMsg_xpath, String errMsg) {
		try 
		{
			if(i==0) 
			{
				//Select Add Credentialed Clinician to New Practice TIN
				CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.TIN_Add_Change_Subtype, "text", "Add Credentialed Clinician to New Practice TIN");
				//Select State
				CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.State, "index", "1");
				//Click on Save without entering any value
				CommonMethods.fnClickOnButton("xpath", NewDataMaintenanceRequestPage.saveBtn_xpath);
				i++;
			}
				verifyMandatoryFields(errMsg_xpath,errMsg);	
				CommonMethods.fnTestResult("TC_01_VerifyMandatoryFields","Error message is displayed when skipping mandatory fields", "passed");
			
		} catch (Exception e) {
			CommonMethods.fnTestResult("TC_01_VerifyMandatoryFields", "TC_01_VerifyMandatoryFields", "failed");
		}
	}
	@Test(dataProvider="TC_01_TestData")
	public void TC_02_VerifyConditionalFields(String errMsg_xpath, String errMsg) {
		try 
		{
			if(i==0) 
			{
				//Select Add Credentialed Clinician to New Practice TIN
				CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.TIN_Add_Change_Subtype, "text", "Add Credentialed Clinician to New Practice TIN");
				//Select State
				CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.State, "index", "1");
				//Click on Save without entering any value
				CommonMethods.fnClickOnButton("xpath", NewDataMaintenanceRequestPage.saveBtn_xpath);
				i++;
			}
				verifyMandatoryFields(errMsg_xpath,errMsg);	
				CommonMethods.fnTestResult("TC_01_VerifyMandatoryFields","Error message is displayed when skipping mandatory fields", "passed");
			
		} catch (Exception e) {
			CommonMethods.fnTestResult("TC_01_VerifyMandatoryFields", "TC_01_VerifyMandatoryFields", "failed");
		}
	}
	
	public void verifyMandatoryFields(String errMsg_xpath, String errMsg) {
		Assert.assertEquals(getErrorMsgText(errMsg_xpath), errMsg);
		
		}
	
	public String getErrorMsgText(String errorMsg_xpath) {
		WebElement errMsg = Browser.driver.findElement(By.xpath(errorMsg_xpath));
		return errMsg.getText().trim();
	}
	
	
	
	}
