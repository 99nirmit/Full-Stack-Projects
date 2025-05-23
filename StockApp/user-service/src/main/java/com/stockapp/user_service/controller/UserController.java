package com.stockapp.user_service.controller;

import com.stockapp.user_service.dto.commandDto.UserCommandDTO;
import com.stockapp.user_service.dto.feignClientDto.OAuthUserDTO;
import com.stockapp.user_service.dto.queryDto.UserQueryDTO;
import com.stockapp.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String oAuthRegistration(@RequestBody OAuthUserDTO oAuthUserDTO){
        return userService.oAuthRegistration(oAuthUserDTO);
    }

    @PostMapping("/save")
    public UserQueryDTO createUser(@RequestBody UserCommandDTO dto){
        return userService.createUser(dto);
    }

    @GetMapping("/")
    public List<UserQueryDTO> getALlUsers(){
        return  userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserQueryDTO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public UserQueryDTO updateUser(@PathVariable Long id, @RequestBody UserCommandDTO dto){
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
