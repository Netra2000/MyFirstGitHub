package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import baseclass.Baseclass;
import utils.utils;

public class homepage extends Baseclass {

	WebDriver driver;
	utils u;
	
	By action= By.xpath("//div[@class='nav-line-1-container']//following-sibling::span");
	By order=By.xpath("//a[@id='nav_prefetch_yourorders']/span");
	By searchbox=By.id("twotabsearchtextbox");
	
	public homepage(WebDriver driver) {
	this.driver=driver;
	this.u=new utils(getDriver());
	}
	
	
	public void hover(String str) {
		u.hover(action);
		u.click(order);
		driver.navigate().back();
		u.send(searchbox, str);
		  

	}
}
