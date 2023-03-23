package com.example.springmodulith.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@RequiredArgsConstructor
public class Order {
    private Long orderId;
    private List<OrderLine> orderLines;

    private Order(Long orderId, List<OrderLine> orderLines) {
        this.orderId = orderId;
        this.orderLines = orderLines;
    }

    public static Order of(Long orderId, List<OrderLine> orderLines) {
        return new Order(orderId, orderLines);
    }
}
