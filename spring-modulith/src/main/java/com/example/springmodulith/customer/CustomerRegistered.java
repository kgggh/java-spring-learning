package com.example.springmodulith.customer;


import lombok.ToString;

@ToString
public class CustomerRegistered {
    private Long customerId;

    private CustomerRegistered(Long customerId) {
        this.customerId = customerId;
    }

    public static CustomerRegistered of(Long customerId) {
        return new CustomerRegistered(customerId);
    }
}
