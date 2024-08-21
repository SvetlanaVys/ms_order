package com.svysk.ms_order.adapters.storage.mapper;

import com.svysk.ms_order.adapters.storage.entity.ProductEntity;
import com.svysk.ms_order.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    Product toProduct(ProductEntity productEntity);

    ProductEntity toProductEntity(Product product);
}
