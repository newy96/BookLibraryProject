package com.book.library.BookLibrary.repositories;

import com.book.library.BookLibrary.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);

    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.name = :authorName")
    List<Book> findBooksByAuthorName(@Param("authorName") String authorName);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.name = :categoryName")
    List<Book> findBooksByCategory(@Param("categoryName") String categoryName);

    @Query("SELECT b FROM Book b JOIN b.publishers p WHERE p.name = :publisherName")
    List<Book> findBooksByPublisher(@Param("publisherName") String publisherName);

}

