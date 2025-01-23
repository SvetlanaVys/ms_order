package com.svysk.ms_order.adapters.storage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ordered-product")
@Data
@ToString
@RequiredArgsConstructor
public class OrderedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordered_product_sequence")
    @SequenceGenerator(name = "ordered_product_sequence", sequenceName = "ordered_product_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    private OrderEntity order;

    @Column(name = "original_product_id")
    private Long originalProductId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Double productPrice;

    private Integer quantity;
}
