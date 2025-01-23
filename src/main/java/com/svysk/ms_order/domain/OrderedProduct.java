package com.svysk.ms_order.domain;

import lombok.Data;

@Data
public class OrderedProduct {
    private Long id;
    private Long originalProductId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
}
