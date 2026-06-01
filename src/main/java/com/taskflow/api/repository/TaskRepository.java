package com.taskflow.api.repository;

import com.taskflow.api.domain.Task;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Override
    @NonNull
    @EntityGraph(attributePaths = "project")
    Page<Task> findAll(@NonNull Pageable pageable);

    @Override
    @NonNull
    @EntityGraph(attributePaths = "project")
    Optional<Task> findById(@NonNull Long id);

    @EntityGraph(attributePaths = "project")
    Page<Task> findTasksByStatus(String status, Pageable pageable);
}
