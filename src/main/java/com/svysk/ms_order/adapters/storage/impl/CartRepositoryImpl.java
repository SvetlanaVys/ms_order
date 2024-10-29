package com.svysk.ms_order.adapters.storage.impl;

import com.svysk.ms_order.adapters.storage.CartEntityDao;
import com.svysk.ms_order.adapters.storage.mapper.CartEntityMapper;
import com.svysk.ms_order.adapters.storage.mapper.ProductEntityMapper;
import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.Product;
import com.svysk.ms_order.domain.repository.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private final CartEntityDao cartEntityDao;

    private final CartEntityMapper cartEntityMapper;
    private final ProductEntityMapper productEntityMapper;

    public CartRepositoryImpl(CartEntityDao cartEntityDao, CartEntityMapper cartEntityMapper, ProductEntityMapper productEntityMapper) {
        this.cartEntityDao = cartEntityDao;
        this.cartEntityMapper = cartEntityMapper;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public List<Cart> findCartByUserId(String userId) {
        return cartEntityDao.findCartByUserId(userId)
                .stream()
                .map(cartEntityMapper::toCart)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cart> findCartByUserIdAndProduct(String userId, Product product) {
        return Optional.of(
                cartEntityMapper.toCart( cartEntityDao.findCartByUserIdAndProduct(userId, productEntityMapper.toProductEntity(product)) )
        );
    }

    @Override
    public Optional<Cart> save(Cart cart) {
        return Optional.of(
                cartEntityMapper.toCart( cartEntityDao.save(cartEntityMapper.toCartEntity(cart)) )
        );
    }

    @Override
    public List<Cart> findAll() {
        return cartEntityDao.findAll()
                .stream()
                .map(cartEntityMapper::toCart)
                .collect(Collectors.toList());
    }
}
