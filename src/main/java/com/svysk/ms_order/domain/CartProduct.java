package com.svysk.ms_order.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class CartProduct {
    private Long id;
    private Product product;
    private Integer productQuantity;

    public void incrementProductQuantity() {
        ++productQuantity;
    }

    public void decrementProductQuantity() {
        --productQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProduct that = (CartProduct) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
