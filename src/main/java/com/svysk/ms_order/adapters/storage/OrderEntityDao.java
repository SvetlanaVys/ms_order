package com.svysk.ms_order.adapters.storage;

import com.svysk.ms_order.adapters.storage.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityDao extends JpaRepository<OrderEntity, Long> {
}
