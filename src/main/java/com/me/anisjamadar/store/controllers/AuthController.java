package com.me.anisjamadar.store.controllers;

import com.me.anisjamadar.store.config.JwtConfig;
import com.me.anisjamadar.store.dtos.JwtResponse;
import com.me.anisjamadar.store.dtos.LoginRequest;
import com.me.anisjamadar.store.dtos.UserDto;
import com.me.anisjamadar.store.mappers.UserMapper;
import com.me.anisjamadar.store.repositories.UserRepository;
import com.me.anisjamadar.store.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
        @Valid @RequestBody LoginRequest loginRequest,
        HttpServletResponse response
    ) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.email,
                loginRequest.password
            )
        );

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        var accessToken = jwtService.generateAccessToken(user);

        var refreshToken = jwtService.generateRefreshToken(user);
        var cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        cookie.setSecure(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(accessToken));
    }

    @PostMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String authHeader) {
        System.out.println("Validate called");
        var token = authHeader.replace("Bearer ", "");
        return jwtService.validateToken(token);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();

        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        var userDto = userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }
}
