package baseclass;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AnnotationDemo {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("1. BeforeSuite - Suite start");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("2. BeforeTest - Test block start");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("3. BeforeClass - Class start");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("4. BeforeMethod - Method start");
    }

    @Test
    public void testCase1() {
        System.out.println("5. Test - testCase1 running");
    }

    @Test
    public void testCase2() {
        System.out.println("5. Test - testCase2 running");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("6. AfterMethod - Method end");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("7. AfterClass - Class end");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("8. AfterTest - Test block end");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("9. AfterSuite - Suite end");
    }
} 