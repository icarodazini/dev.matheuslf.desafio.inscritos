package dev.matheuslf.desafio.inscritos.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        super("Dados inválidos.");
    }

    public ValidationException(String message) {
        super(message);
    }
}
