package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import config.PropertiesFile;

import Pages.GoogleSearchPageObjects;

public class TestNG_googleSearch {
	
	static WebDriver driver = null;
	public static String UsedBrowser;
	
	
	@BeforeTest
	public void setUpTest() {
		
		PropertiesFile.getProperties();
		
		if(UsedBrowser.equalsIgnoreCase("chrome")) {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		}
		else {
			if(UsedBrowser.equalsIgnoreCase("Internet Explorer")) {
				String projectPath = System.getProperty("user.dir");
				System.setProperty("webdriver.ie.driver", projectPath+"/Drivers/InternetExplorer/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			
			}
		}
	}

	@Test
	public static void googleSearch() {
		
		GoogleSearchPageObjects searchPageObj = new GoogleSearchPageObjects(driver);
				
		driver.get("https://google.com/");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		searchPageObj.setTextInSearchBox("Automation Step by Step");
		searchPageObj.clickSearchButton();
								
	}
	
	@AfterTest
	public void tearDownTest() {
		driver.close();
		System.out.println("Test Completed successfully");
		PropertiesFile.setProperties();
	}
	
}
