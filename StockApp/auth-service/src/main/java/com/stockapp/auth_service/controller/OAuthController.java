package com.stockapp.auth_service.controller;

import com.stockapp.auth_service.DTOs.OAuthRequestDto;
import com.stockapp.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth-service/oauth")
public class OAuthController {

    @Autowired
    private AuthService authService;

    public ResponseEntity<?> loginWithGoggle(@RequestBody OAuthRequestDto requestDto){
        return (ResponseEntity<?>) authService.loginWithGoogle(requestDto.getCode());

    }


}
