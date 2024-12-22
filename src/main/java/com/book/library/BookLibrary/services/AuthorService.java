package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.DTOs.*;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    Optional<AuthorDTO> getAuthorById(Long id);
    AuthorDTO createAuthor(AuthorInputDTO authorDTO);
    AuthorDTO updateAuthor(Long id, AuthorInputDTO authorDTO);
    void deleteAuthor(Long id);
}
