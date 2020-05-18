package LeumiTrade;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class leumiTradeHomePage {

	WebDriver driver;
	
	By searchTextBox = By.id("txtSmartSearch");
	By searchButton = By.id("btnSearch");
	
	
	public leumiTradeHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void generalSearch(String name) {
			
		driver.findElement(searchTextBox).sendKeys(name);
		
		List<WebElement> autoSuggest = driver.findElements(By
	            .xpath("//body//div[@class='brd_dark']//tr//tr[1]//td[1]"));
	        // this verify the size of the list. But in this case, i gave the xpath of only the first row from results, 
			//so length will be always 1.
			
	        System.out
	            .println("Size of the AutoSuggets is = " + autoSuggest.size());
	        // print the auto suggest
	        for (WebElement a : autoSuggest)
	            System.out.println("Values are = " + a.getText());
	        // suppose now you want to click on 3rd auto suggest then simply do like
	        // this
	        autoSuggest.get(0).click();
	        
	    
	
	}
	
}
