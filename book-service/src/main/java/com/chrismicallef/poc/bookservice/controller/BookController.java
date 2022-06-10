package com.chrismicallef.poc.bookservice.controller;

import com.chrismicallef.poc.bookservice.api.BookApi;
import com.chrismicallef.poc.bookservice.to.BookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController implements BookApi {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @RequestMapping(value = "/rentBook", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public void rentBook(@RequestBody BookingRequest bookingRequest) {
        LOG.info(String.format("Booking request received [%s]", bookingRequest));
    }
}
