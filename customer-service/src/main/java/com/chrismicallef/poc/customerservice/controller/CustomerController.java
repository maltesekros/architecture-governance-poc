package com.chrismicallef.poc.customerservice.controller;

import com.chrismicallef.poc.customerservice.api.CustomerApi;
import com.chrismicallef.poc.customerservice.model.Customer;
import com.chrismicallef.poc.customerservice.service.CustomerService;
import com.chrismicallef.poc.customerservice.to.CustomerRentRequest;
import com.chrismicallef.poc.customerservice.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customers")
public class CustomerController implements CustomerApi {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    BookService bookService;

    @Autowired
    CustomerService customerService;

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    public Customer getCustomerById(@PathVariable long id) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "customer not found");
        }
        else {
            return customer;
        }

    }

    @RequestMapping(value = "/customerRentBook", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public void rentBook(@RequestBody CustomerRentRequest customerRentRequest) {
        LOG.info(String.format("CustomerRentRequest received [%s]", customerRentRequest));
        bookService.rentBookForCustomer(customerRentRequest.getBookId(), customerRentRequest.getNoOfDays());
    }

}
