package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class actiTIMELogin {
	
	@FindBy(id="username")
	private WebElement unTextBox;
	
	@FindBy(name="pwd")
	private WebElement pwTextBox;
	
	@FindBy(id="loginButton")
	private WebElement loginBtn;
	
	@FindBy(xpath="//span[contains(text(),'Username or Password is invalid. Please try again.')]")
	private WebElement errorMsg;
	
	public actiTIMELogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setUsername(String UN) {
		unTextBox.sendKeys(UN);
	}
	
	public void setPassword(String PW) {
		pwTextBox.sendKeys(PW);
	}
	
	public void clickOnLogin() {
		loginBtn.click();
	}
	
	public void loginAsAdmin(String UN, String PW) {
		unTextBox.sendKeys(UN);
		pwTextBox.sendKeys(PW);
		loginBtn.click();
	}
	
	public void verifyErrorMessage() {
		Assert.assertTrue(errorMsg.isDisplayed());
	}

}
