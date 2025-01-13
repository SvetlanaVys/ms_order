package com.svysk.ms_order.application;

import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.CartProduct;
import com.svysk.ms_order.domain.Product;
import com.svysk.ms_order.domain.exception.CartSaveException;
import com.svysk.ms_order.domain.exception.ProductNotFoundException;
import com.svysk.ms_order.domain.repository.CartRepository;
import com.svysk.ms_order.domain.repository.ProductRepository;
import com.svysk.ms_order.domain.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Cart addProduct(String userId, Product product) {
        if (userId == null || product == null) {
            throw new IllegalArgumentException("User ID and Product must not be null.");
        }

        Optional<Product> dbProduct = productRepository.findById(product.getId());
        if (dbProduct.isEmpty()) {
            throw new ProductNotFoundException(product.getId());
        }

        Cart cart = repository.findByUser(userId).orElseGet(() ->
                Cart.builder()
                        .userId(userId)
                        .createdDate(LocalDateTime.now())
                        .build()
        );

        List<CartProduct> cartProducts = Optional.ofNullable(cart.getCartProducts())
                .orElse(Collections.emptyList());
        Optional<CartProduct> presentCartProduct = cartProducts.stream()
                .filter(cp -> Objects.equals(cp.getProduct(), dbProduct.get()))
                .findFirst();

        presentCartProduct.ifPresentOrElse(
                CartProduct::incrementProductQuantity,
                () -> cart.addProductToCart(dbProduct.get())
        );

        Optional<Cart> savedCart = repository.save(cart);

        if(savedCart.isEmpty()) {
            throw new CartSaveException();
        }

        return savedCart.get();
    }

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }
}
