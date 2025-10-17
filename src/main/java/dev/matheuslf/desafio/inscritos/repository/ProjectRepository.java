package dev.matheuslf.desafio.inscritos.repository;

import dev.matheuslf.desafio.inscritos.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
