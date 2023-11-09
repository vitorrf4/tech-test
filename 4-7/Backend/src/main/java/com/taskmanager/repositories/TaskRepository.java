package com.taskmanager.repositories;

import com.taskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByTaskStatus(Task.Status status);
}
