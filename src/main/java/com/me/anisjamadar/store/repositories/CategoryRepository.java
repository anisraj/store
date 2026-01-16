package com.me.anisjamadar.store.repositories;

import com.me.anisjamadar.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}