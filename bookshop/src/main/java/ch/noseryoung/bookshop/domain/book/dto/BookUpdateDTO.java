package ch.noseryoung.bookshop.domain.book.dto;

import java.util.UUID;

public record BookUpdateDTO(UUID id, String title, String language, Double price, UUID authorId) {
}
