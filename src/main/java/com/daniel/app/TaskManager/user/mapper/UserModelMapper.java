package com.daniel.app.TaskManager.user.mapper;


import com.daniel.app.TaskManager.user.dto.CreateUserDto;
import com.daniel.app.TaskManager.user.dto.LoginDto;
import com.daniel.app.TaskManager.user.dto.UpdateDto;
import com.daniel.app.TaskManager.user.model.UserModel;

import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {


    public UserModel loginUserDtoToUserModel(LoginDto loginDto){
        if(loginDto==null){
            throw  new NullPointerException("loginDto is null");
        }
        UserModel new_user =  new UserModel();
        new_user.setPassword(loginDto.password());
        new_user.setUsername(loginDto.username());


        return  new_user;
    }
    public  UserModel createUserDtoToUserModel(CreateUserDto createUserDto){
        if(createUserDto==null){
            throw  new NullPointerException("createUserDto is null");
        }
        UserModel new_user =  new UserModel();
        new_user.setUsername(createUserDto.username());
        new_user.setFirstName(createUserDto.firstName());
        new_user.setLastName(createUserDto.lastName());
        new_user.setEmail(createUserDto.email());
        new_user.setPassword(createUserDto.password());
        var profile =  createUserDto.profile();

        new_user.setProfile(profile);
        profile.setUser(new_user);
        return  new_user;
    }
    public  UserModel updateUserDtoToUserModel(UpdateDto updateDto){
        if(updateDto==null){
            throw  new NullPointerException("updateDto is null");
        }
         UserModel new_user =  new UserModel();
            new_user.setLastName(updateDto.lastName());
            new_user.setFirstName(updateDto.firstName());
            new_user.setEmail(updateDto.email());
            new_user.setPassword(updateDto.password());



        return  new_user;
    }





}
