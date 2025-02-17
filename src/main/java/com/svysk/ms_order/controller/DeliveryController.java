package com.svysk.ms_order.controller;

import com.svysk.ms_order.controller.exception_handler.ErrorResponse;
import com.svysk.openapi.client.DeliveriesApi;
import com.svysk.openapi.dto.DeliveryDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Deliveries")
@RestController
@RequiredArgsConstructor
@RequestMapping("deliveries")
public class DeliveryController {

    private final DeliveriesApi deliveriesApi;
    private static final String DELIVERY_SERVICE = "deliveryService";

    @GetMapping
    @CircuitBreaker(name = DELIVERY_SERVICE, fallbackMethod = "deliveryFallback")
    @Retry(name = DELIVERY_SERVICE)
    public ResponseEntity<List<DeliveryDto>> getDeliveries() {

        return ResponseEntity.ok(deliveriesApi.getDeliveries());
    }

    public ResponseEntity<ErrorResponse> deliveryFallback(Throwable ex) {

        String errorMessage =
                "Delivery service is currently unavailable. Please try again later.";

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorResponse(errorMessage));
    }
}
