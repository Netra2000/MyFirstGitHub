package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utils{
	
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions action;
	
	public utils(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		this.action=new Actions(driver);
	}
	
	 public void click(By locator) {
	        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	    }

	    public void type(By locator, String text) {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
	    }
	    
	    public void hover(By locator) {
	    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	      action.moveToElement(element).perform();
	    }

	    public void scrollIntoView(WebElement element) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    }

	    public void jsSetValue(By locator, String value) {
	    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].value='" + value + "'; arguments[0].dispatchEvent(new Event('change'));", element);
	    }
	    public void send(By locator, String value) {
	    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	       element.sendKeys(value);
	    }

	    public List<WebElement> getElements(By locator) {
	        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	    }
	    
	    public void clickSuggestion(String suggestionText,By locator) {
	        boolean clicked = false;
	        int attempts = 0;

	        while (attempts < 3 && !clicked) {
	            try {
	                List<WebElement> suggestionList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	                for (WebElement suggestion : suggestionList) {
	                    if (suggestion.getText().equalsIgnoreCase(suggestionText)) {
	                        wait.until(ExpectedConditions.elementToBeClickable(suggestion));
	                        action.moveToElement(suggestion).click().perform();
	                        clicked = true;
	                        break;
	                    }
	                }
	            } catch (StaleElementReferenceException e) {
	                System.out.println("Suggestions refreshed, retrying...");
	                attempts++;
	            }
	        }

	        if (!clicked) {
	            throw new RuntimeException("Suggestion '" + suggestionText + "' not found after retries");
	        }
	    }
	}

	
	
