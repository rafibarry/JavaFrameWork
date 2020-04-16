package Test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaptureScreenShot {
	
	//Capture screenshot of web element
	@Test
	public void screenshotTest() throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		
		WebElement logo = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[5]/span[1]/center[1]/div[1]/div[1]/a[1]/img[1]"));
		File file = logo.getScreenshotAs(OutputType.FILE);
		
		File destFile = new File("logo.png");
		FileUtils.copyFile(file, destFile);
		
		driver.quit();
	}

}
