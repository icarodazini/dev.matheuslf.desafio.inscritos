package dev.matheuslf.desafio.inscritos.repository;

import dev.matheuslf.desafio.inscritos.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
