package com.me.anisjamadar.store.controllers;

import com.me.anisjamadar.store.dtos.ProductDto;
import com.me.anisjamadar.store.entities.Product;
import com.me.anisjamadar.store.mappers.ProductMapper;
import com.me.anisjamadar.store.repositories.CategoryRepository;
import com.me.anisjamadar.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProducts(
         @RequestParam(required = false, name = "categoryId") Byte categoryId
    ) {
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }
        return products
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(
        @RequestBody ProductDto productDto,
        UriComponentsBuilder uriBuilder
    ) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(productDto);
        product.setCategory(category);

        var result = productRepository.save(product);
        var addedProductDto = productMapper.toDto(result);
        var uri = uriBuilder.path("products/{id}").buildAndExpand(addedProductDto.getId()).toUri();
        return ResponseEntity.created(uri).body(addedProductDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
        @PathVariable(name = "id") Long id,
        @RequestBody ProductDto productDto
    ) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productMapper.update(productDto, product);
        product.setCategory(category);
        var result = productRepository.save(product);
        return ResponseEntity.ok(productMapper.toDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
