package com.example.springmodulith.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class Customer {
    private Long customerId;
    private String name;
}
