package generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Utility extends BaseTest {
	
	public static FileInputStream file;
	public static Workbook wb;

	public static String getProperty(String CONFIG_PATH, String key) {
		String property = "";
		Properties p = new Properties();
		try {
			file = new FileInputStream(CONFIG_PATH);
			p.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		property = p.getProperty(key);
		return property;
	}

	public static String getCellValue(String excelPath, String sheet, int r, int c) {
		String cellData = "";
		try {
		file = new FileInputStream(excelPath);
		wb = WorkbookFactory.create(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
		cellData = wb.getSheet(sheet).getRow(r).getCell(c).toString();
		return cellData;
	}
	
	public static int getRowCount(String excelPath, String sheet) {
		int rowCount;
		try {
		file = new FileInputStream(excelPath);
		wb = WorkbookFactory.create(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
		rowCount = wb.getSheet(sheet).getLastRowNum();
		return rowCount;
	}
	
	public static void captureScreenshot(WebDriver driver, String testCaseName) {
		Date d = new Date();
		String currentDate = d.toString().replace(":", "_");
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".//sceenshots/"+testCaseName+"_"+currentDate+".png");
		try {
			Files.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
