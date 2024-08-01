package com.daniel.app.TaskManager.user.dto;

import com.daniel.app.TaskManager.Profile.model.ProfileModel;


public class AuthResponse {

    private String message;
    private String token;
    private Integer id;

    public String getEmail() {
        return email;
    }

    public AuthResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AuthResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public AuthResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AuthResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthResponse setPassword(String password) {
        this.password = password;
        return this;
    }


    public String getUsername() {
        return username;
    }

    public AuthResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    private String firstName;
    private String lastName;
    private String password;
    private  String email;
    private  String  username;
    private  String profileDesc;
    private byte [] profileImage;
    private  Integer profileId;

    public Integer getProfileId() {
        return profileId;
    }

    public AuthResponse setProfileId(Integer profileId) {
        this.profileId = profileId;
        return this;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public AuthResponse setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
        return this;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public AuthResponse setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public AuthResponse(String message, String token, Integer id, String firstName, String lastName, String email, ProfileModel profile, String password, String username) {
        this.message = message;
        this.token = token;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileDesc = profile.getDesc();
        this.profileImage = profile.getProfileImage();
        this.profileId = profile.getId();
        this.password = password;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public AuthResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
