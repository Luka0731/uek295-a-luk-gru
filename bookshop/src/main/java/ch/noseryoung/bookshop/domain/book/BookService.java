package ch.noseryoung.bookshop.domain.book;

import ch.noseryoung.bookshop.domain.author.Author;
import ch.noseryoung.bookshop.domain.author.AuthorService;
import ch.noseryoung.bookshop.domain.book.dto.BookCreateDTO;
import ch.noseryoung.bookshop.domain.book.dto.BookUpdateDTO;
import ch.noseryoung.bookshop.domain.book.dto.BookWithoutAuthorDTO;
import ch.noseryoung.bookshop.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired private BookRepository bookRepository;
    @Autowired private AuthorService authorService;

    public List<Book> findAllBooks(Double maxPrice, Boolean withAuthor) {
        if (withAuthor == null) { withAuthor = true; }

        if (maxPrice != null && maxPrice < 0) {
            throw new IllegalArgumentException("maxPrice cannot be negative");
        }
        if (maxPrice == null && withAuthor) {
            return bookRepository.findAll();
        }
        if (maxPrice == null && !withAuthor) {
            return bookRepository.findAllBooksWithoutAuthor().stream().map(bwa ->
                    new Book(bwa.id(), bwa.title(), bwa.language(), bwa.price(), null)).toList();
        }
        if (!withAuthor) {
            return bookRepository.findBooksWithoutAuthorByPriceLessThanEqual(maxPrice).stream().map(bwo ->
                    new Book(bwo.id(), bwo.title(), bwo.language(), bwo.price(), null)).toList();
        }
        return bookRepository.findBookByPriceLessThanEqual(maxPrice);
    }

    public Book findBookById(UUID bookId, Boolean withAuthor) {
        if (withAuthor == null || withAuthor) {
            return bookRepository.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException(bookId));
        }
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        BookWithoutAuthorDTO bwa = bookRepository.findBookWithoutAuthorById(bookId);
        return new Book(bwa.id(), bwa.title(), bwa.language(), bwa.price(), null);
    }

    public List<Book> findBookByAuthorId(UUID authorId) {
        authorService.findAuthorById(authorId);
        return bookRepository.findBooksByAuthorId(authorId);
    }

    public Book createBook(BookCreateDTO bookRequestDTO) {
        Author author = authorService.findAuthorById(bookRequestDTO.authorId());
        Book book = new Book();
        book.setTitle(bookRequestDTO.title());
        book.setLanguage(bookRequestDTO.language());
        book.setPrice(bookRequestDTO.price());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book updateBook(UUID bookId, BookUpdateDTO bookUpdateDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        Author author = authorService.findAuthorById(bookUpdateDTO.authorId());
        book.setId(bookUpdateDTO.id());
        book.setTitle(bookUpdateDTO.title());
        book.setLanguage(bookUpdateDTO.language());
        book.setPrice(bookUpdateDTO.price());
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
