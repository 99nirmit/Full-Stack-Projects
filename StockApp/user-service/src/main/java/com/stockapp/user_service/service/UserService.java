package com.stockapp.user_service.service;

import com.stockapp.user_service.dto.commandDto.UserCommandDTO;
import com.stockapp.user_service.dto.queryDto.UserQueryDTO;
import com.stockapp.user_service.models.Role;
import com.stockapp.user_service.models.User;
import com.stockapp.user_service.repository.RoleRepository;
import com.stockapp.user_service.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserQueryDTO createUser(UserCommandDTO dto){
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus("ACTIVE");

        Role defaultRole = roleRepository.findByRoleName("RETAIL_USER")
                .orElseThrow(() -> new RuntimeException("Default Role Not Found"));
        user.setRoles(Set.of(defaultRole));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserQueryDTO.class);
    }

    public List<UserQueryDTO> getAllUser(){
        List<User> users = userRepository.findAll();

        return  users.stream()
                .map(user -> modelMapper.map(user, UserQueryDTO.class))
                .collect(Collectors.toList());
    }

    public UserQueryDTO getUser(Long id){
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException());

        return modelMapper.map(user, UserQueryDTO.class);
    }

    public UserQueryDTO updateUser(Long id, UserCommandDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException());

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword((dto.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserQueryDTO.class);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
