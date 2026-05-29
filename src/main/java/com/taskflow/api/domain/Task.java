package com.taskflow.api.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private LocalDate createdAt;
    private LocalDate completedAt;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
