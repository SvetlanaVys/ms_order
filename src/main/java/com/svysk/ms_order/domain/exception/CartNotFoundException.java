package com.svysk.ms_order.domain.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(Long productId) {
        super( String.format("Cart with ID %d does not exist.", productId) );
    }
}
