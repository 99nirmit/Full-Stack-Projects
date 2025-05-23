package com.stockapp.auth_service.service;

import com.stockapp.auth_service.DTOs.OAUthUserInfo;
import com.stockapp.auth_service.DTOs.OAuthResponseDto;
import com.stockapp.auth_service.models.AuthUser;
import com.stockapp.auth_service.models.OtpStore;
import com.stockapp.auth_service.repository.AuthRepository;
import com.stockapp.auth_service.repository.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
public class AuthService {

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

//    @Value("${jwt.secret}")
//    private String jwtSecret;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OtpStore otpStore;

    @Autowired
    private AuthRepository authRepository;


    public OAUthUserInfo loginWithGoogle(String code){

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        System.out.println(redirectUri + " redirectUri getting");
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<OAuthResponseDto> response = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token",
                request,
                OAuthResponseDto.class
        );

        String accessToken = response.getBody().getAccess_token();

        HttpHeaders authHeader = new HttpHeaders();
        authHeader.setBearerAuth(accessToken);
        HttpEntity<?> userRequest = new HttpEntity<>(authHeader);


        HttpEntity<OAUthUserInfo> userResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                userRequest,
                OAUthUserInfo.class
        );

        OAUthUserInfo userInfo = userResponse.getBody();

        //send user info to userService
        System.out.println("userclient called");
        userClient.createUser(userInfo);

        return userInfo;
    }

    public String  generateOtp(String phoneNumber) {
        Random random = new Random();
        String randomNum = String.valueOf(100000 + random.nextInt(90000));
        return randomNum;
    }

    public void sendOtp(String phoneNumber){
        String otp = generateOtp(phoneNumber);
        otpStore.saveOtp(phoneNumber, otp, 300); //5min
        sendSms(phoneNumber, otp);
    }

    public Boolean verifyOtp(String phoneNumber, String inputOtp) {
        return otpStore.validOtp(phoneNumber, inputOtp);
    }

    public void sendSms(String phoneNumber, String otp){
        System.out.println("Sending Otp to  " + phoneNumber + " : " + otp);

    }

    public void saveUserData(String phoneNumber, Long userId) {

        AuthUser authUser = new AuthUser();
        authUser.setPhoneNumber(phoneNumber);
        authUser.setUserId(userId);

        authRepository.save(authUser);
    }
}
