package com.me.anisjamadar.store;

import com.me.anisjamadar.store.entities.Address;
import com.me.anisjamadar.store.entities.Profile;
import com.me.anisjamadar.store.entities.Tag;
import com.me.anisjamadar.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var user = User.builder()
                .name("anis")
                .email("anis@qb.com")
                .password("pass")
                .build();

        var profile = Profile.builder()
                        .bio("bio")
                        .build();

        user.setProfile(profile);
        profile.setUser(user);

        System.out.println(user);
    }

}
