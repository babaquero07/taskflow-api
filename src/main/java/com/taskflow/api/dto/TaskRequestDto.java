package com.taskflow.api.dto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotBlank(message = "Priority cannot be blank")
    private String priority;

    // 1. Cambiado a @NotNull porque es un objeto LocalDateTime
    // 2. Opcional: @FutureOrPresent asegura que la fecha límite no sea en el pasado
    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be in the present or future")
    private LocalDateTime dueDate;

    // @NotNull porque es un número (Long)
    @NotNull(message = "project_id is required")
    private Long project_id;

    private LocalDateTime completedAt;
}
