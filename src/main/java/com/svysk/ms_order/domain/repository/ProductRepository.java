package com.svysk.ms_order.domain.repository;

import com.svysk.ms_order.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(Long id);


}
