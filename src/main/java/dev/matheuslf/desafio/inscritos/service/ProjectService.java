package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.model.Project;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Project criateProject(Project project) {
        return projectRepository.save(project);
    }
}
