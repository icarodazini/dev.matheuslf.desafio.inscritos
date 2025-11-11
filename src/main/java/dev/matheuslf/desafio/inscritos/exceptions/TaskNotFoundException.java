package dev.matheuslf.desafio.inscritos.exceptions;


public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("Recurso não encontrado.");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
