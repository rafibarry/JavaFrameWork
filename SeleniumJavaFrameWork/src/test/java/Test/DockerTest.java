package Test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class DockerTest {

	@Test
	public void test1() throws MalformedURLException {
		
		DesiredCapabilities dc = DesiredCapabilities.chrome(); // Specifying on which browser we want to execute.
		// DesiredCapabilities dc = DesiredCapabilities.firefox(); // if FF is needed instead.
		URL url = new URL("http://localhost:4444/wd/hub"); // Specifying where the Selenium runs / the selenium HUB URL. 
		//4444 - is port number of the container that is running
		
		RemoteWebDriver driver = new RemoteWebDriver(url, dc);

		driver.get("http://www.google.com");
		System.out.println("Title of the page: " + driver.getTitle());
		driver.quit();
	}
}
