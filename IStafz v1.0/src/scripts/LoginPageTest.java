package scripts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generics.BaseTest;
import generics.Utility;
import pompages.actiTIMELogin;

public class LoginPageTest extends BaseTest{
	Logger log = Logger.getLogger("LoginPageTest");
	
	@DataProvider
	public Object[][] getDataFromValidLogin() {
		Object[][] obj = Utility.getDataFromSheet("ValidLogin");
		return obj;
	}
	
	@Test(priority = 1, dataProvider = "getDataFromValidLogin", enabled = true)
	public void testValidLogin(String UN,String PW) {
		actiTIMELogin lp = new actiTIMELogin(driver);
		lp.setUsername(UN); //Entering the Username.
		log.info("Username entered into username text field.");
		lp.setPassword(PW); //Entering the Password.
		log.info("Password entered into password text field.");
		lp.clickOnLogin(); //Clicking on Login Button.
		log.info("Clicked on Login button.");
		
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
		log.info("Trying to login ith Invalid username and password.");
		Assert.assertTrue(lp.verifyErrorMessage());
	}

}
