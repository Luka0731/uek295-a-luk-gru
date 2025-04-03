package ch.noseryoung.bookshop.domain.book;

import ch.noseryoung.bookshop.domain.book.dto.BookCreateDTO;
import ch.noseryoung.bookshop.domain.book.dto.BookUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Boolean withAuthor) {
        return ResponseEntity.ok(bookService.findAllBooks(maxPrice, withAuthor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getProductById(
            @PathVariable UUID id,
            @RequestParam(required = false) Boolean withAuthor) {
        return ResponseEntity.ok(bookService.findBookById(id, withAuthor));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthorId(@PathVariable UUID authorId) {
        return ResponseEntity.ok(bookService.findBookByAuthorId(authorId));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookCreateDTO bookRequestDTO) {
        return ResponseEntity.ok(bookService.createBook(bookRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @Valid @RequestBody BookUpdateDTO bookUpdateDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
