package com.taskflow.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectRequestDto {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;
}
