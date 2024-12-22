package com.book.library.BookLibrary.repositories;

import com.book.library.BookLibrary.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findByNameIn(Set<String> name);

    @Query("SELECT DISTINCT c FROM Category c JOIN c.books b WHERE b.name = :bookName")
    List<Category> findAllByBookName(@Param("bookName") String bookName);

}
