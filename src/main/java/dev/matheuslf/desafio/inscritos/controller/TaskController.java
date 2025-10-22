package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.dto.TaskCreateDTO;
import dev.matheuslf.desafio.inscritos.enums.TaskPriority;
import dev.matheuslf.desafio.inscritos.enums.TaskStatus;
import dev.matheuslf.desafio.inscritos.model.Task;
import dev.matheuslf.desafio.inscritos.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        Task newTask = new Task();
        newTask.setTitle(taskCreateDTO.getTitle());
        newTask.setDescription(taskCreateDTO.getDescription());
        newTask.setPriority(taskCreateDTO.getPriority());

        Task saveTask = taskService.createTask(newTask, taskCreateDTO.getProjectId());

        return new ResponseEntity<>(saveTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(required = false) Long projectId,

            Pageable pageable) {

        Page<Task> tasks = taskService.findAll(status, priority, projectId, pageable);

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        Task updatedTask = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(updatedTask);
    }
}
