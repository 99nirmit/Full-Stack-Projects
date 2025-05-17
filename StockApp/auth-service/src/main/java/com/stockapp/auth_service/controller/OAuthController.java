package com.stockapp.auth_service.controller;

import com.stockapp.auth_service.DTOs.OAuthRequestDto;
import com.stockapp.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth-service")
public class OAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/oauth/google")
    public ResponseEntity<?> loginWithGoggle(@RequestBody OAuthRequestDto requestDto){
        Object userInfo = authService.loginWithGoogle(requestDto.getCode());
        return ResponseEntity.ok(userInfo);
    }

}
