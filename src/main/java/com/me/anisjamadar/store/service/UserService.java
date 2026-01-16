package com.me.anisjamadar.store.service;

import com.me.anisjamadar.store.entities.Address;
import com.me.anisjamadar.store.entities.Product;
import com.me.anisjamadar.store.entities.User;
import com.me.anisjamadar.store.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

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

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
    }

    public void persistRelated() {
        var user = User.builder()
                .name("arshiya")
                .email("ar@infy.com")
                .password("pass")
                .build();

        var address = Address.builder()
                .street("street")
                .city("bhadole")
                .state("state")
                .zip("415")
                .build();

        user.addAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(22L).orElseThrow();
        var address = user.getAddresses().get(0);
        user.removeAddress(address);
    }

    @Transactional
    public void manageProducts() {
        var category = categoryRepository.findById((byte) 1).orElseThrow();
        var product = Product.builder()
                .name("product 2")
                .description("desc 2")
                .price(BigDecimal.valueOf(11.99))
                .category(category)
                .build();

        productRepository.save(product);

    }
}
