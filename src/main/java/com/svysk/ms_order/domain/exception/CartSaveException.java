package com.svysk.ms_order.domain.exception;

public class CartSaveException extends RuntimeException {

    public CartSaveException() {
        super("Failed to save the cart.");
    }

    public CartSaveException(String message) {
        super(message);
    }
}

