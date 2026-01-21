package com.me.anisjamadar.store.repositories;

import com.me.anisjamadar.store.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}