package com.svysk.ms_order.application.mapper;

import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.CartProduct;
import com.svysk.ms_order.domain.Order;
import com.svysk.ms_order.domain.OrderedProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderCartMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "orderedProducts", source = "cartProducts")
    Order mapCartToOrder(Cart cart);

    @Mapping(target = "originalProductId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productPrice", source = "product.price")
    @Mapping(target = "quantity", source = "productQuantity")
    OrderedProduct mapCartProductToOrderedProduct(CartProduct cartProduct);
}
