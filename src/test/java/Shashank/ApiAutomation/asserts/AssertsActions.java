package Shashank.ApiAutomation.asserts;

import io.restassured.response.Response;
import org.testng.Assert;

public class AssertsActions {
    // Common Assertions - Which can be reused.

    public void verifyResponseBody(String actual, String expected, String description){
        Assert.assertEquals(actual, expected, description);
    }
    public void verifyResponseBody(int actual, int expected, String description){
        Assert.assertEquals(actual, expected, description);
    }

    public void verifyStatusCode(Response response, Integer expected){
        Assert.assertEquals(response.getStatusCode(), expected);
    }
}
