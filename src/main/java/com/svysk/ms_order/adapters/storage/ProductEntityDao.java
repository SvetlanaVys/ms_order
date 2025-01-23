package com.svysk.ms_order.adapters.storage;


import com.svysk.ms_order.adapters.storage.entity.ProductEntity;
import com.svysk.ms_order.controller.mapper.CartDtoMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProductEntityDao extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN :ids")
    Set<ProductEntity> findByIds(@Param("ids") Set<Long> ids);
}
