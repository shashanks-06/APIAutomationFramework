package Shashank.ApiAutomation.tests.ddt.vwo;

import Shashank.ApiAutomation.utils.vwo.UtilsExcel;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class VwoLoginApiDdt {
    RequestSpecification reqSpec;
    ValidatableResponse valRes;
    Integer ID;
    Response res;

    @Test(dataProvider = "getData", dataProviderClass = UtilsExcel.class)
    public void testVwoLoginApi(String email, String password){
        System.out.println("--- Login API Testing---");
        System.out.println(email);
        System.out.println(password);
    }
}
