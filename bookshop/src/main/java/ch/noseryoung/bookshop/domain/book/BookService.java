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
        if (maxPrice != null && maxPrice < 0) {
            throw new IllegalArgumentException("maxPrice cannot be negative");
        }
        if (maxPrice == null && (withAuthor == null || withAuthor)) {
            return bookRepository.findAll();
        }
        if (maxPrice == null && !withAuthor) {
            return bookRepository.findAllBooksWithoutAuthor();
        }
        if (!withAuthor) {
            return bookRepository.findBookWithoutAuthorByPriceLessThanEqual(maxPrice);
        }
        return bookRepository.findBookByPriceLessThanEqual(maxPrice);
    }

    public Book findBookById(UUID bookId, Boolean withAuthor) {
        if (bookRepository.existsById(bookId)) {
            if (withAuthor == null || !withAuthor) {
                return bookRepository.findBookWithoutAuthorById(bookId);
            }
            return bookRepository.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException(bookId));
        } else {
            throw new BookNotFoundException(bookId);
        }
        
    }

    public List<Book> findBookByAuthorId(UUID authorId) {
        authorService.findAuthorById(authorId);
        return bookRepository.findBookByAuthorId(authorId);
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
