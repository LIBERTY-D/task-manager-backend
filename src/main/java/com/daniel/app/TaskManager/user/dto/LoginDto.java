package com.daniel.app.TaskManager.user.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginDto(@NotEmpty() String username, @NotEmpty() String password) {
}
