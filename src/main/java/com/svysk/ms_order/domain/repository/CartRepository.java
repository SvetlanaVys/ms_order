package com.svysk.ms_order.domain.repository;

import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.Product;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    List<Cart> findCartByUserId(String userId);

    Optional<Cart> findCartByUserIdAndProduct(String userId, Product product);

    Optional<Cart> save(Cart cart);

    List<Cart> findAll();
}
