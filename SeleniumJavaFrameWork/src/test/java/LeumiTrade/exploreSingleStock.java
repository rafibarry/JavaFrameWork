package LeumiTrade;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class exploreSingleStock {
	WebDriver driver;

	@Test
	public void stockExplore() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://trade.bankleumi.co.il/trade/net/info/general/home.aspx");
		
		leumiTradeHomePage homePage = new leumiTradeHomePage(driver);
		
		homePage.generalSearch("teva");
		
		//driver.quit();
	}
}
