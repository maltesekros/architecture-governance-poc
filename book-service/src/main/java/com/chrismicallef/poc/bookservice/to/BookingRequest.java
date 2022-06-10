package com.chrismicallef.poc.bookservice.to;

public final class BookingRequest {

    private final long bookId;
    private final long noOfDays;

    public BookingRequest(final long bookId, final long noOfDays) {
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
        return String.format("BookingRequest{bookId=%d, noOfDays=%d}", bookId, noOfDays);
    }
}
