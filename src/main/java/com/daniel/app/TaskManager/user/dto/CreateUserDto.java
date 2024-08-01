package com.daniel.app.TaskManager.user.dto;


import com.daniel.app.TaskManager.Profile.model.ProfileModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public record CreateUserDto(
        Integer id,
        @NotEmpty String firstName,
        @NotEmpty String lastName,
        @NotEmpty String password,
        @NotEmpty @Email  String email,
        @NotEmpty  String  username,
         ProfileModel profile
) {
}
