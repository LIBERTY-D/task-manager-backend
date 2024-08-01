package com.daniel.app.TaskManager.user.service;

import com.daniel.app.TaskManager.user.dto.AuthResponse;
import com.daniel.app.TaskManager.user.model.Role;
import com.daniel.app.TaskManager.user.model.UserModel;
import com.daniel.app.TaskManager.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class UserServiceTest {

    // service to test

    @InjectMocks  // injects mocks into the UserService class for testing
    private  UserService userService;

    // mocks
    @Mock
    private UserRepository userRepository;

    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  JwtService jwtService;
    @Mock
    private   AuthenticationManager authenticationManager;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void should_test_signUp(){

        // given
        UserModel user= new UserModel("jane","doe","janedoe@gmail.com",
                "jane25","12345678", Role.USER,null,null);


        // when

        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn(
                "encodePassword");

        Mockito.when(jwtService.generateToken(any(UserModel.class))).thenReturn(
                "jwtToken");

        // then
        AuthResponse authResponse = userService.signUp(user);

        assertEquals("jwtToken", authResponse.getToken());
        assertEquals("encodePassword", user.getPassword());


        verify(jwtService,times(1)).generateToken(user);
        verify(userRepository,times(1)).save(user);


    }

    @Test
    void should_get_users(){

        // given
        List<UserModel> users = new ArrayList<UserModel>();
        users.add(new UserModel("jane","doe","janedoe@gmail.com",
                "jane25","12345678", Role.USER,null,null));

        users.add(new UserModel("janet","doe","janetdoe@gmail.com",
                "janet25","12345678", Role.USER,null,null));

        users.add(new UserModel("john","doe","johndoe@gmail.com",
                "john25","12345678", Role.USER,null,null));



        // when
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // then
        List<UserModel> actualUsers = userService.getUsers();

        assertEquals(3, actualUsers.size());




    }
    @Test
    void should_sign_user(){

        // given
        UserModel user= new UserModel("john","doe","johndoe@gmail.com",
                "john25","12345678", Role.USER,null,null);

        // when

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(jwtService.generateToken(user)).thenReturn("jwtToken");
        // then
        AuthResponse authResponse = userService.signIn(user);

        assertEquals("jwtToken", authResponse.getToken());
        verify(jwtService, times(1)).generateToken(user);


        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));


    }

    @Test
    void test_update_user() {
        // Arrange
        UserModel existingUser = new UserModel();
        existingUser.setUsername("existingUser");

        UserModel updatedUser = new UserModel();
        updatedUser.setUsername("updatedUser");

        // when
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(any(UserModel.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Mock save to return the passed argument

        // then
        UserModel result = userService.updateUser(1, updatedUser);

        // Assert
        assertEquals("existingUser", result.getUsername());
        verify(userRepository).findById(1);
        verify(userRepository).save(updatedUser);
    }

    @Test
    void should_delete_user(){

        // given
        Integer id = 1;

        // when
        userService.deleteUser(id);



        // then
        verify(userRepository).deleteById(id);



    }
}