package com.taskflow.api.service;

import com.taskflow.api.domain.Project;
import com.taskflow.api.dto.ProjectRequestDto;
import com.taskflow.api.dto.ProjectResponseDto;
import com.taskflow.api.exception.ResourceNotFoundException;
import com.taskflow.api.mapper.ProjectMapper;
import com.taskflow.api.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectResponseDto> findAll(Pageable pageable) {
        Page<Project> projectsPage = projectRepository.findAll(pageable);

        List<ProjectResponseDto> dtos = projectsPage.getContent().stream()
                .map(projectMapper::toResponseDto).toList();

        return new PageImpl<>(dtos, pageable, projectsPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectResponseDto findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return projectMapper.toResponseDto(project);
    }

    @Override
    @Transactional
    public ProjectResponseDto save(ProjectRequestDto projectRequestDto) {
        Project project = projectMapper.toEntity(projectRequestDto);
        Project savedProject = projectRepository.save(project);

        return projectMapper.toResponseDto(savedProject);
    }

    @Override
    @Transactional
    public ProjectResponseDto update(Long id, ProjectRequestDto projectRequestDto) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        projectRepository.save(existingProject);

        return projectMapper.toResponseDto(existingProject);
    }

    @Override
    public void delete(Long id) {
        Project projectToDelete = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        projectRepository.delete(projectToDelete);
    }
}
