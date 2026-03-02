package utils;


import io.restassured.response.Response;
import org.testng.Assert;

public class AssertionUtil {

    public static void verifyStatusCode(Response response, int expected) {
        Assert.assertEquals(response.getStatusCode(), expected);
    }

    public static void verifyBodyValue(Response response, String key, Object expected) {
        Assert.assertEquals(response.jsonPath().get(key), expected);
    }
}