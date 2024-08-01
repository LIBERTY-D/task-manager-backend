package com.daniel.app.TaskManager.task.dto;

import com.daniel.app.TaskManager.task.model.TaskModel;
import org.springframework.http.HttpStatus;

public class ResponseDto {
    public HttpStatus status;
    String message;
    TaskModel[] taskModels;
    public ResponseDto(HttpStatus status, String message, TaskModel[] taskModels) {
        this.status = status;
        this.message = message;
        this.taskModels = taskModels;
    }

    public String getMessage() {
        return message;
    }

    public ResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ResponseDto setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public TaskModel[] getTasks() {
        return taskModels;
    }

    public ResponseDto setTasks(TaskModel[] taskModels) {
        this.taskModels = taskModels;
        return this;
    }
}
