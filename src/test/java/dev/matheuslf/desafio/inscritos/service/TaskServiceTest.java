package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.enums.TaskStatus;
import dev.matheuslf.desafio.inscritos.exceptions.TaskNotFoundException;
import dev.matheuslf.desafio.inscritos.model.Project;
import dev.matheuslf.desafio.inscritos.model.Task;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TaskService taskService;

    private Project project;
    private Task task;
    private final Long idValido = 1L;
    private final Long idInvalido = 74L;

    @BeforeEach
    public void setUp() {
        project = new Project("Test Project", null);
        project.setId(idValido);

        task = new Task();
        task.setId(idValido);
        task.setTitle("Tarefa Mock");
    }

    @Test
    @DisplayName("Teste de criação de tarefa com ID de projeto válido")
    public void createTask_ValidProjectID_ReturnsSavedTask() {
        when(projectRepository.findById(idValido)).thenReturn(Optional.of(project));

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.createTask(task, idValido);

        Assertions.assertNotNull(result);

        verify(taskRepository, times(1)).save(task);
    }

    @Test
    @DisplayName("Teste de criação de tarefa com ID de projeto inválido")
    public void createTask_InvalidProjectId_ThrowsNotFoundException() {
        when(projectRepository.findById(idInvalido)).thenReturn(Optional.empty());

        Assertions.assertThrows(TaskNotFoundException.class, () -> {

            taskService.createTask(task, idInvalido);
        });

        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    @DisplayName("Teste de atualização de status de tarefa com ID válido")
    public void updateTaskStatus_ValidId_ReturnUpdateTask() {
        Task existingTask = new Task();
        existingTask.setId(idValido);
        existingTask.setStatus(TaskStatus.TODO);

        when(taskRepository.findById(idValido)).thenReturn(Optional.of(existingTask));

        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TaskStatus newStatus = TaskStatus.DONE;

        Task result = taskService.updateTaskStatus(idValido, newStatus);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(newStatus, result.getStatus());
        verify(taskRepository, times(1)).save(existingTask);
    }
}