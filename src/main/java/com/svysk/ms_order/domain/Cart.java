package com.svysk.ms_order.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class Cart {

    private Long id;
    private String userId;
    private LocalDateTime createdDate;
    private List<CartProduct> cartProducts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void addProductToCart(Product product) {
        CartProduct cartProduct = CartProduct.builder()
                .product(product)
                .productQuantity(1)
                .build();

        if(cartProducts == null) {
            cartProducts = new ArrayList<>();
        }

        cartProducts.add(cartProduct);
    }
}
