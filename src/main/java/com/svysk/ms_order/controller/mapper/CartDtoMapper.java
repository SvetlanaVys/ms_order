package com.svysk.ms_order.controller.mapper;

import com.svysk.ms_order.domain.Cart;
import com.svysk.openapi.dto.CartDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface CartDtoMapper {

    // Define a custom method to convert LocalDateTime to OffsetDateTime
    default OffsetDateTime map(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atOffset(ZoneOffset.UTC);
    }

    CartDto toCartDto(Cart cart);
}
