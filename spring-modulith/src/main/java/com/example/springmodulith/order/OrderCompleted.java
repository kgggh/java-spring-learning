package com.example.springmodulith.order;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@ToString
@Getter
public class OrderCompleted {
    private Long orderId;

    private OrderCompleted(Long orderId) {
        this.orderId = orderId;
    }

    public static OrderCompleted of(Long orderId) {
        Assert.notNull(orderId);
        return new OrderCompleted(orderId);
    }
}
