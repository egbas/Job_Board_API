package com.egbas.job_board.service.impl;

import com.egbas.job_board.config.JwtService;
import com.egbas.job_board.domain.entity.User;
import com.egbas.job_board.payload.request.LoginRequest;
import com.egbas.job_board.payload.request.RegisterRequest;
import com.egbas.job_board.payload.response.ApiResponse;
import com.egbas.job_board.repository.UserRepository;
import com.egbas.job_board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public ApiResponse<?> register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .dateOfBirth(registerRequest.getDateOfBirth())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepository.save(user);

        return ApiResponse.builder()
                .status("success")
                .message("Registration successful")
                .statusCode(HttpStatus.CREATED)
                .build();
    }

    @Override
    public ApiResponse<?> login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword())
        );

        User savedUser = userRepository.findByEmail(loginRequest.getEmail()).get();
        String jwtToken = jwtService.generateToken(savedUser);

        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", jwtToken);

        return ApiResponse.<Map<String, Object>>builder()
                .status("success")
                .message("Login successful")
                .data(data)
                .statusCode(HttpStatus.OK)
                .build();
    }
}
