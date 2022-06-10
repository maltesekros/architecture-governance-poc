package com.chrismicallef.poc.customerservice.api;

import com.chrismicallef.poc.customerservice.model.Customer;
import com.chrismicallef.poc.customerservice.to.CustomerRentRequest;

public interface CustomerApi {

    /**
     * Return Customer By Id
     * @param id Id for Customer
     * @return Customer Entity
     */
    Customer getCustomerById(long id);

    /**
     * Rent a given book for a given Customer
     * @param customerRentRequest Customer Book Rent Request
     */
    void rentBook(CustomerRentRequest customerRentRequest);
}
