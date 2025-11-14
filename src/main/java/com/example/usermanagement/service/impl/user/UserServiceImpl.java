package com.example.usermanagement.service.impl.user;

import com.example.usermanagement.dto.request.user.UpdateUserRequest;
import com.example.usermanagement.dto.response.PaginationResponse;
import com.example.usermanagement.dto.response.UserResponse;
import com.example.usermanagement.entity.UserEntity;
import com.example.usermanagement.exception.custom.NotFoundException;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.repository.specification.UserSpecification;
import com.example.usermanagement.service.abstraction.user.UserService;
import com.example.usermanagement.util.CacheUtilWithRedisson;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.usermanagement.constant.AppConstants.ALL_USERS;
import static com.example.usermanagement.constant.AppConstants.USER_KEY_PREFIX;
import static com.example.usermanagement.constant.AppConstants.USER_LIST_KEY_FORMAT;
import static com.example.usermanagement.enums.UserStatus.DELETED;
import static com.example.usermanagement.exception.ExceptionConstants.USER_NOT_FOUND;
import static com.example.usermanagement.mapper.UserMapper.USER_MAPPER;
import static java.util.concurrent.TimeUnit.MINUTES;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    CacheUtilWithRedisson cache;

    @Override
    public PaginationResponse<UserResponse> getAllUsers(int page, int size, String firstName, String lastName) {
        String key = String.format(USER_LIST_KEY_FORMAT, page, size,
                firstName != null ? firstName : "", lastName != null ? lastName : ""
        );

        PaginationResponse<UserResponse> cached = cache.get(key, PaginationResponse.class);

        if (cached != null)
            return cached;

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        var spec = UserSpecification.filterBy(firstName, lastName);

        Page<UserEntity> userPage = userRepository.findAll(spec, pageable);

        List<UserResponse> content = userPage.getContent()
                .stream()
                .map(USER_MAPPER::buildUserResponse)
                .toList();

        PaginationResponse<UserResponse> response = PaginationResponse.<UserResponse>builder()
                .content(content)
                .totalElements(userPage.getTotalElements())
                .totalPages(userPage.getTotalPages())
                .hasNext(userPage.hasNext())
                .build();

        cache.set(key, response, 5, MINUTES);

        return response;
    }

    @Override
    public UserResponse getUserById(Long id) {
        String key = USER_KEY_PREFIX + id;

        UserResponse cached = cache.get(key, UserResponse.class);

        if (cached != null)
            return cached;

        UserEntity user = fetchUserIfExist(id);
        UserResponse response = USER_MAPPER.buildUserResponse(user);

        cache.set(key, response, 5, MINUTES);

        return response;
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        UserEntity user = fetchUserIfExist(id);

        USER_MAPPER.updateUser(user, request);
        userRepository.save(user);

        UserResponse response = USER_MAPPER.buildUserResponse(user);

        String key = USER_KEY_PREFIX + id;

        cache.set(key, response, 5, MINUTES);
        cache.deleteByPattern(ALL_USERS);

        return response;
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = fetchUserIfExist(id);
        user.setUserStatus(DELETED);

        userRepository.save(user);

        cache.delete(USER_KEY_PREFIX + id);
        cache.deleteByPattern(ALL_USERS);
    }

    private UserEntity fetchUserIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage()));
    }
}