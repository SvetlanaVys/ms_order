package com.svysk.ms_order.domain.repository;

import com.svysk.ms_order.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(String userId);

    Optional<Cart> save(Cart cart);

    List<Cart> findAll();

    void delete(Cart cart);

    Optional<Cart> findById(Long id);

}
