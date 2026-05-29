package com.taskflow.api.service;

import com.taskflow.api.dto.ProjectRequestDto;
import com.taskflow.api.dto.ProjectResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProjectService {
    Page<ProjectResponseDto> findAll(Pageable pageable);
    ProjectResponseDto findById(Long id);
    ProjectResponseDto save(ProjectRequestDto projectRequestDto);
    ProjectResponseDto update(Long id, ProjectRequestDto projectRequestDto);
    void delete(Long id);
}
