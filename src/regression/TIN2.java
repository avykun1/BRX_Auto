package regression;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;
import org.testng.asserts.SoftAssert;

import configuration.Common;
import pageRepository.HomePage;
import pageRepository.NewDataMaintenanceRequestPage;
import utils.Browser;
import utils.CommonMethods;
import utils.ExcelUtil;
import utils.IDataSet;

public class TIN2 implements IDataSet {
	
	int intBeforeCount = 0, intAfterCount = 0;
	public int size;
	public AtomicInteger counter = new AtomicInteger(1);
	String strPatientName = "LNAutomation";
	public static SoftAssert softAssert;
	int i=0;

	@BeforeClass
	public void LogintoSalesforce() throws Exception
	{
		CommonMethods.fnGenerateHtmlReportTable("LogintoSalesforce");
		CommonMethods.fnInitializeEnvironment(this.getClass().getSimpleName());	
		//Login
		Browser.loginapp();
		CommonMethods.fnTestResult("LoginToAPP","Login Page Displayed", "passed");
		
		String strUrl=CommonMethods.fnGetCurrentUrl();
		if (strUrl.contains("lightning")) {
			System.out.println("Currently in Lightining view.");
			SwitchToClassic(Common.classicUrl);
			}
			
		NavigateToAddDMR();
				
	}
	
	@BeforeMethod
	public  void createTestResultsTable(Method testMethod) throws Exception
	{
		CommonMethods.fnGenerateHtmlReportTable(testMethod.getName());
						
	}
	

	
	
	@Test(dataProvider="TC_01_TestData")
	public void TC_01_VerifyMandatoryFields(String errMsg_xpath, String errMsg) {
		try {
		if(i==0) {
		softAssert = new SoftAssert();
		CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.TIN_Add_Change_Subtype , "text", "Add Credentialed Clinician to New Practice TIN and Unlink Old TIN (Demo Updates)");
		CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.State, "text", "AR");
		CommonMethods.fnClickOnLink("xpath", NewDataMaintenanceRequestPage.Date_Received_by_Optum);
		CommonMethods.fnClickOnLink("xpath", NewDataMaintenanceRequestPage.System_Effective_Date);
		CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.Primary_Source, "text", "Clinician Tax ID Add / Update Form (PDF)");
		CommonMethods.fnClickOnButton("xpath", NewDataMaintenanceRequestPage.Save_Btn);
		i++;
		}
		verifyMandatoryFields(errMsg_xpath,errMsg);
		if(counter.getAndIncrement() == size) {
			softAssert.assertAll();
		}
		
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	
	
	@Test(dataProvider="TC_01_TestData")
	public void TC_01_CreateDMR(String errMsg_xpath, String errMsg) {
		try {
		
		softAssert = new SoftAssert();
		CommonMethods.fnEnterText("xpath", NewDataMaintenanceRequestPage.Project_ID, "");
		
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	@DataProvider(name="TC_01_TestData")
	public Iterator<Object[]> getData(@TestInstance Object object) throws IOException {
		ArrayList<Object[]> al1=ExcelUtil.getSheetData("TC_01");
		if(object instanceof IDataSet) {
			((IDataSet)object).setSize(al1.size());
			
		}
		return al1.iterator();
	}
	
	

	@AfterClass
	public  void fnExecuteYesNo()	{
		//System.out.println("Reporting soft asserts -");
		
		
	/*	if (Common.strTestResult=="FAIL"){	
			System.out.println("Test case failed for this data --"+result+"--");
//			result.setStatus(ITestResult.SKIP);			
//			throw new SkipException("Test execution SKIPPED as the previous test FAILED");
		}*/
		
		
	}	
	
	
	
	public void SwitchToClassic(String str) {
		
				
				
				try {
					System.out.println("Switching to Classic view:- "+str);
					
					CommonMethods.fnWaitForPageLoad();
					Thread.sleep(20000);
					
					CommonMethods.fnClickOnButton("xpath", NewDataMaintenanceRequestPage.UserProfileImage);
					CommonMethods.fnClickOnLink("linkText", "Switch to Salesforce Classic");
					CommonMethods.fnTestResult("SwitchToClassic", "Switched to Classic View", "Passed");
					
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						CommonMethods.fnTestResult("SwitchToClassic","Issue occured while switching to classic Page", "Failed");
					}
					
		}
	
	
	public void NavigateToAddDMR() {
		
		
		
		try {
			
			CommonMethods.fnWaitForPageLoad();
			CommonMethods.fnClickOnButton("xpath", HomePage.allTabs_xpath);
			CommonMethods.fnClickOnButton("xpath", HomePage.datamaintenanceReq_xpath);
			CommonMethods.fnWaitForPageLoad();
			CommonMethods.fnTestResult("NavigateToCreateDMR", "Navigated to DMR Home Page", "Passed");
			
			//Click New
			CommonMethods.fnClickOnButton("xpath", HomePage.newBtn_xpath);
			CommonMethods.fnWaitForPageLoad();
			CommonMethods.fnDropDownSelect("xpath", NewDataMaintenanceRequestPage.RecordType, "text", "Clinician - TIN Add/Update");
			CommonMethods.fnClickOnButton("xpath", NewDataMaintenanceRequestPage.Continue_Btn );
			CommonMethods.fnWaitForPageLoad();
			CommonMethods.fnTestResult("NavigateToCreateDMR", "Navigated to ADD DMR Page", "Passed");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				CommonMethods.fnTestResult("Loginapp","Issue occured while navigating to DMR Page", "Failed");
			}
			
}
	
	
	
	public void verifyMandatoryFields(String errMsg_xpath, String errMsg) {
		
		String str= getErrorMsgText(errMsg_xpath);
			System.out.println("Mandatory field check for locator :"+str);
		
		softAssert.assertEquals(str, errMsg, str+" - Strings Not Matching with expected.");
		}
	
	public String getErrorMsgText(String errorMsg_xpath) {
		System.out.println("Fetching error messages...");
		WebElement errMsg = Browser.driver.findElement(By.xpath(errorMsg_xpath));
		return errMsg.getText().trim();
	}
	
	
	
	
	
	@AfterSuite
	public void Report(ITestContext context)
	{
		
		//int ipass = context.getPassedTests().size();
		//int ifail = context.getFailedTests().size();
		//int iskip = context.getSkippedTests().size();
		//CommonMethods.sendFinalEmail(ipass,ifail,iskip);
	}

	@Override
	public void setSize(int size) {
		// TODO Auto-generated method stub
		this.size=size;
		
	}

}
