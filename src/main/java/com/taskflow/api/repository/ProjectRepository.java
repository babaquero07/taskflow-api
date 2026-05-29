package com.taskflow.api.repository;

import com.taskflow.api.domain.Project;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Override
    @NonNull
    Page<Project> findAll(@NonNull Pageable pageable);
}
