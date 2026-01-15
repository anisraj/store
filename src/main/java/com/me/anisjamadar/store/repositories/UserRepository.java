package com.me.anisjamadar.store.repositories;

import com.me.anisjamadar.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
