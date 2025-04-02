package ch.noseryoung.bookshop.exception;

import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(UUID notValidId) {
        super("Author with ID " + notValidId + " not found!");
    }
}
