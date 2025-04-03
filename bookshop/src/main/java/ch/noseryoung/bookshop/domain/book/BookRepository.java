package ch.noseryoung.bookshop.domain.book;

import ch.noseryoung.bookshop.domain.book.dto.BookWithoutAuthorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query(value = "SELECT * FROM book WHERE author_id = ?", nativeQuery = true)
    List<Book> findBooksByAuthorId(UUID authorId);

    List<Book> findBookByPriceLessThanEqual(Double maxPrice);

    @Query(value = "SELECT book_id, title, language, price FROM book", nativeQuery = true)
    List<BookWithoutAuthorDTO> findAllBooksWithoutAuthor();

    @Query(value = "SELECT book_id ,title, language, price FROM book WHERE book_id = :bookId", nativeQuery = true)
    BookWithoutAuthorDTO findBookWithoutAuthorById(@Param("bookId") UUID bookId);

    @Query(value = "SELECT book_id ,title, language, price FROM book WHERE price < :maxPrice", nativeQuery = true)
    List<BookWithoutAuthorDTO> findBooksWithoutAuthorByPriceLessThanEqual(@Param("maxPrice") Double maxPrice);
}
