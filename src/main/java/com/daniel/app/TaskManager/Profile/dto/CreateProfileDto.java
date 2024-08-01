package com.daniel.app.TaskManager.Profile.dto;

import com.daniel.app.TaskManager.user.model.UserModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateProfileDto (byte[] profileImage,@NotEmpty String desc,
                               @NotNull UserModel user){
}
