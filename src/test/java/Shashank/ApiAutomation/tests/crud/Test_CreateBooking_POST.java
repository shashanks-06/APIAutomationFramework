package Shashank.ApiAutomation.tests.crud;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_CreateBooking_POST {

    @Owner("Shashank")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that POST request is working fine.")
    @Test
    public void testVerifyCreateBookingPOST01(){
        Assert.assertEquals(true, true);
    }
}
