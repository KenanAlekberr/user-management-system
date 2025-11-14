package com.example.usermanagement.service.abstraction.auth;

import com.example.usermanagement.dto.request.auth.ChangePasswordRequest;
import com.example.usermanagement.dto.request.auth.LoginRequest;
import com.example.usermanagement.dto.request.auth.ResetPasswordRequest;
import com.example.usermanagement.dto.request.auth.VerifyOtpRequest;
import com.example.usermanagement.dto.request.user.UserRegisterRequest;
import com.example.usermanagement.dto.response.AuthResponse;
import com.example.usermanagement.dto.response.UserResponse;

public interface AuthService {
    String register(UserRegisterRequest request);

    AuthResponse login(LoginRequest request);

    UserResponse verifyOtp(VerifyOtpRequest request);

    void changePassword(Long id, ChangePasswordRequest request);

    void logout(String authHeader);

    void forgotPassword(String email);

    void resetPassword(ResetPasswordRequest request);
}