package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.entities.Author;
import com.book.library.BookLibrary.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.book.library.BookLibrary.Mapper.Mapper;
import com.book.library.BookLibrary.DTOs.*;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private Mapper mapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, Mapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return mapper.mapAuthors(authors);
    }

    public Optional<AuthorDTO> getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        AuthorDTO authorDTO = mapper.modelMapper.map(author, AuthorDTO.class);
        return Optional.ofNullable(authorDTO);
    }

    public AuthorDTO createAuthor(AuthorInputDTO authorDTO) {
        Author authorToSave = mapper.modelMapper.map(authorDTO, Author.class);
        Author savedAuthor = authorRepository.save(authorToSave);
        return mapper.modelMapper.map(savedAuthor, AuthorDTO.class);
    }

    public AuthorDTO updateAuthor(Long id, AuthorInputDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        existingAuthor.setName(authorDTO.getName());
        existingAuthor.setDescription(authorDTO.getDescription());

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return mapper.modelMapper.map(updatedAuthor, AuthorDTO.class);
    }

    public void deleteAuthor(Long id) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        authorRepository.deleteById(existingAuthor.getId());
    }

}