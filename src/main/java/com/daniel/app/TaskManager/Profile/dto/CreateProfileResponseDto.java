package com.daniel.app.TaskManager.Profile.dto;


import com.daniel.app.TaskManager.Profile.model.ProfileModel;

public class CreateProfileResponseDto extends ProfileResponseDtoParent
{

    private ProfileModel profile;
    public CreateProfileResponseDto(String message, int status, ProfileModel profile) {
        super(message, status);
        this.profile = profile;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public CreateProfileResponseDto setProfile(ProfileModel profile) {
        this.profile = profile;
        return this;
    }
}
