package com.daniel.app.TaskManager.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UpdateDto(@NotEmpty String firstName, @NotEmpty String lastName,
                        String password,//TODO delete this in the future
                        @NotEmpty @Email String email) {

}
