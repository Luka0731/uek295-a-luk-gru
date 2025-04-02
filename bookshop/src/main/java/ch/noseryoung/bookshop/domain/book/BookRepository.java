package ch.noseryoung.bookshop.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query(value = "SELECT * FROM book WHERE author_id = ?", nativeQuery = true)
    List<Book> findBookByAuthorId(UUID authorId);

    List<Book> findBookByPriceLessThanEqual(Double maxPrice);

    @Query(value = "SELECT book_id ,title, language, price FROM book", nativeQuery = true)
    List<Book> findAllBooksWithoutAuthor();

    @Query(value = "SELECT book_id ,title, language, price FROM book WHERE book_id = ?", nativeQuery = true)
    List<Book> findBookWithoutAuthorById(UUID bookId);
}
