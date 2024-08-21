package com.svysk.ms_order.controller.mapper;

import com.svysk.ms_order.domain.Cart;
import com.svysk.openapi.dto.CartDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartDtoMapper {
    CartDto toCartDto(Cart cart);
}
