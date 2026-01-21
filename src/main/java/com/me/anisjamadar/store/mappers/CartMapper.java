package com.me.anisjamadar.store.mappers;

import com.me.anisjamadar.store.dtos.CartDto;
import com.me.anisjamadar.store.dtos.CartItemDto;
import com.me.anisjamadar.store.entities.Cart;
import com.me.anisjamadar.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
