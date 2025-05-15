package com.stockapp.user_service.service;

import com.stockapp.user_service.datahelper.UserDataHelper;
import com.stockapp.user_service.dto.commandDto.UserCommandDTO;
import com.stockapp.user_service.dto.queryDto.UserQueryDTO;
import com.stockapp.user_service.models.Role;
import com.stockapp.user_service.models.User;
import com.stockapp.user_service.repository.RoleRepository;
import com.stockapp.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Test
    void createUser() {
        //Arrange
        //I/P Prepare
        UserCommandDTO createUserByCommandDTO = UserDataHelper.createNewUserByUserCommandDTO();
        User savedUser = UserDataHelper.savedUser();

        //Expected Result
        UserQueryDTO expectedUser = UserDataHelper.savedUserQueryDTO();

        //Mock Behaviour

        when(modelMapper.map(any(UserCommandDTO.class), eq(User.class)))
                .thenReturn(savedUser);

        when(modelMapper.map(any(User.class), eq(UserQueryDTO.class)))
                .thenReturn(expectedUser);

        when(passwordEncoder.encode(any(CharSequence.class)))
                .thenReturn("encryptedPassword");

        when(roleRepository.findByName("RETAIL_USER"))
                .thenReturn(Optional.of(new Role()));

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        //Act
        //Actual function call

        UserQueryDTO actualResult = userService.createUser(createUserByCommandDTO);

        //Assert

        assertEquals(expectedUser, actualResult);
        assertEquals(expectedUser.getId(), actualResult.getId());

        verify(userRepository).save(any(User.class));
        verify(roleRepository).findByName("RETAIL_USER");
    }

    @Test
    void getAllUser() {
        //Arrange
        //No I/P
        //Expected
        UserDataHelper.createUserList(5);
        List<User> expectedUserResult = UserDataHelper.createUserList(5);
        UserQueryDTO expectedUser = UserDataHelper.savedUserQueryDTO();
        List<UserQueryDTO> expectedUserQueryResult = UserDataHelper.createUserQueryList(5);

        //Mock Behaviour
        when(userRepository.findAll())
                .thenReturn(expectedUserResult);

        for(int i = 0; i < expectedUserResult.size(); i++) {
            when(modelMapper.map((expectedUserResult.get(i)), UserQueryDTO.class))
                    .thenReturn(expectedUserQueryResult.get(i));
        }

        //Act
        List<UserQueryDTO> actualResult = userService.getAllUser();

        //Assert
        assertEquals(expectedUserQueryResult, actualResult);
        assertEquals(5, actualResult.size());

        verify(userRepository).findAll();
    }

    @Test
    void getUser() {
        //I/P
        Long id = 1L;
        User userExpected = UserDataHelper.savedUser();
        UserQueryDTO userQueryDTOExpected = UserDataHelper.savedUserQueryDTO();

        //Mock Behaviour
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(userExpected));

        when(modelMapper.map(any(User.class), eq(UserQueryDTO.class)))
                .thenReturn(userQueryDTOExpected);

        //ACT
        UserQueryDTO actualResult = userService.getUser(1L);

        //Assert

        assertEquals(userQueryDTOExpected, actualResult);
        assertEquals(userQueryDTOExpected.getId(), actualResult.getId());

        verify(userRepository).findById(1L);
    }

    @Test
    void updateUser() {
        //I/P
        Long id = 1L;
        UserCommandDTO dto = UserDataHelper.createNewUserByUserCommandDTO();
        User userToUpdate = UserDataHelper.savedUser();
        User updatedUser = UserDataHelper.savedUser();

        updatedUser.setName(dto.getName());
        updatedUser.setUsername(dto.getUsername());
        updatedUser.setEmail(dto.getEmail());
        updatedUser.setPhoneNumber(dto.getPhoneNumber());
        updatedUser.setPassword(dto.getPassword());

        UserQueryDTO userQueryDTOExpected = UserDataHelper.savedUserQueryDTO();

        //Mock
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(userToUpdate));

        when(userRepository.save(any(User.class)))
                .thenReturn(updatedUser);

        when(modelMapper.map(any(User.class), eq(UserQueryDTO.class)))
                .thenReturn(userQueryDTOExpected);

        //Act
        UserQueryDTO actualResult = userService.updateUser(1L, dto);

        //assert
        assertEquals(userQueryDTOExpected, actualResult);
        assertEquals(userQueryDTOExpected.getId(), actualResult.getId());

        verify(userRepository).findById(1L);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void deleteUser() {

        //No I/P
        //Mock
        doNothing().when(userRepository).deleteById(1L);

        //ACT
        userService.deleteUser(1L);

        //Assert
        verify(userRepository).deleteById(1L);
    }
}