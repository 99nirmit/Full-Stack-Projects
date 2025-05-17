package com.stockapp.auth_service.service;

import com.stockapp.auth_service.DTOs.OAUthUserInfo;
import com.stockapp.auth_service.DTOs.OAuthResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthService {

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    @Value("${jwt.secret}")
    private String jwtSecret;


    public Object loginWithGoogle(String code){

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant", "authorization_code");

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


        HttpEntity<?> userResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                userRequest,
                OAUthUserInfo.class
        );

        System.out.println(userResponse.getBody() + " GoogleUserInfo");

        return userResponse.getBody();


    }

}
