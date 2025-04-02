package ch.noseryoung.bookshop.exception;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(UUID notValidId) {
        super("Book with ID " + notValidId + " not found!");
    }
}
