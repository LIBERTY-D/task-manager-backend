package com.daniel.app.TaskManager.user.mapper;


import com.daniel.app.TaskManager.user.dto.CreateUserDto;
import com.daniel.app.TaskManager.user.dto.UpdateDto;
import com.daniel.app.TaskManager.user.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserModelMapperTest {

    private  UserModelMapper userModelMapper;

    @BeforeEach
    void setUp() {
        userModelMapper =  new UserModelMapper();
    }

    @Test

    void login_user_dto_to_userModel_should_not_be_null(){

        var exp = assertThrows(NullPointerException.class,
                () -> userModelMapper.loginUserDtoToUserModel(null));
        assertEquals("loginDto is null",exp.getMessage());

    }

    @Test
    void should_create_login_user_dto_to_userModel(){
        CreateUserDto createDto =
                new CreateUserDto(1,"jane","doe","12345678"
                ,"janedoe@gmail.com","jane25",null);


        UserModel new_user =  new UserModel();
        new_user.setUsername(createDto.username());
        new_user.setFirstName(createDto.firstName());
        new_user.setLastName(createDto.lastName());
        new_user.setEmail(createDto.email());
        new_user.setPassword(createDto.password());

        assertEquals(createDto.username(),new_user.getUsername());
        assertEquals(createDto.firstName(),new_user.getFirstName());
        assertEquals(createDto.lastName(),new_user.getLastName());
        assertEquals(createDto.email(),new_user.getEmail());
        assertEquals(createDto.password(),new_user.getPassword());


    }

    @Test
    void create_user_dto_to_userModel_should_not_be_null(){
        var exp = assertThrows(NullPointerException.class,
                () -> userModelMapper.createUserDtoToUserModel(null));
        assertEquals("createUserDto is null",exp.getMessage());
    }

    @Test
    void should_create_create_user_dto_to_userModel(){
        CreateUserDto createDto =
                new CreateUserDto(1,"jane","doe","12345678"
                ,"janedoe@gmail.com","jane25",null);

        UserModel new_user =  new UserModel();
        new_user.setUsername(createDto.username());
        new_user.setFirstName(createDto.firstName());
        new_user.setLastName(createDto.lastName());
        new_user.setEmail(createDto.email());
        new_user.setPassword(createDto.password());

        assertEquals(createDto.username(),new_user.getUsername());
        assertEquals(createDto.firstName(),new_user.getFirstName());
        assertEquals(createDto.lastName(),new_user.getLastName());
        assertEquals(createDto.email(),new_user.getEmail());
        assertEquals(createDto.password(),new_user.getPassword());
    }
    @Test

    void update_user_dto_to_userModel_should_not_be_null(){
        var exp = assertThrows(NullPointerException.class,
                () -> userModelMapper.updateUserDtoToUserModel(null));
        assertEquals("updateDto is null",exp.getMessage());

    }

    @Test

    void should_create_update_user_dto_to_userModel(){
        UpdateDto updateDto = new UpdateDto("jane","doe","12345678", "janedoe" +
                "@gmail.com");

        UserModel new_user =  new UserModel();
        new_user.setFirstName(updateDto.firstName());
        new_user.setLastName(updateDto.lastName());
        new_user.setEmail(updateDto.email());
        new_user.setPassword(updateDto.password());

        assertEquals(updateDto.firstName(),new_user.getFirstName());
        assertEquals(updateDto.lastName(),new_user.getLastName());
        assertEquals(updateDto.email(),new_user.getEmail());
    }
}