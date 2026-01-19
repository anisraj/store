package com.me.anisjamadar.store.mappers;

import com.me.anisjamadar.store.dtos.RegisterUserRequest;
import com.me.anisjamadar.store.dtos.UserDto;
import com.me.anisjamadar.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
}
