package com.taskflow.api.controller;

import com.taskflow.api.dto.TaskRequestDto;
import com.taskflow.api.dto.TaskResponseDto;
import com.taskflow.api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> getAllTasks(Pageable pageable) {
        Page<TaskResponseDto> tasks = taskService.findAll(pageable);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TaskResponseDto>> getAllTasksByStatus(
            @RequestParam String status, Pageable pageable
    ) {
        Page<TaskResponseDto> tasks = taskService.findAllByStatus(status, pageable);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findById(@PathVariable Long id) {
        TaskResponseDto task = taskService.findById(id);

        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> saveTask(@RequestBody @Valid TaskRequestDto taskRequestDto) {
        TaskResponseDto task = taskService.save(taskRequestDto);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask
            (@PathVariable Long id, @RequestBody @Valid TaskRequestDto taskRequestDto) {
        TaskResponseDto task = taskService.update(id, taskRequestDto);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
