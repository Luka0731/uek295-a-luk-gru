package ch.noseryoung.bookshop.domain.author;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
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
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Country cannot be empty")
    private String country;

    @Column(nullable = false)
    @Past(message = "The birthdate of the author must lie in the past")
    @NotNull(message = "Birthday cannot be null")
    private Date birthday;
}
