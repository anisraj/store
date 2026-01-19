package com.me.anisjamadar.store.controllers;

import com.me.anisjamadar.store.dtos.RegisterUserRequest;
import com.me.anisjamadar.store.dtos.UserDto;
import com.me.anisjamadar.store.entities.User;
import com.me.anisjamadar.store.mappers.UserMapper;
import com.me.anisjamadar.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ) {
        if (!Set.of("name", "email").contains(sort)) {
            sort = "name";
        }
        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
        @RequestBody RegisterUserRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        var user = userMapper.toEntity(request);
        var result = userRepository.save(user);
        var userDto = userMapper.toDto(result);
        var uri = uriBuilder.path("users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
}
