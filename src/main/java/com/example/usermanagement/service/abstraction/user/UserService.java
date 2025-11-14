package com.example.usermanagement.service.abstraction.user;

import com.example.usermanagement.dto.request.user.UpdateUserRequest;
import com.example.usermanagement.dto.response.PaginationResponse;
import com.example.usermanagement.dto.response.UserResponse;

public interface UserService {
    PaginationResponse<UserResponse> getAllUsers(int page, int size, String firstName, String lastName);

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);
}