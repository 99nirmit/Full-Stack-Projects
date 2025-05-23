package com.stockapp.user_service.service;

import com.stockapp.user_service.dto.commandDto.UserCommandDTO;
import com.stockapp.user_service.dto.feignClientDto.OAuthUserDTO;
import com.stockapp.user_service.dto.queryDto.UserQueryDTO;
import com.stockapp.user_service.models.Role;
import com.stockapp.user_service.models.User;
import com.stockapp.user_service.repository.AuthClient;
import com.stockapp.user_service.repository.RoleRepository;
import com.stockapp.user_service.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    private AuthClient authClient;


    public UserQueryDTO createUser(UserCommandDTO dto){
        User user = modelMapper.map(dto, User.class);
        user.setStatus("ACTIVE");

        Role defaultRole = roleRepository.findByRoleName("RETAIL_USER")
                .orElseThrow(() -> new RuntimeException("Default Role Not Found"));
        user.setRoles(Set.of(defaultRole));
        User savedUser = null;
        if(checkIfUserIsAlreadyRegistered(dto.getEmail())){
            savedUser = userRepository.save(user);
        }

        authClient.userCreated(savedUser.getPhoneNumber(), savedUser.getId());
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

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserQueryDTO.class);
    }

    public Boolean checkIfUserIsAlreadyRegistered(String email){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        System.out.println(user.isPresent() + " user found in DB");
        return user.isPresent();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public String oAuthRegistration(OAuthUserDTO oAuthUserDTO) {
        System.out.println("oAuthRegistration called of user-service");
        Boolean userAlreadyRegistered = checkIfUserIsAlreadyRegistered(oAuthUserDTO.getEmail());
        if(userAlreadyRegistered){
            System.out.println("New User creation process");
            return "New User";
        }else{
            return "Already User";
        }
    }
}
