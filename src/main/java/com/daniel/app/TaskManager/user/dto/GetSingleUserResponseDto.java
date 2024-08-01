package com.daniel.app.TaskManager.user.dto;


import com.daniel.app.TaskManager.Profile.model.ProfileModel;
import com.daniel.app.TaskManager.user.model.UserModel;

public class GetSingleUserResponseDto {

        private String message;
        private int status;
        private UserModel user;
        ProfileModel profile;

    public GetSingleUserResponseDto(String message, int status,
                                    UserModel user,ProfileModel profile) {
        this.message = message;
        this.status = status;
        this.user = user;
        this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public GetSingleUserResponseDto setProfile(ProfileModel profile) {
        this.profile = profile;
        return this;
    }

    public GetSingleUserResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public UserModel getUser() {
        return user;
    }

    public GetSingleUserResponseDto setUser(UserModel user) {
        this.user = user;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public GetSingleUserResponseDto setStatus(int status) {
        this.status = status;
        return this;
    }
}
