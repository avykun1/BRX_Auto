package utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



public class ResultsLib {
	
	static String reportfilePath = null;
	
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
			reportfilePath = reportfile.getAbsolutePath();
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
	
	
	public static void fnGenerateHtmlReportTable( String strTestName) throws Exception {
		try {
			
			String header = "<html><head><title>TestReport</title></head><body link='orange'><table border='0' cellspacing='1' cellpadding='1' width='100%'><tr><td align='left'><img src='"
					+ System.getProperty("user.dir")
					+ "\\Optum.PNG' alt='Logo'height='50' width='100'/></td></tr><tr><td align='center'><h4 align='center'><font face='arial'  color='#153e7e' size='5'><b><center>Detailed Report</center></b></font></h4></td></tr><tr></tr></table>";
			
			String table = "<table border='1' cellspacing='1' cellpadding='1' width='80%'>"
					+ "		<tr><td colspan='3'><h4 align='center'><font color='black' size='4' face='Arial'><b>"+strTestName
					+ "</b></font></h4></td></tr><tr></tr><tr><td bgcolor='#153e7e' width='30%' align='center' valign='middle'><font color='#e0e0e0' size='2' face='Arial'><b>Step</b></td><td bgcolor='#153e7e' align='center' valign='middle'><font color='#e0e0e0' size='2' face='Arial'><b>Description</b></font></td><td bgcolor='#153e7e' align='center' valign='middle' width='10%'><font color='#e0e0e0' size='2' face='Arial'><b>Status</b></font></td></tr>";
			
			String template;
			File reportfile = new File(System.getProperty("user.dir") + "\\DetailedTestReport.html");
			FileWriter fw;
			if (reportfilePath == null) {
				reportfile.createNewFile();
				reportfilePath = reportfile.getAbsolutePath();
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
		}
	}
	
	public static void fnTestResult(String strExceptedResult, String strDescription, String strResultStatus, boolean screenshot) throws IOException {
		String template;
		String reportfilePath = System.getProperty("user.dir") + "\\DetailedTestReport.html";
		if (strResultStatus.equalsIgnoreCase("Pass")) 
		{
			if (screenshot) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
				String strDate = dateFormat.format(new Date());
				String hrefString = System.getProperty("user.dir")+ "\\Screenshots\\"+strDate+ ".png";			 
				CommonMethods.fnTakeScreenShot(strResultStatus);
			
				template = "<tr><td width='30%' align='center'><font color='#153e7e' size='1' face='Arial align='center''><b>" + strExceptedResult
						+ "</b></font></td><td align='center'><font color='#153e7e' size='1' face='Arial' align='center'><b>"  + strDescription
						+ " </b></font></td><td width='10%' bgcolor='green' align='center'><font color='white' size='1' face='Arial'><b><a href='"
						+ hrefString + "'>Passed</a></b></font></td></tr>";
			}
			else
				template = "<tr><td width='30%' align='center'><font color='#153e7e' size='1' face='Arial align='center''><b>" + strExceptedResult
						+ "</b></font></td><td width='30%' align='center'><font color='#153e7e' size='1' face='Arial' align='center'><b>" + strDescription
						+ "</b></font></td><td width='10%' bgcolor='green' align='center'><font color='white' size='1' face='Arial'><b>Passed</b></font></td></tr>";

			
			File reportfile = new File(reportfilePath);
			FileWriter fw;
			fw = new FileWriter(reportfile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(template);
			bw.flush();
			bw.close();
			
		}
		else {
			// Common.FailedTests.add(Common.className);
			//fnTakeScreenShot(strResultStatus);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String strDate = dateFormat.format(new Date());
			String hrefString = System.getProperty("user.dir")+ "\\Screenshots\\"+strDate+ ".png";			 
			CommonMethods.fnTakeScreenShot(strResultStatus);
			template = "<tr><td width='30%' align='center'><font color='#153e7e' size='1' face='Arial align='center''><b>" + strExceptedResult
					+ "</b></font></td><td align='center'><font color='#153e7e' size='1' face='Arial' align='center'><b>"  + strDescription
					+ " </b></font></td><td width='10%' bgcolor='red' align='center'><font color='white' size='1' face='Arial'><b><a href='"
					+ hrefString + "'>Failed</a></b></font></td></tr>";
			File reportfile = new File(reportfilePath);
			FileWriter fw;
			fw = new FileWriter(reportfile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(template);
			bw.flush();
			bw.close();
			
			
		}
	}
	
	
	public static void editDetailedReport(int totaltests, int totalpassed, int totalfailed, int skipped) throws Exception {
		try {
			String reportfilePath = System.getProperty("user.dir") + "\\DetailedTestReport.html";
			File reportfile = new File(reportfilePath);
			FileReader fr;
			fr = new FileReader(reportfile.getAbsoluteFile());
			BufferedReader br = new BufferedReader(fr);
			String totalStr = "";
			String s;
			while ((s = br.readLine()) != null) {
	            totalStr = totalStr+s;
	        }
	        totalStr = totalStr.replaceAll("Totalvalue",totaltests+"").replaceAll("Totalpassvalue",totalpassed+"").replaceAll("Totalfailvalue",totalfailed+"").replaceAll("Totalskipvalue",skipped+"");
	        br.close();
			fr.close();	        
			FileWriter fw;
			fw = new FileWriter(reportfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(totalStr);
			//fw.write(totalStr);
			bw.flush();
			bw.close();
			fw.close();
						
		} catch (Exception ex) {
			System.out.println(ex.toString());		
		}
	}

	
	
	


}
