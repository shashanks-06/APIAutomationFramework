package Shashank.ApiAutomation.modules;

import Shashank.ApiAutomation.pojos.Booking;
import Shashank.ApiAutomation.pojos.BookingDates;
import Shashank.ApiAutomation.pojos.BookingResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

public class PayloadManager {
//    It will perform Serialization and Deserialization
    // Converting the JAVA object to the String
    // {}
    Gson gson = new Gson();

    public String createPayloadBookingAsString(){
        Booking booking = new Booking();
        booking.setFirstName("James");
        booking.setLastName("Brown");
        booking.setTotalPrice(111);
        booking.setDepositPaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn("2024-02-01");
        bookingDates.setCheckOut("2024-02-31");
        booking.setBookingDates(bookingDates);

        booking.setAdditionalNeeds("Lunch");
        System.out.println(booking);

        // Java Object -> JSON String (byteStream) - Serlization
        String jsonStringPayload = gson.toJson(booking);
        System.out.println(jsonStringPayload);
        return jsonStringPayload;
    }

    public String createPayloadBookingAsStringFaker(){
        Faker faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstName(faker.name().firstName());
        booking.setLastName(faker.name().lastName());
        booking.setTotalPrice(faker.random().nextInt(1000));
        booking.setDepositPaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn("2024-02-01");
        bookingDates.setCheckOut("2024-02-31");
        booking.setBookingDates(bookingDates);

        booking.setAdditionalNeeds("Lunch");
        System.out.println(booking);

        // Java Object -> JSON String (byteStream) - Serialization
        String jsonStringPayload = gson.toJson(booking);
        System.out.println(jsonStringPayload);
        return jsonStringPayload;
    }

    public BookingResponse bookingResponseJava(String responseString){
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
}
