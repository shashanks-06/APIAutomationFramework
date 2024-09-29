package Shashank.ApiAutomation.pojos;

public class BookingResponse {
    private String bookingid;
    private Booking booking;

    public String getBookingId() {
        return bookingid;
    }

    public void setBookingId(String bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
