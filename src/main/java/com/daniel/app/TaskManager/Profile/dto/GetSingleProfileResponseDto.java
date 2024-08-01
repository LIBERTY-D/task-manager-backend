package com.daniel.app.TaskManager.Profile.dto;

import com.daniel.app.TaskManager.Profile.model.ProfileModel;

import java.util.Optional;

public class GetSingleProfileResponseDto extends  ProfileResponseDtoParent {


    private Optional<ProfileModel> profile;

    public Optional<ProfileModel> getProfile() {
        return profile;
    }

    public GetSingleProfileResponseDto setProfile(Optional<ProfileModel> profile) {
        this.profile = profile;
        return this;
    }

    public GetSingleProfileResponseDto(String message, int status, Optional<ProfileModel> profile) {
        super(message, status);
        this.profile = profile;
    }





}
