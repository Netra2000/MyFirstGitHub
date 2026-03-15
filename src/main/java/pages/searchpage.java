
	package pages;

	import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utils.utils;

import java.time.Duration;
import java.util.List;

	public class searchpage {
	    private WebDriver driver;
	    private utils u;
	

	    // Locator for product titles
	    private By productTitles = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//h2/span");
	    By suggestions = By.xpath("//div[@class='left-pane-results-container']/div[starts-with(@id,'sac-suggestion-row')]");
	    public searchpage(WebDriver driver) {
	        this.driver = driver;
	        this.u = new utils(driver);
	     
	    }
	    
      
    

	   

    public void suggestion() {
    	u.clickSuggestion("lipsticks mac", suggestions);
    }



	    // Click product by name
	    public void clickProduct(String productName) {
	        List<WebElement> products = driver.findElements(productTitles);
	        for (WebElement product : products) {
	            if (product.getText().contains(productName)) {
	                u.scrollIntoView(product);
	                product.click();
	                break;
	            }
	        }
	    }
	}

