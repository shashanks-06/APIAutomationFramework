package Shashank.ApiAutomation.base;

import Shashank.ApiAutomation.asserts.AssertsActions;
import Shashank.ApiAutomation.endpoints.APIConstants;
import Shashank.ApiAutomation.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    // Common to All to TestCase
    //  Base Test Father -> Testcase - Son - Single Inheritance
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public AssertsActions assertsActions;
    public JsonPath jsonPath;
    public PayloadManager payloadManager;

    @BeforeTest
    public void setUp(){
        payloadManager = new PayloadManager();
        assertsActions = new AssertsActions();
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();

//                requestSpecification = RestAssured.
//                given()
//                .baseUri(APIConstants.BASE_URL)
//                .contentType(ContentType.JSON)
//                .log().all();

    }

    public String getToken(){
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL)
                .contentType(ContentType.JSON);

        // Setting the payload
        String payload = payloadManager.setAuthPayload();

        // Get the Token
        response = requestSpecification.body(payload)
                .when().post();

        // Token Extraction
        String token = payloadManager.getTokenFromJSON(response.asString());

        return token;
    }
}
