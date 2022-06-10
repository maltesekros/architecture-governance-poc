package com.chrismicallef.poc.bookservice.api;

import com.chrismicallef.poc.bookservice.to.BookingRequest;


public interface BookApi {

    /**
     * Rent a book.
     *
     * @param bookingRequest Booking Request Details.
     */
    void rentBook(BookingRequest bookingRequest);
}
