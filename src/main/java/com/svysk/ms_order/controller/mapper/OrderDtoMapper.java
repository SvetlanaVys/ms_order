package com.svysk.ms_order.controller.mapper;

import com.svysk.ms_order.domain.Order;
import com.svysk.openapi.dto.OrderDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

    // Define a custom method to convert LocalDateTime to OffsetDateTime
    default OffsetDateTime map(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atOffset(ZoneOffset.UTC);
    }

    OrderDto toOrderDto(Order order);

    // Define a custom method to convert OffsetDateTime to LocalDateTime
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime value) {
        if (value == null) {
            return null;
        }
        return value.toLocalDateTime();  // Convert OffsetDateTime to LocalDateTime
    }

    Order toOrder(OrderDto cart);
}
