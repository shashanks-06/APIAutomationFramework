package Shashank.ApiAutomation.tests.crud;

import Shashank.ApiAutomation.base.BaseTest;
import Shashank.ApiAutomation.endpoints.APIConstants;
import Shashank.ApiAutomation.pojos.BookingResponse;
import Shashank.ApiAutomation.utils.PropertyReader;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class testCreateBookingTCPOST extends BaseTest {

    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_RBT-4")
    @TmsLink("RBT-4")
    @Owner("Shashank")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that POST request is working fine.")
    @Test
    public void testVerifyCreateBookingPOST01(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                        .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(
                PropertyReader.readKey("booking.post.statuscode.success")
        ));

        //Default Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo(
                PropertyReader.readKey("booking.post.firstname")
        ));


//        AssertJ
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertThat(bookingResponse.getBookingId()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstName()).isNotEmpty().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstName()).isEqualTo(
                PropertyReader.readKey("booking.post.firstname")
        );

//        TestNG Assertions
        assertsActions.verifyStatusCode(response, 200);
    }
}
