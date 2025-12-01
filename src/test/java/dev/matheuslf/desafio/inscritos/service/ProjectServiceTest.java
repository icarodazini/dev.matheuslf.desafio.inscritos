package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.model.Project;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project("Test Project", null);
        project.setId(1L);
    }

    @Test
    @DisplayName("Deve criar e retornar um projeto quando o save for bem-sucedido")
    void createProject_ShouldReturnSavedProject() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project savedProject = projectService.createProject(project);

        Assertions.assertNotNull(savedProject, "O projeto salvo não deve ser nulo.");
        Assertions.assertEquals("Test Project", savedProject.getName());

        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void findAllProjects() {

    }
}