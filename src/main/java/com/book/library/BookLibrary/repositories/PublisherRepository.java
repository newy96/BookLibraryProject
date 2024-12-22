package com.book.library.BookLibrary.repositories;

import com.book.library.BookLibrary.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Set<Publisher> findByNameIn(Set<String> name);

    @Query("SELECT DISTINCT p FROM Publisher p JOIN p.books b WHERE b.name = :bookName")
    List<Publisher> findAllByBookName(@Param("bookName") String bookName);
}
