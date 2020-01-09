package scripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generics.BaseTest;
import generics.Utility;
import pompages.actiTIMELogin;

public class LoginPageTest extends BaseTest{
	
	@DataProvider
	public Object[][] getDataFromValidLogin() {
		Object[][] obj = Utility.getDataFromSheet("ValidLogin");
		return obj;
	}
	
	@Test(priority = 1, dataProvider = "getDataFromValidLogin", enabled = true)
	public void testValidLogin(String UN,String PW) {
		actiTIMELogin lp = new actiTIMELogin(driver);
		lp.setUsername(UN); //Entering the Username.
		lp.setPassword(PW); //Entering the Password.
		lp.clickOnLogin(); //Clicking on Login Button.
		
	}
	
	@DataProvider
	public Object[][] getDataFromInvalidLogin() {
		Object[][] obj = Utility.getDataFromSheet("InvalidLogin");
		return obj;
	}
	
	@Test(priority = 2,dataProvider = "getDataFromInvalidLogin", enabled = true)
	public void testInvalidLogin(String UN, String PW) {
		actiTIMELogin lp = new actiTIMELogin(driver);
		lp.loginIntoActiTIME(UN, PW);
		Assert.assertTrue(lp.verifyErrorMessage());
	}

}
