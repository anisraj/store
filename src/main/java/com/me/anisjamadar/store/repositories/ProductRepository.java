package com.me.anisjamadar.store.repositories;

import com.me.anisjamadar.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}