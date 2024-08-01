package com.daniel.app.TaskManager.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateDto(@NotNull() Integer id,
                        String title,
                        String desc,
                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                         Date dueDate,
                         boolean isCompleted,
                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                         Date completedOn,
                        String []tags) {
}
