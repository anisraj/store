package com.me.anisjamadar.store.service;

import com.me.anisjamadar.store.entities.User;
import com.me.anisjamadar.store.repositories.ProfileRepository;
import com.me.anisjamadar.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public void showEntityState() {
        User user = User.builder()
                .name("anis")
                .email("anis@qb.com")
                .password("pass")
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(8L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }
}
