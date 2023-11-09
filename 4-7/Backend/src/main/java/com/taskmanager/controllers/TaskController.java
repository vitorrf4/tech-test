package com.taskmanager.controllers;

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
     * @return 200 with the list of all the tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(repository.findAll());
    }

    /**
     * Get a list of tasks by their status
     * @param status the status of the task, either pending or completed
     * @return 400 if the sent status is neither pending nor completed
     *         200 if it's a valid taskStatus, response is the list of tasks
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable String status) {
        String statusUpper = status.toUpperCase();
        if (!statusUpper.equals("COMPLETED") && !statusUpper.equals("PENDING")) {
            return ResponseEntity.badRequest().build();
        }

        var taskStatus = Task.Status.valueOf(statusUpper);

        return ResponseEntity.ok(repository.findAllByTaskStatus(taskStatus));
    }

    /**
     * Get a specific task by ID.
     * @param id the ID of the task to retrieve
     * @return 404 if the task with the given id does not exist
     *         200 if it exists, responds with the task
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        var task = repository.findById(id);
        if (task.isEmpty()) return ResponseEntity.notFound().build();

        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new task.
     * @param taskJson a map containing the title and description of the task
     * @return 400 if either the title or the description are empty or invalid
     *         201 if it has been successfully created, response is the uri where the newly created task
     *         can be found and the task itself on the response body
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> taskJson) {
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
     * Change the current status of a given task
     * @param id the id of the task to be updated
     * @param status the new status to be assigned
     * @return 404 if the user with the given id does not exist
     *         400 if the sent status is neither completed nor pending
     *         204 if the task status has been successfully updated
     */
    @PutMapping("/{id}/{status}")
    public ResponseEntity<String> changeTaskStatus(@PathVariable Long id , @PathVariable String status) {
        var task = repository.findById(id);

        if (task.isEmpty()) return ResponseEntity.notFound().build();

        status = status.toUpperCase();
        if (!status.equals("COMPLETED") && !status.equals("PENDING")) {
            return ResponseEntity.badRequest().build();
        }

        var taskStatus = Task.Status.valueOf(status);

        task.get().changeStatus(taskStatus);
        repository.save(task.get());

        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a task by ID.
     * @param id the ID of the task to be deleted
     * @return 404 if the task with the given id does not exist
     *         201 if the task has been successfully deleted;
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Task>> deleteTask(@PathVariable Long id) {
        var task = repository.findById(id);
        if (task.isEmpty()) return ResponseEntity.notFound().build();

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

