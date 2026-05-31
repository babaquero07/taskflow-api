package com.taskflow.api.service;

import com.taskflow.api.dto.TaskRequestDto;
import com.taskflow.api.dto.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskService {
    Page<TaskResponseDto> findAll(Pageable pageable);
    TaskResponseDto findById(Long id);
    TaskResponseDto save(TaskRequestDto taskRequestDto);
    TaskResponseDto update(Long id, TaskRequestDto taskRequestDto);
    void delete(Long id);
}
