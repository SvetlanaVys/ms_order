package com.svysk.ms_order.adapters.storage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart-product")
@Data
@ToString
@RequiredArgsConstructor
public class CartProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_product_sequence")
    @SequenceGenerator(name = "cart_product_sequence", sequenceName = "cart_product_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id", nullable=false)
    private CartEntity cart;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity = 1;
}
