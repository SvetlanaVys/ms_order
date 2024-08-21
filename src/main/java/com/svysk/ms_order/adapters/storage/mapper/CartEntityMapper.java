package com.svysk.ms_order.adapters.storage.mapper;

import com.svysk.ms_order.adapters.storage.entity.CartEntity;
import com.svysk.ms_order.domain.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {

    CartEntity toCartEntity(Cart cart);

    Cart toCart(CartEntity cartEntity);
}
