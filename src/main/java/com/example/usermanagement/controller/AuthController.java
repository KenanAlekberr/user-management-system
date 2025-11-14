package com.example.usermanagement.controller;

import com.example.usermanagement.dto.request.auth.ChangePasswordRequest;
import com.example.usermanagement.dto.request.auth.ForgotPasswordRequest;
import com.example.usermanagement.dto.request.auth.LoginRequest;
import com.example.usermanagement.dto.request.auth.ResetPasswordRequest;
import com.example.usermanagement.dto.request.auth.VerifyOtpRequest;
import com.example.usermanagement.dto.request.user.UserRegisterRequest;
import com.example.usermanagement.dto.response.AuthResponse;
import com.example.usermanagement.dto.response.UserResponse;
import com.example.usermanagement.service.abstraction.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {
    AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public String register(@Valid @RequestBody UserRegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/verify")
    @ResponseStatus(OK)
    public UserResponse register(@Valid @RequestBody VerifyOtpRequest request) {
        return authService.verifyOtp(request);
    }

    @PostMapping("/login")
    @ResponseStatus(OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/changePassword/{id}")
    @ResponseStatus(OK)
    public void changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(id, request);
    }

    @PostMapping("/forgot-password")
    @ResponseStatus(OK)
    public void forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request.getEmail());
    }

    @PostMapping("/reset-password")
    @ResponseStatus(OK)
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(OK)
    public void logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        authService.logout(authHeader);
    }
}