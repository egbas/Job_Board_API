package com.egbas.job_board.service;

import com.egbas.job_board.payload.request.LoginRequest;
import com.egbas.job_board.payload.request.RegisterRequest;
import com.egbas.job_board.payload.response.ApiResponse;

public interface AuthService {
    ApiResponse<?> register(RegisterRequest registerRequest);
    ApiResponse<?> login(LoginRequest loginRequest);
}
