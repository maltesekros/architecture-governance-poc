package com.chrismicallef.poc.customerservice.to;

public class BookBookingRequest {
    private final long bookId;
    private final long noOfDays;

    public BookBookingRequest(final long bookId, final long noOfDays) {
        this.bookId = bookId;
        this.noOfDays = noOfDays;
    }

    public long getBookId() {
        return bookId;
    }

    public long getNoOfDays() {
        return noOfDays;
    }

    @Override
    public String toString() {
        return String.format("BookBookingRequest{bookId=%d, noOfDays=%d}", bookId, noOfDays);
    }
}
