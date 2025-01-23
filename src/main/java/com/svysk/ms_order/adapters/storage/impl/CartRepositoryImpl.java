package com.svysk.ms_order.adapters.storage.impl;

import com.svysk.ms_order.adapters.storage.CartEntityDao;
import com.svysk.ms_order.adapters.storage.entity.CartEntity;
import com.svysk.ms_order.adapters.storage.mapper.CartEntityMapper;
import com.svysk.ms_order.adapters.storage.mapper.ProductEntityMapper;
import com.svysk.ms_order.domain.Cart;
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
    public Optional<Cart> findByUser(String userId) {

        Optional<CartEntity> cartEntity = cartEntityDao.findByUser(userId);

        return cartEntity.map(cartEntityMapper::toCart);

    }

    @Override
    public Optional<Cart> save(Cart cart) {

        CartEntity cartEntity = cartEntityMapper.toCartEntity(cart);
        cartEntity.getCartProducts().forEach(cp -> cp.setCart(cartEntity));

        return Optional.of(
                cartEntityDao.save(cartEntity)
        ).map(cartEntityMapper::toCart);
    }

    @Override
    public List<Cart> findAll() {
        return cartEntityDao.findAll()
                .stream()
                .map(cartEntityMapper::toCart)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Cart cart) {
        cartEntityDao.delete(cartEntityMapper.toCartEntity(cart));
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartEntityDao.findById(id).map(cartEntityMapper::toCart);
    }
}
