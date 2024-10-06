package Shashank.ApiAutomation.tests.integration;

import Shashank.ApiAutomation.base.BaseTest;
import Shashank.ApiAutomation.endpoints.APIConstants;
import Shashank.ApiAutomation.pojos.Booking;
import Shashank.ApiAutomation.pojos.BookingResponse;
import Shashank.ApiAutomation.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TCIntegration_Assignment extends BaseTest {
    // Create A Booking and Create a Token
    // Delete the Booking
    // Get booking Verify (404)


    @Test(groups = "integration", priority = 1)
    @Owner("Shashank")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token", getToken());

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification).given()
                        .body(payloadManager.createPayloadBookingAsString())
                        .when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

//        AssertJ
        assertThat(bookingResponse.getBookingId()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstName()).isNotBlank().isNotEmpty()
                .isEqualTo(PropertyReader.readKey("booking.post.firstname"));

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingId());
    }



    @Test(groups = "integration", priority = 2)
    @Owner("Shashank")
    @Description("TC#INT1 - Step 2. Make Patch Update to the Booking by ID")
    public void testPatchBookingById(ITestContext iTestContext){
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" +bookingid;

        requestSpecification.basePath(basePathPATCH);

        response = RestAssured.given(requestSpecification)
                .cookie("token", token)
                .body(payloadManager.partialUpdateBookingById())
                .when().patch();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstName()).isNotBlank().isNotEmpty()
                .isEqualTo(PropertyReader.readKey("booking.patch.firstname"));

    }
    @Test(groups = "integration", priority = 3)
    @Owner("Shashank")
    @Description("TC#INT1 - Step 3. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL +"/" + bookingid;

        requestSpecification.basePath(basePathGET);

        response = RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstName()).isNotEmpty().isNotBlank();
        assertThat(booking.getFirstName()).isEqualTo(PropertyReader.readKey("booking.patch.firstname"));

    }

    @Test(groups = "integration", priority = 4)
    @Owner("Shashank")
    @Description("TC#INT1 - Step 3. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathDELETE);

        response = RestAssured.given(requestSpecification)
                .cookie("token", token)
                .when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);


    }


}