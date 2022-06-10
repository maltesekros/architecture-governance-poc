package com.chrismicallef.poc.customerservice.model;

public class Customer {

    private long id;
    private String name;
    private boolean canBorrowBook;

    public Customer(final long id, final String name, final boolean canBorrowBook) {
        this.id = id;
        this.name = name;
        this.canBorrowBook = canBorrowBook;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCanBorrowBook() {
        return canBorrowBook;
    }
}
