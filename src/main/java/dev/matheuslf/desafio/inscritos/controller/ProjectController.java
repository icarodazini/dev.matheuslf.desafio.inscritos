package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.dto.ProjectCreateDTO;
import dev.matheuslf.desafio.inscritos.model.Project;
import dev.matheuslf.desafio.inscritos.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        Project newProject = new Project();
        newProject.setName(projectCreateDTO.getName());
        newProject.setDescription(projectCreateDTO.getDescription());
        newProject.setStartDate(projectCreateDTO.getStartDate());
        newProject.setEndDate(projectCreateDTO.getEndDate());

        Project saveProduct = projectService.createProject(newProject);

        return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<java.util.List<Project>> getAllProjects() {
        List<Project> projects = projectService.findAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
