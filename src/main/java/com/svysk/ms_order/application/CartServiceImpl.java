package com.svysk.ms_order.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svysk.ms_order.domain.Cart;
import com.svysk.ms_order.domain.Product;
import com.svysk.ms_order.domain.repository.CartRepository;
import com.svysk.ms_order.domain.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.svysk.ms_order.config.KafkaTopicConfig.ORDER_REQUEST_TOPIC;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void addProduct(String userId, Product product) {
        List<Cart> userCartOpt = repository.findCartByUserId(userId);
        Cart cart = new Cart();

        if(!userCartOpt.isEmpty()) {
            Optional<Cart> userCartProductOpt = repository.findCartByUserIdAndProduct(userId, product);

            if(userCartProductOpt.isPresent()) {
                cart = userCartProductOpt.get();
                cart.incrementProductQuantity();
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
    public List<Cart> findCartsByUser(String userId) {
        return repository.findCartByUserId(userId);
    }

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }

    @Override
    public void sendCart(final List<Cart> carts) {
        ObjectMapper objectMapper = new ObjectMapper();
        String cartJSON = null;
        try {
            cartJSON = objectMapper.writeValueAsString(carts);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.kafkaTemplate.send(ORDER_REQUEST_TOPIC, cartJSON);
    }
}
