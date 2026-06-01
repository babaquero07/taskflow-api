package com.taskflow.api.service;

import com.taskflow.api.domain.Project;
import com.taskflow.api.domain.Task;
import com.taskflow.api.dto.TaskRequestDto;
import com.taskflow.api.dto.TaskResponseDto;
import com.taskflow.api.exception.ResourceNotFoundException;
import com.taskflow.api.mapper.TaskMapper;
import com.taskflow.api.repository.ProjectRepository;
import com.taskflow.api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<TaskResponseDto> findAll(Pageable pageable) {
        Page<Task> taskPage = taskRepository.findAll(pageable);

        List<TaskResponseDto> dtos = taskPage.getContent().stream()
                .map(taskMapper::toDto).toList();

        return new PageImpl<>(dtos, pageable, taskPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskResponseDto> findAllByStatus(String status, Pageable pageable) {
        Page<Task> taskPage = taskRepository.findTasksByStatus(status, pageable);

        List<TaskResponseDto> dtos = taskPage.getContent().stream()
                .map(taskMapper::toDto).toList();

        return new PageImpl<>(dtos, pageable, taskPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task not found"));


        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public TaskResponseDto save(TaskRequestDto taskRequestDto) {
        Task task = taskMapper.toEntity(taskRequestDto);

        Project project = projectRepository.findById(taskRequestDto.getProject_id())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        task.setProject(project);

        Task savedTask = taskRepository.save(task);

        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepository.save(task);

        return taskMapper.toDto(task);
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);
    }
}
