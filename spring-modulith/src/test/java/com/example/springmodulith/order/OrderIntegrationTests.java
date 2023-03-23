package com.example.springmodulith.order;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.PublishedEvents;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApplicationModuleTest
class OrderIntegrationTests {

    private final OrderManagement orders;

    OrderIntegrationTests(OrderManagement orders) {
        this.orders = orders;
    }

    @Test
    void publishesOrderCompletion(PublishedEvents events) {
        var reference = Order.of(2L, new ArrayList<>());
        orders.complete(reference);

        var matchingMapped = events.ofType(OrderCompleted.class)
                .matching(OrderCompleted::getOrderId, reference.getOrderId());

        assertThat(matchingMapped).hasSize(1);
    }
}