package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import baseclass.Baseclass;

public class ScreenshotUtils {
    public static String capture(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) Baseclass.getDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            return path; // ✅ always return valid path
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }
}