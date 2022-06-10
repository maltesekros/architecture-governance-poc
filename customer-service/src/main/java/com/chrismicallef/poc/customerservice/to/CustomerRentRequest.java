package com.chrismicallef.poc.customerservice.to;

public class CustomerRentRequest {

    private final long customerId;
    private final long bookId;
    private final long noOfDays;

    public CustomerRentRequest(final long customerId, final long bookId, final long noOfDays) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.noOfDays = noOfDays;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getBookId() {
        return bookId;
    }

    public long getNoOfDays() {
        return noOfDays;
    }

    @Override
    public String toString() {
        return String.format("CustomerRentRequest{customerId=%d, bookId=%d, noOfDays=%d}", customerId, bookId, noOfDays);
    }
}
