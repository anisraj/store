package com.me.anisjamadar.store.repositories;

import com.me.anisjamadar.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> findProductByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
