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

import java.util.List;

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
    @DisplayName("Deve retornar todos os projetos encontrados pelo repositório")
    void findAllProjects_ShouldReturnAllProjects() {

        Project project2 = new Project("Segundo Projeto", null);
        project2.setId(2L);
        List<Project> expectedProjects = List.of(project, project2);

        when(projectRepository.findAll()).thenReturn(expectedProjects);

        List<Project> result = projectService.findAllProjects();

        Assertions.assertNotNull(result, "A lista de projetos não deve ser nula.");
        Assertions.assertEquals(2, result.size(), "A lista deve retornar dois projetos.");
        Assertions.assertEquals(expectedProjects, result, "Os projetos esperados devem estar na lista.");

        verify(projectRepository, times(1)).findAll();
    }
}