package com.taskmanager;

import com.taskmanager.models.Task;
import com.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestData implements ApplicationRunner {
    private final TaskRepository taskRepository;

    @Autowired
    public TestData(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void run(ApplicationArguments args) {
        taskRepository.save(new Task("title 1", "description 1"));
        taskRepository.save(new Task("title 2", "description 2"));
        taskRepository.save(new Task("title 3", "description 3"));
    }
}
