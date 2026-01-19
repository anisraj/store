package com.me.anisjamadar.store.mappers;

import com.me.anisjamadar.store.dtos.ProductDto;
import com.me.anisjamadar.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
}
