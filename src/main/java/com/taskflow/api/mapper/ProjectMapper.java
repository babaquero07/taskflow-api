package com.taskflow.api.mapper;

import com.taskflow.api.domain.Project;
import com.taskflow.api.dto.ProjectRequestDto;
import com.taskflow.api.dto.ProjectResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(target = "id", ignore = true)
    Project toEntity(ProjectRequestDto projectRequestDto);

    ProjectResponseDto toResponseDto(Project project);
}
