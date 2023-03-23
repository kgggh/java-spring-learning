package com.example.springmodulith.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerManagement {
    private final ApplicationEventPublisher events;

    @Transactional
    public void signUp(Customer customer) {
        log.info("customer={}",customer);
        events.publishEvent(CustomerRegistered.of(customer.getCustomerId()));
    }
}
