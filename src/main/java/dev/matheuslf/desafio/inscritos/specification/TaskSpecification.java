package dev.matheuslf.desafio.inscritos.specification;

import dev.matheuslf.desafio.inscritos.enums.TaskPriority;
import dev.matheuslf.desafio.inscritos.enums.TaskStatus;
import dev.matheuslf.desafio.inscritos.model.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class TaskSpecification implements Specification<Task> {

    private TaskStatus status;
    private TaskPriority priority;
    private Long projectId;

    public TaskSpecification(TaskStatus status, TaskPriority priority, Long projectId) {
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new java.util.ArrayList<>();

        if (status != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }

        if (priority != null) {
            predicates.add(criteriaBuilder.equal(root.get("priority"), priority));
        }

        if (projectId != null) {
            predicates.add(criteriaBuilder.equal(root.get("project").get("id"), projectId));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
