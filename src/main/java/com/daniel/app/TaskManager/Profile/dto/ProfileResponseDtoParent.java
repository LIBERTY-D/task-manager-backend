package com.daniel.app.TaskManager.Profile.dto;



public class ProfileResponseDtoParent {

    private  String message;
    private int status;

    public String getMessage() {
        return message;
    }

    public ProfileResponseDtoParent setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ProfileResponseDtoParent setStatus(int status) {
        this.status = status;
        return this;
    }

    public ProfileResponseDtoParent(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
