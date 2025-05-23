package com.stockapp.user_service.dto.queryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryDTO {

    private Long id;

    private String name;

    private String email;

    private String username;

    private String phoneNumber;

    private String string;
}
