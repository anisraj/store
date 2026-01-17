package com.me.anisjamadar.store.service;

import com.me.anisjamadar.store.entities.Address;
import com.me.anisjamadar.store.entities.Category;
import com.me.anisjamadar.store.entities.Product;
import com.me.anisjamadar.store.entities.User;
import com.me.anisjamadar.store.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
        var category = new Category("cat 1");
        var product = Product.builder()
                .name("product 2")
                .description("desc 2")
                .price(BigDecimal.valueOf(11.99))
                .category(category)
                .build();

        productRepository.save(product);

    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(20), (byte) 1);
    }

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("sh");

        var matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
        var products = productRepository.findProductByCriteria(null, BigDecimal.valueOf(1), BigDecimal.valueOf(10));
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach((u) -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }
}
