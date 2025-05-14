package com.stockapp.user_service.dto.commandDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCommandDTO {

    private String name;

    private String email;

    private String username;

    private String password;

}
