package com.stockapp.user_service.datahelper;

import com.stockapp.user_service.dto.commandDto.UserCommandDTO;
import com.stockapp.user_service.dto.queryDto.UserQueryDTO;
import com.stockapp.user_service.models.Role;
import com.stockapp.user_service.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDataHelper {



    public static UserCommandDTO getUserCommandDTO(String name, String email, String username, String phoneNumber, String password, String status){

        UserCommandDTO dto = new UserCommandDTO();
        dto.setName("Nirmit");
        dto.setEmail("nirmit@gmail.com");
        dto.setUsername("nirmit99");
        dto.setPhoneNumber("98766542");
        dto.setPassword("Nirmit@99");
        dto.setStatus("ACTIVE");

        return dto;
    }

    public static List<UserQueryDTO> createUserQueryList(int count){
        List<UserQueryDTO> userList = new ArrayList<>();

        for(int i = 0; i < count; i++){
            userList.add(getUserQueryDTO(1L, "Nirmit", "nirmit@gmail,com", "nirmit99", "987654321"));
        }
        return userList;
    }

    public static List<User> createUserList(int count){
        List<User> userList = new ArrayList<>();

        for(int i = 0; i < count; i++){
            userList.add(savedUser());
        }
        return userList;
    }


    public static UserQueryDTO getUserQueryDTO(Long id, String name, String email, String username, String phoneNumber){

        UserQueryDTO dto = new UserQueryDTO();
        User user = new User();

        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setUsername(username);
        dto.setPhoneNumber(phoneNumber);

        return dto;
    }

    public static UserCommandDTO createNewUserByUserCommandDTO(){
        return getUserCommandDTO("Nirmit","nirmit@gmail.com", "nirmit99", "9876551", "Nirmit@99", "ACTIVE");
    }

    public static UserQueryDTO savedUserQueryDTO(){
        return getUserQueryDTO(1L,"Nirmit", "nirmit@gmail.com", "nirmit99", "9876551");
    }

    public static User savedUser(){
        User user = new User();

        Role roles = new Role();
        roles.setId(1L);
        roles.setRoleName("RETAIL_USER");

        user.setId(1L);
        user.setName("Nirmit");
        user.setEmail("nirmit@gmail.com");
        user.setUsername("nirmit99");
        user.setPhoneNumber("98766542");
        user.setPassword("Nirmit@99");
        user.setStatus("ACTIVE");
        user.setRoles(Set.of(roles));

        return user;
    }

}
