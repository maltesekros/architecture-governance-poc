package com.chrismicallef.poc.customerservice.service;

import com.chrismicallef.poc.customerservice.infra.BookRestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookRestHelper bookRestHelper;

    public void rentBookForCustomer(long bookId, long noOfDays) {
        try {
            bookRestHelper.rentBook(bookId, noOfDays);
        } catch (URISyntaxException e) {
            LOG.error("Error at Infra level", e);
            throw new RuntimeException("Error at Infra level", e);
        }
    }
}
