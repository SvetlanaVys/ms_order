package com.svysk.ms_order.domain;

import lombok.Data;

@Data
public class Cart {

    Long id;
    String userId;
    Product product;
    Integer productCount = 1;

    public Integer incrementProductCount() {
        return ++productCount;
    }

    public Integer decrementProductCount() {
        return --productCount;
    }
}
