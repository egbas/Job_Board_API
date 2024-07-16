package com.egbas.job_board.service;

import com.egbas.job_board.payload.request.RegisterRequest;
import com.egbas.job_board.payload.request.UpdateRequest;
import com.egbas.job_board.payload.response.ApiResponse;

public interface UserService {

    ApiResponse<?> viewUser();
    ApiResponse<?> updateUser(UpdateRequest updateRequest);
    ApiResponse<?> deleteUser();
}
