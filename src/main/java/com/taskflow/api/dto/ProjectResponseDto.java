package com.taskflow.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
}
