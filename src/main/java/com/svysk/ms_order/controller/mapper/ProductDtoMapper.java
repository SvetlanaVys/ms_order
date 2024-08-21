package com.svysk.ms_order.controller.mapper;

import com.svysk.ms_order.domain.Product;
import com.svysk.openapi.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProductDto toProductDto(Product product);

    Product toProduct(ProductDto productDto);
}
