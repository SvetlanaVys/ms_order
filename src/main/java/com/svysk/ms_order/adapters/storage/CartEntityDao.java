package com.svysk.ms_order.adapters.storage;

import com.svysk.ms_order.adapters.storage.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartEntityDao extends JpaRepository<CartEntity, Long> {

    @Query(value = "FROM CartEntity WHERE userId = :user_id")
    Optional<CartEntity> findByUser(@Param("user_id") String userId);
}
