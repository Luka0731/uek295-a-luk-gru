package ch.noseryoung.bookshop.domain.book;

import jakarta.persistence.*;
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
    private String title;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private Double price;
}
