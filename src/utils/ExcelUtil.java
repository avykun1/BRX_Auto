package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public static String TESTDATA_SHEET_PATH="C:\\Users\\avykun1\\Desktop\\DMR\\DMR_Automation-master\\TestData\\DataProvider.xlsx";
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static ArrayList<Object[]> al;
	
	public static ArrayList<Object[]> getSheetData(String sheetname) throws IOException {
		FileInputStream fis=new FileInputStream(TESTDATA_SHEET_PATH);
		workbook=new XSSFWorkbook(fis);
		int index=workbook.getSheetIndex(sheetname);
		sheet=workbook.getSheetAt(index);
		Iterator<Row> rows=sheet.iterator();
		rows.next();
		al=new ArrayList<Object[]>();
		while(rows.hasNext()) {
			Row row=rows.next();
			Object[] ob=new Object[row.getLastCellNum()];
			for(int i=0;i<row.getLastCellNum();i++) {
				if(row.getCell(i).getCellType()==CellType.STRING)
					ob[i]=row.getCell(i).getStringCellValue();
				else
					ob[i]=NumberToTextConverter.toText(row.getCell(i).getNumericCellValue());
				}
			al.add(ob);
		}
		return al;
		
	}
	
}
