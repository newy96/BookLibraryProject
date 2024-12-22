package com.book.library.BookLibrary.controllers;

import com.book.library.BookLibrary.entities.Author;
import com.book.library.BookLibrary.services.AuthorService;
import com.book.library.BookLibrary.DTOs.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("getAuthor/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Optional<AuthorDTO> author = authorService.getAuthorById(id);
        return author.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createAuthor")
    public ResponseEntity<AuthorDTO> createAuthor(AuthorInputDTO authorDTO) {
        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PutMapping("updateAuthor/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorInputDTO authorDTO) {
        AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
        if (updatedAuthor != null) {
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteAuthor/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}