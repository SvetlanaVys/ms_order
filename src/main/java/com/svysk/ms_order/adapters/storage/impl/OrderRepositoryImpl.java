package com.svysk.ms_order.adapters.storage.impl;


import com.svysk.ms_order.adapters.storage.OrderEntityDao;
import com.svysk.ms_order.adapters.storage.entity.OrderEntity;
import com.svysk.ms_order.adapters.storage.mapper.OrderEntityMapper;
import com.svysk.ms_order.domain.Order;
import com.svysk.ms_order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderEntityDao orderEntityDao;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public List<Order> findAll() {
        return orderEntityDao.findAll()
                .stream()
                .map(orderEntityMapper::toOrder)
                .toList();
    }

    @Override
    public Order save(Order cart) {
        OrderEntity orderEntity = orderEntityMapper.toOrderEntity(cart);
        orderEntity.getOrderedProducts().forEach(op -> op.setOrder(orderEntity));

        return orderEntityMapper.toOrder( orderEntityDao.save(orderEntity) );
    }
}
