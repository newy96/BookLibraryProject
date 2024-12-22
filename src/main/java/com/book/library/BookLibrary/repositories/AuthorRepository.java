package com.book.library.BookLibrary.repositories;

import com.book.library.BookLibrary.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);
}
