package ch.noseryoung.bookshop.domain.book.dto;

import java.util.UUID;

public record BookCreateDTO(String title, String language, Double price, UUID authorId) {
}
