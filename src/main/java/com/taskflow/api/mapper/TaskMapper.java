package com.taskflow.api.mapper;

import com.taskflow.api.domain.Task;
import com.taskflow.api.dto.TaskRequestDto;
import com.taskflow.api.dto.TaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    Task toEntity(TaskRequestDto taskRequestDto);

    TaskResponseDto toDto(Task task);
}
