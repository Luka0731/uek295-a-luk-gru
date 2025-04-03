package ch.noseryoung.bookshop.domain.author;

import ch.noseryoung.bookshop.exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(UUID bookId) {
        return authorRepository.findById(bookId)
                .orElseThrow(() -> new AuthorNotFoundException(bookId));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(UUID authorId, Author authorDetails) {
        Author author = authorRepository.findById(authorDetails.getId())
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        author.setName(authorDetails.getName());
        author.setCountry(authorDetails.getCountry());
        author.setBirthday(authorDetails.getBirthday());
        return authorRepository.save(author);
    }

    public void deleteAuthor(UUID authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new AuthorNotFoundException(authorId);
        }
        authorRepository.deleteById(authorId);
    }
}