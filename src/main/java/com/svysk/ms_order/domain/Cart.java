package com.svysk.ms_order.domain;

import lombok.Data;

@Data
public class Cart {

    Long id;
    String userId;
    Product product;
    Integer productQuantity = 1;

    public Integer incrementProductQuantity() {
        return ++productQuantity;
    }

    public Integer decrementProductQuantity() {
        return --productQuantity;
    }
}
