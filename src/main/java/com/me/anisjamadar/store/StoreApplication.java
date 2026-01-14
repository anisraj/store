package com.me.anisjamadar.store;

import com.me.anisjamadar.store.entities.Address;
import com.me.anisjamadar.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        //ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var user = User.builder()
                .name("anis")
                .email("anis@qb.com")
                .password("pass")
                .build();
        var address = Address.builder()
                .street("street")
                .city("Pune")
                .state("Maharashtra")
                .zip("415302")
                .build();

        user.addAddress(address);

        System.out.println(user);
    }

}
