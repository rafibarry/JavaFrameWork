import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserTest {

	public static void main(String[] args) {
		
		//String projectPath = System.getProperty("user.dir");
		//System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/ChromeDriver/chromedriver.exe");
		WebDriverManager.chromedriver().setup(); // - takes latest version
		//WebDriverManager.chromedriver().version("2.36").setup();  - if you want to use a specific version.
		WebDriver driver = new ChromeDriver();
		
		
		
		//WebDriverManager.iedriver().setup(); // - takes latest version	
		//System.setProperty("webdriver.ie.driver", projectPath+"/Drivers/InternetExplorer/IEDriverServer.exe");
		//WebDriver driver = new InternetExplorerDriver();
		
		driver.get("http://google.com/");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement textBox = driver.findElement(By.name("q"));
		textBox.sendKeys("Automation Step by Step");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		driver.close();
	}
}
