package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendReportsBasicDemo {
	
	private static WebDriver driver = null;

	public static void main(String[] args) {

	// initialize the HtmlReporter
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

	// initialize ExtentReports and attach the HtmlReporter
	ExtentReports extent = new ExtentReports();
	
	// attach only HtmlReporter
	extent.attachReporter(htmlReporter);
	
	// creating tests
	ExtentTest test1 = extent.createTest("Google Search Test1", "This is a test to validate google functionality");
	
	String projectPath = System.getProperty("user.dir");
	System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/ChromeDriver/chromedriver.exe");
	driver = new ChromeDriver();
	
	test1.log(Status.INFO, "Starting Test Case");
	
	driver.get("https://google.com");
	
	test1.pass("Navigating to google.com");
	driver.findElement(By.name("q")).sendKeys("Automation");
	test1.pass("Entered text in search box");
	driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	test1.pass("Press the 'Enter' key");
	driver.close();
	driver.quit();
	test1.pass("Closed the browser");
	test1.info("Test Completed");
	
	//calling flush, writes everything to the log file;
	extent.flush();
	}

}
