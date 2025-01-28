package com.svysk.ms_order.domain.service;


import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.Product;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart addProduct(String userId, Product product);

    Optional<Cart> findCartsByUser(String userId);

    List<Cart> findAll();
}
