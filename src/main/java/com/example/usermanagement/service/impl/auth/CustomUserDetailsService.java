package com.example.usermanagement.service.impl.auth;

import com.example.usermanagement.entity.UserEntity;
import com.example.usermanagement.exception.custom.NotFoundException;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.example.usermanagement.exception.ExceptionConstants.USER_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;


@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws NotFoundException {
        UserEntity user = userRepository.findActiveByEmail(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage()));

        return new CustomUserDetails(user);
    }
}