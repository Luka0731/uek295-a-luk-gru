package ch.noseryoung.bookshop.domain.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book getBook(UUID id) {
        return repository.findById(id).orElse(null);
    }

}
