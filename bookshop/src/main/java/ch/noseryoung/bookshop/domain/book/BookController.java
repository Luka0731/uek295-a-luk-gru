package ch.noseryoung.bookshop.domain.book;

import ch.noseryoung.bookshop.domain.author.Author;
import ch.noseryoung.bookshop.domain.author.AuthorService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Hidden
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getProduct(
            @PathVariable UUID id) {
        Book book = bookService.getBook(id);
        return book != null
                ? ResponseEntity.ok(book)
                : ResponseEntity.notFound().build();
    }

}
