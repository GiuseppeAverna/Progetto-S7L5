package Progetto.S7L5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("L'evento con ID: " + id + " non è stato trovato!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
