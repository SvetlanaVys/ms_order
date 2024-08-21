package com.svysk.ms_order.adapters.storage;

import com.svysk.ms_order.adapters.storage.entity.CartEntity;
import com.svysk.ms_order.adapters.storage.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartEntityDao extends JpaRepository<CartEntity, Long> {

    @Query(value = "FROM CartEntity WHERE userId = :user_id")
    List<CartEntity> findCartByUserId(@Param("user_id") String userId);

    @Query(value = "FROM CartEntity WHERE userId = :user_id AND product = :product")
    CartEntity findCartByUserIdAndProduct(@Param("user_id") String userId,
                                          @Param("product") ProductEntity product);
}
