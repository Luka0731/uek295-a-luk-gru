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

    public Author updateAuthor(UUID id, Author authorDetails) {
        Author author = findAuthorById(id);
        author.setName(authorDetails.getName());
        author.setCountry(authorDetails.getCountry());
        author.setBirthday(authorDetails.getBirthday());
        return authorRepository.save(author);
    }

    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }
}