package com.example.foodordering.FoodOrderingAPI.services;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.LoginRequest;
import com.example.foodordering.FoodOrderingAPI.dto.requestDto.RegisterUserRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.AuthResponse;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.UserResponse;
import com.example.foodordering.FoodOrderingAPI.exception.BusinessException;
import com.example.foodordering.FoodOrderingAPI.mapper.UserMapper;
import com.example.foodordering.FoodOrderingAPI.models.User;
import com.example.foodordering.FoodOrderingAPI.repository.UserRepository;
import com.example.foodordering.FoodOrderingAPI.webSecurity.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserResponse register(RegisterUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already registered");
        }

        User user = userMapper.toEntity(request);

        String hashedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BusinessException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new BusinessException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }


}
