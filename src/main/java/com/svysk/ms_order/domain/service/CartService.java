package com.svysk.ms_order.domain.service;


import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.Product;

import java.util.List;

public interface CartService {
    Cart addProduct(String userId, Product product);

    List<Cart> findAll();
}
