package com.svysk.ms_order.domain.exception;


public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long productId) {
        super( String.format("Product with ID %d does not exist.", productId) );
    }
}
