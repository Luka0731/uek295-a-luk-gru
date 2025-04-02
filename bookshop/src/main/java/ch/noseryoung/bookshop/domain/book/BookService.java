package ch.noseryoung.bookshop.domain.book;

import ch.noseryoung.bookshop.domain.author.Author;
import ch.noseryoung.bookshop.domain.author.AuthorService;
import ch.noseryoung.bookshop.domain.book.dto.BookRequestDTO;
import ch.noseryoung.bookshop.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired private BookRepository bookRepository;
    @Autowired private AuthorService authorService; // TODO: make to authorService

    public List<Book> findAllBooks(Double maxPrice, Boolean withAuthor) {
        if (maxPrice == null) {
            if (withAuthor|| withAuthor == null) { return bookRepository.findAll(); }
            else { return bookRepository.findAllBooksWithoutAuthor(); }

        }
        if (maxPrice < 0) { throw new IllegalArgumentException("maxPrice cannot be negative"); }
        return bookRepository.findBookByPriceLessThanEqual(maxPrice);
    }


    public Book finBookById(UUID bookId, Boolean withAuthor) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public List<Book> findBookByAuthorId(UUID authorId) {
        authorService.findAuthorById(authorId);
        return bookRepository.findBookByAuthorId(authorId);
    }

    public List<Book> findAllBooksWithoutAuthor() {
        return bookRepository.findAll();
    }

    public Book findBookWithoutAuthorById(UUID bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public List<Book> findAllBooksByMaxPrice(Double maxPrice) {
        if (maxPrice != null) {
            return bookRepository.findBookByPriceLessThanEqual(maxPrice);
        }
        return bookRepository.findAll();
    }

    public Book createBook(BookRequestDTO bookRequestDTO) {
        Author author = authorService.findAuthorById(bookRequestDTO.authorId());
        Book book = new Book();
        book.setTitle(bookRequestDTO.title());
        book.setLanguage(bookRequestDTO.language());
        book.setPrice(bookRequestDTO.price());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book updateBook(UUID bookId, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        Author author = authorService.findAuthorById(bookRequestDTO.authorId());
        book.setTitle(bookRequestDTO.title());
        book.setLanguage(bookRequestDTO.language());
        book.setPrice(bookRequestDTO.price());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public void deleteBook(UUID bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        bookRepository.deleteById(bookId);
    }
}