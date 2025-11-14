package com.example.usermanagement.repository.specification;

import com.example.usermanagement.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import static com.example.usermanagement.enums.UserStatus.DELETED;

public class UserSpecification {
    public static Specification<UserEntity> filterBy(String firstName, String lastName) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();

            predicate = cb.and(predicate, cb.notEqual(root.get("userStatus"), DELETED));

            if (firstName != null && !firstName.isBlank())
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));

            if (lastName != null && !lastName.isBlank())
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"));

            return predicate;
        };
    }
}