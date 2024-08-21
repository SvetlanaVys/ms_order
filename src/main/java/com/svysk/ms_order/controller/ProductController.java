package com.svysk.ms_order.controller;

import com.svysk.ms_order.controller.mapper.ProductDtoMapper;
import com.svysk.ms_order.domain.Product;
import com.svysk.ms_order.domain.service.ProductService;
import com.svysk.openapi.api.ProductsApi;
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
public class ProductController implements ProductsApi {

    private final ProductService service;

    private final ProductDtoMapper productDtoMapper;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ProductsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long id) {

        Optional<Product> product = service.findById(id);

        return product.map(value -> ResponseEntity.ok(productDtoMapper.toProductDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Override
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(productDtoMapper::toProductDto)
                .collect(Collectors.toList()));
    }
}
