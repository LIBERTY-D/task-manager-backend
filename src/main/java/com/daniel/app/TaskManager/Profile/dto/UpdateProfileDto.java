package com.daniel.app.TaskManager.Profile.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateProfileDto(byte[] profileImage, @NotEmpty String desc) {
}
