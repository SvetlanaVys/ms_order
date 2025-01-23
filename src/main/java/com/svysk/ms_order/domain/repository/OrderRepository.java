package com.svysk.ms_order.domain.repository;

import com.svysk.ms_order.domain.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAll();

    Order save(Order cart);
}
