package com.example.springmodulith.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderManagement {
    private final ApplicationEventPublisher events;

    @Transactional
    public void complete(Order order) {
        log.info("new order");
        events.publishEvent(OrderCompleted.of(order.getOrderId()));
    }
}
