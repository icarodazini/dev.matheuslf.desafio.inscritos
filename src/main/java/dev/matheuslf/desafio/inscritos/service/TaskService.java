package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.enums.TaskPriority;
import dev.matheuslf.desafio.inscritos.enums.TaskStatus;
import dev.matheuslf.desafio.inscritos.exceptions.TaskNotFoundException;
import dev.matheuslf.desafio.inscritos.exceptions.ValidationException;
import dev.matheuslf.desafio.inscritos.model.Project;
import dev.matheuslf.desafio.inscritos.model.Task;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repository.TaskRepository;
import dev.matheuslf.desafio.inscritos.specification.TaskSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Task createTask(Task task, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ValidationException::new);

        task.setProject(project);
        project.getTasks().add(task);

        projectRepository.save(project);

        return task;
    }

    public Page<Task> findAll(TaskStatus status, TaskPriority priority, Long projectId, Pageable pageable) {
        TaskSpecification spec = new TaskSpecification(status, priority, projectId);

        return taskRepository.findAll(spec, pageable);
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        System.out.println("Status recebido: " + status);
        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        task.setStatus(status);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException();
        }
        taskRepository.deleteById(id);
    }
}
