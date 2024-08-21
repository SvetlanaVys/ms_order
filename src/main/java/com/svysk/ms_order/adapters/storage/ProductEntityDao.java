package com.svysk.ms_order.adapters.storage;


import com.svysk.ms_order.adapters.storage.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityDao extends JpaRepository<ProductEntity, Long> {
}
