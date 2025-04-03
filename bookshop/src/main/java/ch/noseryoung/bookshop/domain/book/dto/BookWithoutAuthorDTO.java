package ch.noseryoung.bookshop.domain.book.dto;

import java.util.UUID;

public record BookWithoutAuthorDTO(UUID id, String title, String language, Double price) {
}
