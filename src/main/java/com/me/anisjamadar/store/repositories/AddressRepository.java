package com.me.anisjamadar.store.repositories;

import com.me.anisjamadar.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}