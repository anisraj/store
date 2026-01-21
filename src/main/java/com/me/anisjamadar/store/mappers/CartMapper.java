package com.me.anisjamadar.store.mappers;

import com.me.anisjamadar.store.dtos.CartDto;
import com.me.anisjamadar.store.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
