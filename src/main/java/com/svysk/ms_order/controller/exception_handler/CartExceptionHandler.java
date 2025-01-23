package com.svysk.ms_order.controller.exception_handler;

import com.svysk.ms_order.controller.CartController;
import com.svysk.ms_order.domain.exception.CartSaveException;
import com.svysk.ms_order.domain.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice(basePackageClasses = CartController.class)
public class CartExceptionHandler {

    @ExceptionHandler(CartSaveException.class)
    public ResponseEntity<ErrorResponse> handleCartSaveException(CartSaveException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
