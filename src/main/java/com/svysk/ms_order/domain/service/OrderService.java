package com.svysk.ms_order.domain.service;

import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order placeOrder(final Cart cart);
}
