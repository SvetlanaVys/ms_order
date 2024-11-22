package com.svysk.ms_order.controller;

import com.svysk.ms_order.controller.mapper.CartDtoMapper;
import com.svysk.ms_order.controller.mapper.ProductDtoMapper;
import com.svysk.ms_order.domain.service.CartService;
import com.svysk.openapi.api.CartsApi;
import com.svysk.openapi.dto.CartDto;
import com.svysk.openapi.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CartController implements CartsApi {

    private final CartService service;

    private final ProductDtoMapper productDtoMapper;
    private final CartDtoMapper cartDtoMapper;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return CartsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> addProductToCart(String userId, ProductDto productDto) {
        service.addProduct(userId, productDtoMapper.toProduct(productDto));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CartDto>> getCarts() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(cartDtoMapper::toCartDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<CartDto>> getCartsByUser(String userId) {
        return ResponseEntity.ok(service.findCartsByUser(userId).stream()
                .map(cartDtoMapper::toCartDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Void> sendCart(String userId) {
        service.sendCart(service.findCartsByUser(userId));
        return ResponseEntity.ok().build();
    }
}
