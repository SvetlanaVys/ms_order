package com.svysk.ms_order.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class Product {

    Long id;
    String name;
    Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
