package baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Baseclass {

    // ThreadLocal driver for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private Properties prop;

    @BeforeClass
    public void setup() throws IOException {
        loadConfig();
        launchBrowser();
    }
    private void loadConfig() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config.properties");
        prop.load(fs);
    }

    private void launchBrowser() {
        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--incognito");

            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");

            driver.set(new ChromeDriver(options));  // ✅ ThreadLocal set
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(prop.getProperty("url"));
    }

    // Getter method for driver
    public static WebDriver getDriver() {
        return driver.get();  // ✅ ThreadLocal get
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();  // ✅ cleanup ThreadLocal
            //hello
        }
    }
}