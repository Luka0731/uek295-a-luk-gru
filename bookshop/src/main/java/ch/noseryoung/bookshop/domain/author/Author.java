package ch.noseryoung.bookshop.domain.author;

import ch.noseryoung.bookshop.domain.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "author_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Date birthday;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "author_id")
    private Set<Book> products = new LinkedHashSet<>();
}
