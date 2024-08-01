package com.daniel.app.TaskManager.Profile.dto;

import com.daniel.app.TaskManager.Profile.model.ProfileModel;

public class UpdateProfileResponseDto extends CreateProfileResponseDto{
    public UpdateProfileResponseDto(String message, int status, ProfileModel profile) {
        super(message, status, profile);
    }
}
