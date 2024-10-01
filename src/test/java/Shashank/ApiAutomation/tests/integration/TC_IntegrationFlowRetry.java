package Shashank.ApiAutomation.tests.integration;

import Shashank.ApiAutomation.base.BaseTest;
import Shashank.ApiAutomation.endpoints.APIConstants;
import Shashank.ApiAutomation.listeners.RetryAnalyzer;
import Shashank.ApiAutomation.pojos.Booking;
import Shashank.ApiAutomation.pojos.BookingResponse;
import Shashank.ApiAutomation.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test(retryAnalyzer = RetryAnalyzer.class)
public class TC_IntegrationFlowRetry extends BaseTest {
    // Create A Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking

// ITestContext is used to share the data in between the test function within the same class while integration testing

    @Test(groups = "integration", priority = 1)
    @Owner("Shashank")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token", getToken());

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();

        // Validatable Assertion
        validatableResponse.statusCode(200);
//        validatableResponse.body("booking.firstname", Matchers.equalTo("Pramod"));

        // DeSer
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBookingId()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstName()).isNotEmpty().isNotBlank()
                .isEqualTo(PropertyReader.readKey("booking.post.firstname"));


        iTestContext.setAttribute("bookingid", bookingResponse.getBookingId());

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Shashank")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingById(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        // GET Req
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);

        response = RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        // AssertJ
        assertThat(booking.getFirstName()).isNotEmpty().isNotBlank()
                .isEqualTo(PropertyReader.readKey("booking.get.firstname"));
    }

    @Test(groups = "integration", priority = 3)
    @Owner("Shashank")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingById(ITestContext iTestContext){
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println("Token is " + token + " and bookingid is " + bookingid);

        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured.given(requestSpecification)
                .cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstName()).isNotBlank().isNotEmpty();
        assertThat(booking.getFirstName()).isEqualTo(PropertyReader.readKey("booking.put.firstname"));
        assertThat(booking.getLastName()).isEqualTo(PropertyReader.readKey("booking.put.lastname"));

    }

    @Test(groups = "integration", priority = 4)
    @Owner("Shashank")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){

        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathDELETE);

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(200);        // To Fail

    }


}
