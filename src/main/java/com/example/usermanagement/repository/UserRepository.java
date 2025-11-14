package com.example.usermanagement.repository;

import com.example.usermanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    @Query(value = "SELECT * FROM users u WHERE u.id = :id AND u.user_status IN ('ACTIVE', 'IN_PROGRESS')", nativeQuery = true)
    Optional<UserEntity> findActiveById(@Param("id") Long id);

    @Query(value = "SELECT * FROM users u WHERE u.email = :email AND u.user_status IN ('ACTIVE', 'IN_PROGRESS')", nativeQuery = true)
    Optional<UserEntity> findActiveByEmail(@Param("email") String email);
}