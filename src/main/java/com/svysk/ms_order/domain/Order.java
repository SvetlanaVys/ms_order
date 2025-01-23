package com.svysk.ms_order.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    private Long id;
    private String userId;
    private LocalDateTime createdDate;
    private List<OrderedProduct> orderedProducts;
}
