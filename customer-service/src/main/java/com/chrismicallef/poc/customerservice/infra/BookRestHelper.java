package com.chrismicallef.poc.customerservice.infra;

import com.chrismicallef.poc.customerservice.to.BookBookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class BookRestHelper {

    private static final Logger LOG = LoggerFactory.getLogger(BookRestHelper.class);

    // Use RestTemplate provided from parent
    @Autowired
    RestTemplate restTemplate;

    @Value("${bookService.baseUrl}")
    private String bookServiceBaseUrl;

    public static final String RENT_BOOK = "rentBook";

    public void rentBook(long bookId, long noOfDays) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        BookBookingRequest bookBookingRequest = new BookBookingRequest(bookId, noOfDays);
        HttpEntity<BookBookingRequest> request = new HttpEntity<>(bookBookingRequest, headers);
        final String requestUrl = this.bookServiceBaseUrl + RENT_BOOK;
        URI uri = new URI(requestUrl);
        ResponseEntity<Void> result = restTemplate.postForEntity(uri, request, Void.class);
        LOG.debug(String.format("Result [code: %d]", result.getStatusCode().value()));
    }
}
