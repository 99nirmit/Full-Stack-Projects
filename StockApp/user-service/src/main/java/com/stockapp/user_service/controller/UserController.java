package com.stockapp.user_service.controller;

import com.netflix.discovery.converters.Auto;
import com.stockapp.user_service.dto.commandDto.UserCommandDTO;
import com.stockapp.user_service.dto.queryDto.UserQueryDTO;
import com.stockapp.user_service.service.UserService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

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
