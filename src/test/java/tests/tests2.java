package tests;

import baseclass.Baseclass;
import pages.homepage;
import pages.searchpage;
import pages.productPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class tests2 extends Baseclass {

    @Test
    public void testAmazonFlow() {
        // Home page
        homepage home = new homepage(Baseclass.getDriver());
        home.hover("lipsticks");

        // Search results
        searchpage results = new searchpage(getDriver());
        results.suggestion();
        results.clickProduct("L’Oreal Paris Infallible Matte Resistance Liquid Lipstick- 635 Worth It Medium, 5ml");

        // Switch to product tab
        for (String window : getDriver().getWindowHandles()) {
        	getDriver().switchTo().window(window);
        }

        // Product page
        productPage product = new productPage(getDriver());
        product.selectQuantity("2");
        product.addToCart();

        // Cart page
       
    }
}