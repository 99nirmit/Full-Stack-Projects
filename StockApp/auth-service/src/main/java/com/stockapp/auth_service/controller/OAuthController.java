package com.stockapp.auth_service.controller;

import com.stockapp.auth_service.DTOs.OAUthUserInfo;
import com.stockapp.auth_service.DTOs.OAuthRequestDto;
import com.stockapp.auth_service.DTOs.OtpDto;
import com.stockapp.auth_service.service.AuthService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth-service")
public class OAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/oauth/google")
    public ResponseEntity<OAUthUserInfo> loginWithGoggle(@RequestBody OAuthRequestDto requestDto){
        OAUthUserInfo userInfo = authService.loginWithGoogle(requestDto.getCode());
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/sendOtp")
    public void sendOtp(@RequestBody String  phoneNumber){
        authService.sendOtp(phoneNumber);
    }

    @PostMapping("/verifyOtp")
    public Boolean verifyOtp(@RequestBody OtpDto otpDto){
        return authService.verifyOtp(otpDto.getPhone(), otpDto.getOtp());
    }

    @GetMapping("/user")
    public void userCreated(@RequestBody String phoneNumber, Long userId){
        authService.saveUserData(phoneNumber, userId);
    }

}
