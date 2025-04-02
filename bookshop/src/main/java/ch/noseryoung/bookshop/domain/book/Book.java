package ch.noseryoung.bookshop.domain.book;

import ch.noseryoung.bookshop.domain.author.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_id")
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Language cannot be empty")
    private String language;

    @Column(nullable = false)
    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price cant be negative")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    @NotNull(message = "AuthorId cannot be null")
    private Author author;
}
