package Shashank.ApiAutomation.modules;

import Shashank.ApiAutomation.pojos.*;
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

    // get Token
    public String setAuthPayload(){
        // Auth Object -> json String
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to this -> " + jsonPayloadString);
        return jsonPayloadString;
    }

    public String getTokenFromJSON(String responseString){
        TokenResponse tokenResponse = gson.fromJson(responseString, TokenResponse.class);
        return tokenResponse.getToken();
    }

//     get Booking ID
    public Booking getResponseFromJSON(String responseString){
        // Response ( JSON) ->  Object TokenResponse
        // Deserialization
        Booking booking = gson.fromJson(responseString, Booking.class);
        return  booking;
    }

    public String fullUpdatePayloadAsString(){
        Booking booking = new Booking();
        booking.setFirstName("Shashank");
        booking.setLastName("Surjekar");
        booking.setTotalPrice(611);
        booking.setDepositPaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn("2024-02-01");
        bookingDates.setCheckOut("2024-02-31");
        booking.setBookingDates(bookingDates);

        booking.setAdditionalNeeds("Dinner");
        System.out.println(booking);

        // Java Object -> JSON String (byteStream) - Serialization
        return gson.toJson(booking);
    }

    public String partialUpdateBookingById(){
        Booking booking = new Booking();
        booking.setFirstName("Shashank");

        System.out.println(booking);

        return gson.toJson(booking);
    }
}
