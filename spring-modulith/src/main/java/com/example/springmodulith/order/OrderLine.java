package com.example.springmodulith.order;

public class OrderLine {
    private Long orderLindId;
    private Long productId;
    private int quantity;

    private OrderLine(Long orderLindId, Long productId, int quantity) {
        this.orderLindId = orderLindId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
