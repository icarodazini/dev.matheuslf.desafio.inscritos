package dev.matheuslf.desafio.inscritos.dto;

import dev.matheuslf.desafio.inscritos.enums.TaskStatus;

public class StatusUpdateDTO {
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
