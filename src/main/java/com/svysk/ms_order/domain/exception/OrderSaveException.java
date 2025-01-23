package com.svysk.ms_order.domain.exception;

public class OrderSaveException extends RuntimeException {

    public OrderSaveException() {
        super("Failed to save the order.");
    }

    public OrderSaveException(String message) {
        super(message);
    }
}
