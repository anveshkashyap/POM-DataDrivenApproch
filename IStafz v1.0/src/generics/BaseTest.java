package generics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTest implements IAutoConstant{

	public static WebDriver driver;
	
	@BeforeSuite
	public void initializeBrowsers() {
		System.setProperty(CHROME_KEY, CHROME_PATH);
		System.setProperty(GECKO_KEY, GECKO_PATH);
	}
	
	@Parameters({"browser"})
	@BeforeMethod
	public void openTheBrowser(String browser) {
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		int timeoutPeriod = Integer.parseInt(Utility.getProperty(CONFIG_PATH, "ImplicitTimeout"));
		driver.manage().timeouts().implicitlyWait(timeoutPeriod, TimeUnit.SECONDS);
		String url = Utility.getProperty(CONFIG_PATH, "URL");
		driver.get(url);
	}
	
	@AfterMethod
	public void closeTheBrowser(ITestResult result) {
		if(ITestResult.FAILURE==result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}
		driver.close();
	}
}
