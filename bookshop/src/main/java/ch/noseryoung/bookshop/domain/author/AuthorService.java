package ch.noseryoung.bookshop.domain.author;

import ch.noseryoung.bookshop.domain.book.Book;
import ch.noseryoung.bookshop.domain.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public List<Author> getAuthors() {
        return repository.findAll();
    }

    public Author getAuthor (UUID id) {
        return repository.findById(id).orElse(null);
    }

}
