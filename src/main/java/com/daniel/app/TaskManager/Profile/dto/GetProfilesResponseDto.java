package com.daniel.app.TaskManager.Profile.dto;

import com.daniel.app.TaskManager.Profile.model.ProfileModel;

import java.util.List;

public class GetProfilesResponseDto extends ProfileResponseDtoParent {
    private  int length;
    private List<ProfileModel> profiles;
    public GetProfilesResponseDto(String message, int status, int length, List<ProfileModel> profiles) {
        super(message, status);
        this.length = length;
        this.profiles = profiles;
    }

    public List<ProfileModel> getProfiles() {
        return profiles;
    }

    public GetProfilesResponseDto setProfiles(List<ProfileModel> profiles) {
        this.profiles = profiles;
        return this;
    }

    public int getLength() {
        return length;
    }

    public GetProfilesResponseDto setLength(int length) {
        this.length = length;
        return this;
    }
}
