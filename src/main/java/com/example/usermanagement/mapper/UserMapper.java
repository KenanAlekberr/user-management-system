package com.example.usermanagement.mapper;

import com.example.usermanagement.dto.request.user.UpdateUserRequest;
import com.example.usermanagement.dto.request.user.UserRegisterRequest;
import com.example.usermanagement.dto.response.UserResponse;
import com.example.usermanagement.entity.UserEntity;
import io.micrometer.common.util.StringUtils;

import java.time.LocalDateTime;

import static com.example.usermanagement.enums.UserRole.USER;
import static com.example.usermanagement.enums.UserStatus.ACTIVE;
import static com.example.usermanagement.enums.UserStatus.IN_PROGRESS;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buildUserEntity(UserRegisterRequest request) {
        return UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(request.getPassword())
                .userStatus(ACTIVE)
                .userRole(USER)
                .build();
    }

    public UserResponse buildUserResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .userStatus(user.getUserStatus())
                .userRole(user.getUserRole())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void updateUser(UserEntity user, UpdateUserRequest request) {
        if (StringUtils.isNotEmpty(request.getFirstName()))
            user.setFirstName(request.getFirstName());

        if (StringUtils.isNotEmpty(request.getLastName()))
            user.setLastName(request.getLastName());

        if (StringUtils.isNotEmpty(request.getEmail()))
            user.setEmail(request.getEmail());

        if (StringUtils.isNotEmpty(request.getPhone()))
            user.setPhone(request.getPhone());

        user.setUserStatus(IN_PROGRESS);
    }
}