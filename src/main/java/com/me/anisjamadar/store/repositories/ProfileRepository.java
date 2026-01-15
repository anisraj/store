package com.me.anisjamadar.store.repositories;

import com.me.anisjamadar.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}