package com.svysk.ms_order.application;

import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.Product;
import com.svysk.ms_order.domain.repository.CartRepository;
import com.svysk.ms_order.domain.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    @Override
    public void addProduct(String userId, Product product) {
        List<Cart> userCartOpt = repository.findCartByUserId(userId);
        Cart cart = new Cart();

        if(!userCartOpt.isEmpty()) {
            Optional<Cart> userCartProductOpt = repository.findCartByUserIdAndProduct(userId, product);

            if(userCartProductOpt.isPresent()) {
                cart = userCartProductOpt.get();
                cart.incrementProductCount();
            } else {
                cart.setProduct(product);
                cart.setUserId(userId);
            }

        } else {
            cart.setProduct(product);
            cart.setUserId(userId);
        }

        repository.save(cart);
    }

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }
}
