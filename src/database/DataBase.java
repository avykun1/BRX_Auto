package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import utils.CommonMethods;

public class DataBase {
	public static String path;

	public static int foo, rowCnt = 0, colCnt = 0;
	public static String param2, param3, param4, databaseactivity, currentDatum = null, currentQuery = null;
	public static Connection conn = null;
	long duration = 0;
	public static ArrayList<String> names = new ArrayList<String>();
	public static ArrayList<String> name1 = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> dbResultSet = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> dbResultHeader = null;
	public static ArrayList<String> currentRow = null, currentCol = null;
	public static boolean dbConnected = false;

	
	public static ResultSet executeQuery(String query) throws SQLException {
		try {
			getDbConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println(query);
			ResultSet rset = stmt.executeQuery(query);
			System.out.println("Select Query Executed Successfully");
			return rset;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public static void executeUpdate(String query) throws SQLException {
		try {
			System.out.println(" Query is : " + query);
			getDbConnection();
			Statement stmt = conn.createStatement();
			System.out.println(query);
			stmt.executeUpdate(query);
			stmt.executeUpdate("Commit");
			System.out.println(" Update Query Executed Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("executeUpdate","Exception in SQL DB Update.", "failed");
		}
	}

	public static void query1(String Query1) throws Exception {
		/* @pranay */
		ResultSet rs2 = regularQueryHit(Query1);
		rs2.next();
		param2 = rs2.getString(1);
		param3 = rs2.getString(2);
		param4 = rs2.getString(3);
		System.out.println(param2);
		System.out.println(param3);
		System.out.println(param4);
		rs2.close();

	}

	
	

	
	@SuppressWarnings("static-access")
	public static ResultSet regularQueryHit(String Qry) throws SQLException {
		ResultSet rs2 = null;
		try {
			System.out.println(" CASE 1*******");
			ResultSet rs = (new DataBase()).executeQuery(Qry);
			/* printing all the rows */
			while (rs.next()) {
				System.out.print(" ");
				try {
					for (int i = 1; rs.getString(i) != null; i++) {
					}
				} catch (Exception exp) {
				}
			}
			System.out.println(" ");
			// System.out.println(" CASE 2*******");
			rs2 = (new DataBase()).executeQuery(Qry);
			return rs2;
		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			// Fail Test if exception occurs
			CommonMethods.fnTestResult("regularQueryHit","Exception in DB connection", "failed");
		}
		return rs2;
	}


	public static Connection getDbConnection() throws SQLException {
		String url = null;
		// look for the db driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			url = CommonMethods.fnGetEnvironmentDataElementValue("DB_CONNECTION_STRING");
			Properties dbProps = new Properties();
			dbProps.setProperty("driver", "oracle.jdbc.OracleDriver");
			dbProps.setProperty("user", CommonMethods.fnGetEnvironmentDataElementValue("DB_USER_NAME"));
			dbProps.setProperty("password", CommonMethods.fnGetEnvironmentDataElementValue("DB_PASSWORD"));
			// create connection
			conn = DriverManager.getConnection(url, dbProps);
		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			CommonMethods.fnTestResult("getDbConnection","Exception in DB connecton", "failed");
		}
		return conn;
	}

	
	@SuppressWarnings("static-access")
	public static void queryCount(String Query1) throws SQLException {
		try {
			System.out.println(Query1);
			// System.out.println(" Query Count method start **********");
			ResultSet rs = (new DataBase()).executeQuery(Query1);
			rs.last();
			param2 = String.valueOf(rs.getRow());
			// param2 = rs.getString(1);

		} catch (Exception e) {
			System.out.println("Exception Details" + e);
			CommonMethods.fnTestResult("queryCount","Exception in DB connecton", "failed");
		}

	}
	/**
	 * Thread safe method to get count
	 */
	public static int getQueryCount(String query) throws SQLException{
		int count = -1;
		try {
			System.out.println(query);
			// System.out.println(" Query Count method start **********");
			ResultSet rs = DataBase.executeQuery(query);
			rs.last();
			count = Integer.parseInt(String.valueOf(rs.getRow()));

		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("getQueryCount","Exception in DB connecton", "failed");
		}
		return count;
	}

	public static String fnExecuteQueryReturnFirstColumn(String strQuery) {
		String strColumnValue = "";
		try {
			ResultSet objResultSet = DataBase.executeQuery(strQuery);
			objResultSet.next();
			strColumnValue = objResultSet.getString(1);
			objResultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
			CommonMethods.fnTestResult("fnExecuteQueryReturnFirstColumn","Exception Occured While retriving CaseID from DB", "failed");
		}
		return strColumnValue;
	}
}