package com.stockapp.user_service.repository;

import com.stockapp.user_service.models.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.email FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}
