package com.example.springmodulith.mail;

import com.example.springmodulith.customer.CustomerRegistered;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailCustomerEventListener {

    @ApplicationModuleListener
    public void on(CustomerRegistered customerRegistered) {
        log.info("Customer Registered [{}]", customerRegistered);
    }
}
