package com.stockapp.auth_service.repository;

import com.stockapp.auth_service.DTOs.OAUthUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url="http://localhost:8082")
public interface UserClient {

    @PostMapping("api/users/create")
    ResponseEntity<String> createUser(@RequestBody OAUthUserInfo oaUthUserInfo);
}
