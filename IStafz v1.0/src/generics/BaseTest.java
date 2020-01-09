package generics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import generics.IAutoConstant;

public class BaseTest implements IAutoConstant{

	public static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
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
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		int timeoutPeriod = Integer.parseInt(Utility.getProperty("ImplicitTimeout"));
		driver.manage().timeouts().implicitlyWait(timeoutPeriod, TimeUnit.SECONDS);
		String url = Utility.getProperty("URL");
		driver.get(url);
	}
	
	
	@AfterMethod
	public void closeTheBrowser() {
		/*
		 * if(ITestResult.FAILURE==result.getStatus()) {
		 * Utility.captureScreenshot(driver, result.getName()); }
		 */
		driver.quit();
	}
}
