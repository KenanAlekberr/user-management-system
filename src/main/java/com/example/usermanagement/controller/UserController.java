package com.example.usermanagement.controller;

import com.example.usermanagement.dto.request.user.UpdateUserRequest;
import com.example.usermanagement.dto.response.PaginationResponse;
import com.example.usermanagement.dto.response.UserResponse;
import com.example.usermanagement.service.abstraction.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/user")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = PRIVATE)
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    UserService userService;

    @GetMapping
    public PaginationResponse<UserResponse> getAllUsers(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return userService.getAllUsers(page, size, firstName, lastName);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(OK)
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/put/{id}")
    @ResponseStatus(NO_CONTENT)
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}