package com.daniel.app.TaskManager.user.exception;



import java.util.HashMap;

public class UserException {
    private int status;

    private HashMap<String,Object> errors;

    public HashMap<String, Object> getErrors() {
        return errors;
    }

    public UserException setErrors(HashMap<String, Object> errors) {
        this.errors = errors;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public UserException() {
    }

    public UserException(HashMap<String, Object> errors, int status) {
        this.errors = errors;
        this.status = status;
    }

    public UserException setStatus(int status) {
        this.status = status;
        return this;
    }
}
