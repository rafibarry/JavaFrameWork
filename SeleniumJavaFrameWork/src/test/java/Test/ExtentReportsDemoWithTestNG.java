//to run before starting Jenkins:
//java -Dhudson.model.DirectoryBrowserSupport.CSP="" -jar jenkins.war
// New Comment

package Test;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import junit.framework.Assert;

//import com.relevantcodes.extentreports.ExtentReports;


public class ExtentReportsDemoWithTestNG {

	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	static WebDriver driver = null;
	String projectPath = "C:\\Users\\Rafi\\workspace\\SeleniumJavaFrameWork";
	static ExtentTest test1;
	boolean exist;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		String absolutePath = projectPath + "\\test-output\\HTML-Reports\\";
		File existingFile = new File(absolutePath + "extent.html");
		
		Path filePath = existingFile.toPath();
		BasicFileAttributes attributes = null;
		if (existingFile.exists()) {
			attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
			FileTime creationTime = attributes.creationTime();
			
			//FileTime creationTime = view.creationTime();
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
			String cTime = df.format(creationTime.toMillis());
			
			String newName = "extent_"+cTime+".html";
			String DestPath = projectPath + "\\test-output\\HTML-Reports\\" + newName;
	
			existingFile.renameTo(new File(DestPath));
			
		}
		htmlReporter = new ExtentHtmlReporter(projectPath + "/test-output/HTML-Reports/extent.html");
		//extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/ExtentReport.html", true);
		htmlReporter.config().setDocumentTitle("Automation report");
		htmlReporter.config().setReportName("Functional Report");

	
		extent = new ExtentReports();		
		extent.attachReporter(htmlReporter);		
		
	}
	
	@BeforeTest
	public void setUpTest() {
	//	String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public static void test1() throws Exception {
		//SoftAssert softAssert = new SoftAssert();
		test1 = extent.createTest("Google Search Test1", "This is a test to validate google functionality");
		driver.get("https://google.com");
		String title = driver.getTitle();
		if (title.equalsIgnoreCase("google")) {
			test1.pass("Title is correct");
		}
		else {
				test1.fail("Title is not as expected: got " + title + " instead of 'google'");
		}
		
		Assert.assertEquals("Wrong Status", true, false);
		
	//	test1.pass("Title is correct");
		//softAssert.assertEquals("Goole", title, "Titles not match");
		
		driver.findElement(By.name("q")).sendKeys("Automation");
		test1.pass("Entered text in search box");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		test1.pass("Press the 'Enter' key");
		driver.close();
		driver.quit();
		test1.pass("Closed the browser");
		test1.info("Test Completed");
//		softAssert.assertAll();
		}
	
	@Test
	public static void test2() throws Exception {
		ExtentTest test2 = extent.createTest("Google Search Test2", "This is a test to validate google functionality");
		// log(Status, details)
        test2.log(Status.INFO, "This step shows usage of log(status, details)");

        // info(details)
        test2.info("This step shows usage of info(details)");
        
        // log with snapshot
        test2.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        
        // test with snapshot
        test2.addScreenCaptureFromPath("screenshot.png");	
	}

	@AfterMethod
	public void getTestResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test1.log(Status.FAIL, "Test case failed is " + result.getName());
			test1.log(Status.FAIL, "Test case failed is " + result.getThrowable());
				
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test1.log(Status.SKIP, "Test case skipped is " + result.getName());		
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test1.log(Status.PASS, "Test case passed is " + result.getName());		
			
		}
		
	}
	
	@AfterTest
	public void tearDownTest() {
		
		// calling flush writes everything to the log file
		extent.flush();
//		driver.close();
		System.out.println("Test Completed successfully");
//		driver.quit();
	} 
	
	
}
