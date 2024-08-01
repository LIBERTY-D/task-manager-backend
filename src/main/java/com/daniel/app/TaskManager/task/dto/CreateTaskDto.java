package com.daniel.app.TaskManager.task.dto;

import com.daniel.app.TaskManager.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record   CreateTaskDto(Integer id,
                            @NotEmpty()  String title,
                            @NotEmpty  String desc,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                            @NotNull() Date dueDate,
                            @NotNull() String []tags,
                            @NotNull Integer userId) {


}
