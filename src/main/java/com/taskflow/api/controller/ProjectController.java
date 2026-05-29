package com.taskflow.api.controller;

import com.taskflow.api.dto.ProjectRequestDto;
import com.taskflow.api.dto.ProjectResponseDto;
import com.taskflow.api.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDto>> getAllProjects(Pageable pageable) {
        Page<ProjectResponseDto> projects = projectService.findAll(pageable);

        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        ProjectResponseDto project = projectService.findById(id);

        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> saveProject(@RequestBody @Valid ProjectRequestDto projectRequestDto) {
        ProjectResponseDto project = projectService.save(projectRequestDto);

        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id,
                                                            @RequestBody @Valid ProjectRequestDto projectRequestDto) {
        ProjectResponseDto project = projectService.update(id, projectRequestDto);

        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
