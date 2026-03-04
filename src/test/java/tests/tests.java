package tests;

import baseclass.Baseclass;
import pages.homepage;
import pages.searchpage;
import utils.ExcelUtils;
import utils.ExtentManager;
import pages.productPage;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.*;
import org.testng.annotations.*;

public class tests extends Baseclass {
	@DataProvider(name="excelData") 
	public Object[][] getExcelData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx"; 
		return ExcelUtils.getTestData(path, "Products"); 
		}
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @Test(dataProvider="excelData")
    public void testAmazonFlow(String productName,String Quantity) {

        test = extent.createTest("Amazon Flow Test");

        test.info("Starting Test Execution");
        test.info("Product Name: " + productName);
        test.info("Quantity: " + Quantity);

        homepage home = new homepage(getDriver());
        home.hover(productName);
        test.pass("Hovered over search field");

        searchpage results = new searchpage(getDriver());
        results.suggestion();
        test.pass("Suggestion selected");

        results.clickProduct("L’Oreal Paris Infallible Matte Resistance Liquid Lipstick- 635 Worth It Medium, 5ml");
        test.pass("Product clicked");

        for (String window : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(window);
        }

        productPage product = new productPage(getDriver());
        product.selectQuantity(Quantity);
        test.pass("Quantity selected");

        product.addToCart();
        test.pass("Added to cart");

        String actualTitle = getDriver().getTitle();

        if(actualTitle.contains("Shopping Cart")) {
            test.pass("Cart page opened successfully");
        } else {
            test.fail("Cart page not opened");
        }

        Assert.assertTrue(actualTitle.contains("Shopping Cart"));
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}