//to run before starting Jenkins:
//java -Dhudson.model.DirectoryBrowserSupport.CSP="" -jar jenkins.war

package Test;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;




public class ExtentReportsWithSpecialClass {

	private static WebDriver driver;
	ExtentReports report;
	static com.relevantcodes.extentreports.ExtentTest test;
	String reportsPath = "C:\\Users\\Rafi\\workspace\\SeleniumJavaFrameWork\\test-output\\HTML-Reports\\";
	static String baseUrl;
	
	@BeforeClass
	public void setUp() throws Exception {
	/*	String absolutePath = projectPath + "\\test-output\\HTML-Reports\\";
		File existingFile = new File(absolutePath + "extent.html");
		
		Path filePath = existingFile.toPath();
		BasicFileAttributes attributes = null;
		if (existingFile.exists()) {
			attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
			FileTime creationTime = attributes.creationTime();
			
			//FileTime creationTime = view.creationTime();
			DateFormat df = new SimpleDateFormat("HH.mm.ss dd-MM-yyyy");
			String cTime = df.format(creationTime.toMillis());
			
			String newName = "extent_"+cTime+".html";
			String DestPath = projectPath + "\\test-output\\HTML-Reports\\" + newName;
	
			existingFile.renameTo(new File(DestPath));
			
		} */
		baseUrl = "https://www.google.com";
		report = ExtentFactory.createReportInstance();
		test = report.startTest("Verify welcome text");
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Browser Started");
		
	}
	
	
	@Test
	public static void test1() throws Exception {
		
		driver.get(baseUrl);
		driver.findElement(By.name("q")).sendKeys("Automation");
		test.log(LogStatus.INFO,"Entered text in search box");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		test.log(LogStatus.INFO,"Press the 'Enter' key");
		String title= driver.getTitle();
		//Assert.assertEquals("Google", title, "Title is not the expected one");
		SoftAssert assertion = new SoftAssert();
		assertion.assertEquals(title, "Goole");
		
		}
	
	@AfterMethod
	public void getTestResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Failure reason: " + result.getThrowable());
				
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test case skipped is " + result.getName());		
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test case passed is " + result.getName());		
			
		}
	}
	
	@AfterTest
	public void tearDownTest() {
		// calling flush writes everything to the log file
        report.flush();
		driver.close();
		driver.quit();
		System.out.println("Test Completed successfully");
		
	}
}
