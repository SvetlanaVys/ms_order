package com.svysk.ms_order.controller.exception_handler;

import com.svysk.ms_order.controller.OrderController;
import com.svysk.ms_order.domain.exception.CartNotFoundException;
import com.svysk.ms_order.domain.exception.OrderSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = OrderController.class)
public class OrderExceptionHandler {

    @ExceptionHandler(OrderSaveException.class)
    public ResponseEntity<ErrorResponse> handleOrderSaveException(OrderSaveException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(CartNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
