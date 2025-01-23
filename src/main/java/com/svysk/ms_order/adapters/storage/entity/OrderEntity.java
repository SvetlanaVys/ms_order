package com.svysk.ms_order.adapters.storage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchase_order")
@Data
@ToString
@RequiredArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderedProductEntity> orderedProducts;

}
