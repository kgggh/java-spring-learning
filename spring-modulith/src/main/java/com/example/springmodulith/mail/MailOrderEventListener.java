package com.example.springmodulith.mail;

import com.example.springmodulith.order.OrderCompleted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailOrderEventListener {

    @ApplicationModuleListener
    public void on(OrderCompleted orderCompleted) {
        log.info("Order Completed [{}]", orderCompleted);
    }
}
