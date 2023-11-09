package com.taskmanager.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.taskmanager.models.Task;
import com.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping(path = "/tasks")
public class TaskController {
    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all tasks.
     * @return ResponseEntity with the list of all tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(repository.findAll());
    }

    /**
     * Get a specific task by ID.
     * @param id the ID of the task to retrieve
     * @return ResponseEntity with the task corresponding to the given ID, if found, otherwise returns not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        var task = repository.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new task.
     * @param taskJson a map containing the title and description of the task
     * @return 201 Created status code with the uri where the newly created task
     * can be found and the task itself on the response body
     */
    @PostMapping
    public ResponseEntity createTask(@RequestBody Map<String, String> taskJson) {
        String title = taskJson.get("title");
        String description = taskJson.get("description");

        if (title == null || description == null ||
                title.isEmpty() || title.isBlank() ||
                description.isEmpty() || description.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Task newTask = new Task(title, description);
        Task savedTask = repository.save(newTask);

        return ResponseEntity.created(URI.create("tasks/" + savedTask.getId())).body(savedTask);
    }

    /**
     * Mark a task as completed.
     * @param id the ID of the task to mark as completed
     * @return ResponseEntity indicating the completion status of the task
     */
    @PutMapping("/{id}/complete")
    public ResponseEntity<String> completeTask(@PathVariable Long id ) {
        var task = repository.findById(id);

        if (task.isEmpty()) return ResponseEntity.notFound().build();

        if (task.get().getTaskStatus() == Task.Status.COMPLETED)
            return ResponseEntity.badRequest().body("This task has already been completed");


        task.get().completeTask();
        repository.save(task.get());

        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a task by ID.
     * @param id the ID of the task to delete
     * @return ResponseEntity indicating the deletion status of the task
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Task>> deleteTask(@PathVariable Long id) {
        var task = repository.findById(id);
        if (task.isEmpty()) return ResponseEntity.notFound().build();

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

