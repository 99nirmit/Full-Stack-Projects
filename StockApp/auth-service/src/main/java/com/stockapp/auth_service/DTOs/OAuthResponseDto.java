package com.stockapp.auth_service.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthResponseDto {

    private String access_token;
    private String expires_in;
    private String token_type;
    private String scope;
    private String id_token;

}
