package scripts;

import org.testng.annotations.Test;

import generics.BaseTest;
import generics.Utility;
import pompages.actiTIMELogin;

public class TestValidLoginAsAdmin extends BaseTest{
	
	@Test
	public void testValidLogin() {
		
		String UN = Utility.getCellValue(EXCEL_PATH, "ValidLogin", 1, 0);
		String PW = Utility.getCellValue(EXCEL_PATH, "ValidLogin", 1, 1);
		actiTIMELogin lp = new actiTIMELogin(driver);
		lp.setUsername(UN); //Entering the Username.
		lp.setPassword(PW); //Entering the Password.
		lp.clickOnLogin(); //Clicking on Login Button.
		
	}

}
