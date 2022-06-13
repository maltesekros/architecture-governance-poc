package com.chrismicallef.poc.customerservice.service;

import com.chrismicallef.poc.customerservice.model.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Random;

@Service
public class CustomerService {

    private HashMap<Long, Customer> customers;
    private String[] firstNames = {"Chris", "Melanie", "Luke", "Ian", "Gilbert", "Nicholas", "Josanne", "Jake", "Matthias", "Arthur"};
    private String[] lastNames = {"Micallef", "Borg", "Galea", "Brown", "Zammit", "Cassar", "Zanetti", "Rossi", "Azzopardi", "Butler"};

    @PostConstruct
    private void init() {
        Random randomiser = new Random(1000);
        customers = new HashMap<>();
        for (long i = 1; i <= 10; i++) {
            String randomFirstName = firstNames[Math.abs(randomiser.nextInt()) % firstNames.length];
            String randomLastName = lastNames[Math.abs(randomiser.nextInt()) % lastNames.length];
            customers.put(i, new Customer(i, randomFirstName + " " + randomLastName, true));
        }
    }

    public Customer getCustomer(long id) {
        return customers.get(id);
    }
}
