package api.testing;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class Amm {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @Test
    public void launch() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        actions = new Actions(driver);

        driver.get("https://www.amazon.in");

        // Hover over account menu
        By accountMenu = By.xpath("//div[@class='nav-line-1-container']//following-sibling::span");
        WebElement menu = driver.findElement(accountMenu);
        actions.moveToElement(menu).perform();

        // Click on "Your Orders"
        By yourOrders = By.xpath("//a[@id='nav_prefetch_yourorders']/span");
        retryingClick(yourOrders);

        driver.navigate().back();

        // Search for lipsticks
        By searchBox = By.id("twotabsearchtextbox");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBox)).sendKeys("lipsticks");

        // Handle dynamic suggestions (lipsticks mac)
        By suggestions = By.xpath("//div[@class='left-pane-results-container']/div[starts-with(@id,'sac-suggestion-row')]");
        boolean clicked = false;
        int attempts = 0;

        while (attempts < 3 && !clicked) {
            try {
                List<WebElement> suggestionList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestions));
                for (WebElement suggestion : suggestionList) {
                    if (suggestion.getText().equalsIgnoreCase("lipsticks mac")) {
                        wait.until(ExpectedConditions.elementToBeClickable(suggestion));
                        actions.moveToElement(suggestion).click().perform();
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
            throw new RuntimeException("Suggestion 'lipsticks mac' not found after retries");
        }

        // Store parent window
        String parent = driver.getWindowHandle();

        // Handle product results
        By products = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[@role='listitem']");
        List<WebElement> productList = driver.findElements(products);

        for (int i = 1; i <= productList.size(); i++) {
            By productTitle = By.xpath("(//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[@role='listitem']//h2/span)[" + i + "]");
            String title = driver.findElement(productTitle).getText();

            if (title.contains("L’Oreal Paris Infallible Matte Resistance Liquid Lipstick- 635 Worth It Medium, 5ml")) {
                retryingClick(productTitle);
                break;
            }
        }

        // Switch to product tab
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Select quantity using JavaScript to ensure Amazon registers the change
        WebElement qtyDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("quantity")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='2'; arguments[0].dispatchEvent(new Event('change'));", qtyDropdown);
        System.out.println("Quantity set to 2 via JavaScript.");

        // Add to cart
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();
        System.out.println("Product added to cart.");

        driver.quit();
    }

    // Utility method for retrying clicks
    public void retryingClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(locator)));
                actions.moveToElement(element).click().perform();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Element not stable after retries: " + locator);
    }
}