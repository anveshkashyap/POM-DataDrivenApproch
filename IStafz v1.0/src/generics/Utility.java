package generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;

public class Utility extends BaseTest {
	
	public static FileInputStream file;

	public static String getProperty(String key) {
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
	
	public static void captureScreenshot(String testCaseName) throws IOException {
		Date d = new Date();
		String currentDate = d.toString().replace(':', '_').replace(' ', '_');
		String currentDir = System.getProperty("user.dir");
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File(currentDir+"/screenshots/"+testCaseName+"_"+currentDate+".png"));
		
	}
	
	public static Object[][] getDataFromSheet(String sheet) {
		Xls_Reader reader = new Xls_Reader(EXCEL_PATH);
		Object[][] data = new Object[reader.getRowCount(sheet)-1][reader.getColumnCount(sheet)];
		
		for (int i = 0; i < reader.getRowCount(sheet)-1; i++) {
			for (int k = 0; k < reader.getColumnCount(sheet); k++) {
				data[i][k] = reader.getCellData(sheet, k, i+2).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
		
		
		
	}
	
	
	

}
