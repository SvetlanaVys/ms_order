package com.svysk.ms_order.adapters.storage.mapper;

import com.svysk.ms_order.adapters.storage.entity.OrderEntity;
import com.svysk.ms_order.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {

    OrderEntity toOrderEntity(Order order);

    Order toOrder(OrderEntity orderEntity);
}
