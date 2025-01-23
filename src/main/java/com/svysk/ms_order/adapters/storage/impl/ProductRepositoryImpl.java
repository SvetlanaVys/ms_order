package com.svysk.ms_order.adapters.storage.impl;

import com.svysk.ms_order.adapters.storage.ProductEntityDao;
import com.svysk.ms_order.adapters.storage.mapper.ProductEntityMapper;
import com.svysk.ms_order.domain.Product;
import com.svysk.ms_order.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductEntityDao productEntityDao;

    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryImpl(ProductEntityDao productEntityDao, ProductEntityMapper productEntityMapper) {
        this.productEntityDao = productEntityDao;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public List<Product> findAll() {
        return productEntityDao.findAll()
                .stream()
                .map(productEntityMapper::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productEntityDao.findById(id)
                .map(productEntityMapper::toProduct);
    }

    @Override
    public Set<Product> findByIds(Set<Long> ids) {
        return productEntityDao.findByIds(ids).stream()
                .map(productEntityMapper::toProduct)
                .collect(Collectors.toSet());
    }
}
