package com.svysk.ms_order.controller;

import com.svysk.ms_order.controller.mapper.CartDtoMapper;
import com.svysk.ms_order.controller.mapper.OrderDtoMapper;
import com.svysk.ms_order.domain.service.OrderService;
import com.svysk.openapi.api.OrdersApi;
import com.svysk.openapi.dto.CartDto;
import com.svysk.openapi.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrdersApi {

    private final OrderService service;
    private final OrderDtoMapper orderDtoMapper;
    private final CartDtoMapper cartDtoMapper;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return OrdersApi.super.getRequest();
    }

    @Override
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(orderDtoMapper::toOrderDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<OrderDto> placeOrder(CartDto cartDto) {

        return ResponseEntity.ok(
                orderDtoMapper.toOrderDto(service.placeOrder(cartDtoMapper.toCart(cartDto)))
        );
    }
}
