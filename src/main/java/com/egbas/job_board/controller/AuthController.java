package com.egbas.job_board.controller;

import com.egbas.job_board.payload.request.LoginRequest;
import com.egbas.job_board.payload.request.RegisterRequest;
import com.egbas.job_board.payload.response.ApiResponse;
import com.egbas.job_board.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest registerRequest){
        ApiResponse<?> response = authService.register(registerRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest loginRequest){
        ApiResponse<?> response = authService.login(loginRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
