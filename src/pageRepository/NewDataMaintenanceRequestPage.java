package pageRepository;

public class NewDataMaintenanceRequestPage {
	
	public static String recordType_xpath = "//label[text()='Record Type of new record']/parent::td/parent::tr//select";
	public static String continueBtn_xpath = "//input[@title='Continue']";
	
	public static String saveBtn_xpath = "//input[@title='Save']";
	
	
	//Error Messages
	public static String error_Mandatory_xapth = "//div[@class='errorMsg']";
	
	
	public static String UserProfileImage = "//button[contains(@class,'userProfile')]";
	public static String ClassicSwitch = "//a[text()='Switch to Salesforce Classic']";
	public static String RecordType = "//label[text()='Record Type of new record']/parent::td/following-sibling::td[1]//select";
	public static String Continue_Btn = "//input[@title='Continue']";
	public static String Save_Btn = "//input[@title='Save']";
	public static String Project_ID = "//label[text()='Project ID']/parent::td//following-sibling::td[1]//input";
	public static String Project_Type = "//label[text()='Project Type']/parent::td//following-sibling::td[1]//input";
	public static String TIN_Add_Change_Subtype = "//label[text()='TIN Add/Update Subtype']/parent::td//following-sibling::td[1]//select";
	public static String Date_Received_by_Optum = "//label[text()='Date Received by Optum?']/parent::td//following::span[2]";	
	public static String Date_Received_by_Optum_errMsg = "//label[text()='Date Received by Optum?']/parent::td//following-sibling::td[1]//div";
	public static String State = "//label[text()='State']/parent::td//following-sibling::td[1]//select";
	public static String Clinician_NPI = "//label[text()='Clinician (Type I) NPI']/parent::td//following-sibling::td[1]//input";
	public static String Clinician_NPI_errMsg = "//label[text()='Clinician (Type I) NPI']/parent::td//following-sibling::td[1]//div";
	public static String Provider_First_Name = "//label[text()='Provider First Name']/parent::td//following-sibling::td[1]//input";
	public static String Provider_First_Name_errMsg = "//label[text()='Provider First Name']/parent::td//following-sibling::td[1]//div";
	public static String Group_NPI = "//label[text()='Clinician (Type II) NPI']/parent::td//following-sibling::td[1]//input";
	public static String Group_NPI_errMsg = "//label[text()='Clinician (Type II) NPI']/parent::td//following-sibling::td[1]//div";
	public static String Provider_Last_Name = "//label[text()='Provider Last Name']/parent::td//following-sibling::td[1]//input";
	public static String Provider_Last_Name_errMsg = "//label[text()='Provider Last Name']/parent::td//following-sibling::td[1]//div";
	public static String Practitioner = "//label[text()='Practitioner ']/parent::td//following-sibling::td[1]//input";
	public static String Provider_Facet_ID = "//label[text()='OHBS Facets Common Prac ID']/parent::td//following-sibling::td[1]//input";
	public static String Provider_Facet_ID_errMsg = "//label[text()='OHBS Facets Common Prac ID']/parent::td//following-sibling::td[1]//div";
	public static String Practitioner_TIN = "//label[text()='Practitioner TIN']/parent::td//following-sibling::td[1]//input";
	public static String Group_Name = "//label[text()='Group Name']/parent::td//following-sibling::td[1]//input";
	public static String Practitioner_DBA = "//label[text()='Practitioner DBA ']/parent::td//following-sibling::td[1]//input";
	public static String Group_Facet_ID = "//label[text()='OHBS Facets Provider Group ID']/parent::td//following-sibling::td[1]//input";
	public static String Group_Facet_ID_errMsg = "//label[text()='OHBS Facets Provider Group ID']/parent::td//following-sibling::td[1]//div";
	public static String Practitioner_Facet_ID = "//label[text()='OHBS Facets Prac ID']/parent::td//following-sibling::td[1]//input";
	public static String Practitioner_Facet_ID_errMsg = "//label[text()='OHBS Facets Prac ID']/parent::td//following-sibling::td[1]//div";
	public static String Secondary_Effective_Date = "//label[text()='Secondary Effective Date']/parent::td//following-sibling::td[1]//input";
	public static String Comment = "//label[text()='Comment']/parent::td//following-sibling::td[1]//textarea";
	public static String Current_TIN = "//label[text()='Current TIN']/parent::td//following-sibling::td[1]//input";
	public static String Current_TIN_errMsg = "//label[text()='Current TIN']/parent::td//following-sibling::td[1]//div";
	public static String Current_TIN_Model = "//label[text()='Current TIN Model']/parent::td//following-sibling::td[1]//select";
	public static String Current_TIN_Model_errMsg = "//label[text()='Current TIN Model']/parent::td//following-sibling::td[1]//div";
	public static String Current_TIN_Facets_Group_ID = "//label[text()='Current TIN OHBS Facets Group ID']/parent::td//following-sibling::td[1]//input";
	public static String Current_TIN_Term_Date = "//label[text()='Current TIN Term Date']/parent::td//following-sibling::td[1]//input";
	public static String Current_TIN_Owner_Name = "//label[text()='Current TIN Owner Name']/parent::td//following-sibling::td[1]//input";
	public static String New_TIN_Owner_Name = "//label[text()='New TIN Owner Name ']/parent::td//following-sibling::td[1]//input";
	public static String Current_Primary_Practice_State = "//label[text()='Current Primary Practice State']/parent::td//following-sibling::td[1]//input";
	public static String New_Primary_Practice_State = "//label[text()='New Primary Practice State']/parent::td//following-sibling::td[1]//input";
	public static String New_TIN_Number = "//label[text()='New TIN Number']/parent::td//following-sibling::td[1]//input";
	public static String New_TIN_Number_errMsg = "//label[text()='New TIN Number']/parent::td//following-sibling::td[1]//div";
	public static String New_TIN_Model = "//label[text()='New TIN Model']/parent::td//following-sibling::td[1]//select";
	public static String New_TIN_Model_errMsg = "//label[text()='New TIN Model']/parent::td//following-sibling::td[1]//div";
	public static String New_TIN_Facets_Group_ID = "//label[text()='New TIN OHBS Facets Group ID']/parent::td//following-sibling::td[1]//input";
	public static String Effective_Date_of_New_TIN = "//label[text()='Effective Date of New TIN']/parent::td//following-sibling::td[1]//input";
	public static String Effective_Date_of_New_TIN_errMsg = "//label[text()='Effective Date of New TIN']/parent::td//following-sibling::td[1]//div";
	public static String Does_New_TIN_have_MD_practice_Locations = "//label[text()='Does New TIN have Maryland Locations?']/parent::td//following-sibling::td[1]//select";
	public static String Does_New_TIN_have_MD_practice_Locations_errMsg = "//label[text()='Does New TIN have Maryland Locations?']/parent::td//following-sibling::td[1]//div";
	public static String Confirmation_date_to_provider_MD_only = "//label[text()='Confirm Date to Provider (Maryland only)']/parent::td//following-sibling::td[1]//input";
	public static String Urgency = "//label[text()='Urgency']/parent::td//following-sibling::td[1]//select";
	public static String Primary_Source = "//label[text()='Primary Source']/parent::td//following-sibling::td[1]//select";
	public static String Primary_Source_errMsg = "//label[text()='Primary Source']/parent::td//following-sibling::td[1]//div";
	public static String System_Effective_Date = "//label[text()='System Effective Date']/parent::td//following-sibling::td[1]//a";
	public static String System_Effective_Date_errMsg = "//label[text()='System Effective Date']/parent::td//following-sibling::td[1]//div";
	public static String Clinician_Initial_Cred_Date_Criteria_Met = "//label[text()='Clinician Initial Cred Date Criteria Met']/parent::td//following-sibling::td[1]//select";
	public static String Clinician_Initial_Cred_Date_Criteria_Met_errMsg = "//label[text()='Clinician Initial Cred Date Criteria Met']/parent::td//following-sibling::td[1]//div";
	public static String Effec_Date_Discrepency_with_Source_Doc = "//label[text()='Effec Date Discrepency with Source Doc?']/parent::td//following-sibling::td[1]//select";
	public static String Effec_Date_Discrepency_with_Source_Doc_errMsg = "//label[text()='Effec Date Discrepency with Source Doc?']/parent::td//following-sibling::td[1]//div";
	public static String Group_Initial_Cred_Date_Criteria_Met = "//label[text()='Group Initial Cred Date Criteria Met?']/parent::td//following-sibling::td[1]//select";
	public static String Effective_Date_Discrepancy_Explanation = "//label[text()='Effective Date Discrepancy Explanation']/parent::span/parent::td//following-sibling::td[1]//textarea";
	public static String Clinician_Cred_Status_Criteria_Met = "//label[text()='Clinician Cred Status Criteria Met?']/parent::td//following-sibling::td[1]//select";
	public static String Clinician_Cred_Status_Criteria_Met_errMsg = "//label[text()='Clinician Cred Status Criteria Met?']/parent::td//following-sibling::td[1]//div";
	public static String Reciprocity_State_Criteria_Met = "//label[text()='Reciprocity State Criteria Met?']/parent::td//following-sibling::td[1]//select";
	public static String Reciprocity_State_Criteria_Met_errMsg = "//label[text()='Reciprocity State Criteria Met?']/parent::td//following-sibling::td[1]//div";
	public static String Existing_TIN_Criteria_Met = "//label[text()='Existing TIN Criteria Met?']/parent::td//following-sibling::td[1]//select";
	public static String Existing_TIN_Criteria_Met_errMsg = "//label[text()='Existing TIN Criteria Met?']/parent::td//following-sibling::td[1]//div";
	public static String Provider_Currently_Pending_Inactivation = "//label[text()='Provider Currently Pending Inactivation?']/parent::td//following-sibling::td[1]//select";
	public static String Provider_Currently_Pending_Inactivation_errMsg = "//label[text()='Provider Currently Pending Inactivation?']/parent::td//following-sibling::td[1]//div";
	public static String Clinician_Contract_Loaded_in_OHBS_Facets = "//label[text()='Clinician Contract Loaded in OHBS Facets']/parent::td//following-sibling::td[1]//select";
	public static String Group_Contract_Loaded_in_OHBS_Facets = "//label[text()='Group Contract Loaded in OHBS Facets?']/parent::td//following-sibling::td[1]//select";
	public static String Is_Grp_Changing_Both_TIN_Id_and_Name = "//label[text()='Is Grp Changing Both TIN Id and Name?']/parent::td//following-sibling::td[1]//select";



}
