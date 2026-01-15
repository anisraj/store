package com.me.anisjamadar.store;

import com.me.anisjamadar.store.entities.User;
import com.me.anisjamadar.store.repositories.UserRepository;
import com.me.anisjamadar.store.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        UserService service = context.getBean(UserService.class);
        service.showRelatedEntities();

    }

}
