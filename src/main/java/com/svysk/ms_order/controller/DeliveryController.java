package com.svysk.ms_order.controller;

import com.svysk.openapi.client.DeliveriesApi;
import com.svysk.openapi.dto.DeliveryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @GetMapping()
    public ResponseEntity<List<DeliveryDto>> getDeliveries() {
        return ResponseEntity.ok(deliveriesApi.getDeliveries());
    }
}
