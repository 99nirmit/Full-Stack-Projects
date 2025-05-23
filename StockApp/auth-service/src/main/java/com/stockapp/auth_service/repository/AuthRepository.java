package com.stockapp.auth_service.repository;

import com.stockapp.auth_service.models.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthUser, Long> {
    String findByPhoneNumber(String phoneNumber);
}
