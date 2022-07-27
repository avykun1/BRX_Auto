package configuration;

import java.util.ArrayList;
import java.util.List;

public class Common {
	public static String Environment = null;
	public static String XML_PATH = "C:\\Users\\avykun1\\Desktop\\DMR\\DMR_Automation-master\\src\\configuration\\properties.xml";
	public static String strTestType = null;
	public static String className, StrScreenShotsFolder;
	public static boolean almConnEst = false;
	public static String ExecTime;
	public static String Path2;
	public static String strTestResult;
	public static String firstFail = null;
	public static String Temp1, Temp2, Temp3, Temp4, Temp5, Temp6, Temp7, Temp8, Temp9, Temp10;
	public static int intTestStep=0;
	public static int intInternalCount =0;
	public static String classicUrl="https://cs51.salesforce.com/home/home.jsp?source=lex";
	
	//For new HTML report
	public static String reportfilePath=null;
	public static String suiteName = null;
	public static String totaltests;
	public static String passedtests;
	public static List<String>FailedTests  = new ArrayList<>();
	public static List<String>Tests = new ArrayList<>();
	
	// Separate Line  
	public static String strLineSeparator = "----------------------------------------------------------------------------------------------------"; 
}
