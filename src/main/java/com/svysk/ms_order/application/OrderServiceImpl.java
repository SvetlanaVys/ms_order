package com.svysk.ms_order.application;

import com.svysk.ms_order.application.mapper.OrderCartMapper;
import com.svysk.ms_order.domain.Order;
import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.Product;
import com.svysk.ms_order.domain.exception.CartNotFoundException;
import com.svysk.ms_order.domain.exception.OrderSaveException;
import com.svysk.ms_order.domain.exception.ProductNotFoundException;
import com.svysk.ms_order.domain.repository.CartRepository;
import com.svysk.ms_order.domain.repository.OrderRepository;
import com.svysk.ms_order.domain.repository.ProductRepository;
import com.svysk.ms_order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final OrderCartMapper orderCartMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Order placeOrder(Cart cart) {

        // Validation
        Cart dbCart = getValidCartFromDB(cart);

        // Map cart to order
        Order order = orderCartMapper.mapCartToOrder(dbCart);

        // Save order
        Order savedOrder = null;
        try {
            savedOrder = repository.save(order);

        } catch (DataIntegrityViolationException ex) {
            throw new OrderSaveException("Failed to save order");
        }

        // Delete cart
        cartRepository.delete(dbCart);

        return savedOrder;
    }


    public Cart getValidCartFromDB(Cart cart) {
        if (cart == null || cart.getId() == null) {
            throw new IllegalArgumentException("Cart must not be null and must have an ID.");
        }

        // Retrieve the cart from the database
        Cart dbCart = cartRepository.findById(cart.getId())
                .orElseThrow(() -> new CartNotFoundException(cart.getId()));

        // Validate product availability
        Set<Long> productIds = dbCart.getCartProducts()
                .stream()
                .map(product -> product.getProduct().getId())
                .collect(Collectors.toSet());

        Set<Long> availableProducts = productRepository.findByIds(productIds)
                .stream()
                .map(Product::getId)
                .collect(Collectors.toSet());

        if(!productIds.containsAll(availableProducts)) {
            throw new ProductNotFoundException("Some products in the cart are not available.");
        }

        return dbCart;
    }
}