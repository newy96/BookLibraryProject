package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.entities.*;
import com.book.library.BookLibrary.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.book.library.BookLibrary.Mapper.Mapper;
import com.book.library.BookLibrary.DTOs.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private Mapper mapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository, Mapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
        this.mapper = mapper;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return mapper.mapBooks(books);
    }

    public Optional<BookDTO> getBookByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        BookDTO bookDTO = mapper.modelMapper.map(book, BookDTO.class);
        return Optional.ofNullable(bookDTO);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book bookToSave = mapper.modelMapper.map(bookDTO, Book.class);

        bookToSave.setCategories(new HashSet<Category>());
        bookToSave.setPublishers(new HashSet<Publisher>());

        Author author = authorRepository.findByName(bookDTO.getAuthor());
        if (author == null) {
            return null;
        }
        bookToSave.setAuthor(author);

        Set<Category> category = categoryRepository.findByNameIn(new HashSet<>(bookDTO.getCategories()));
        if (category == null) {
            return null;
        }
        bookToSave.getCategories().addAll(category);

        Set<Publisher> publisher = publisherRepository.findByNameIn(new HashSet<>(bookDTO.getPublishers()));
        if (publisher == null) {
            return null;
        }
        bookToSave.getPublishers().addAll(publisher);

        Book savedBook = bookRepository.save(bookToSave);

        return mapper.modelMapper.map(savedBook, BookDTO.class);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setName(bookDTO.getName());
        existingBook.setSerialName(bookDTO.getSerialName());
        existingBook.setDescription(bookDTO.getDescription());

        Author author = authorRepository.findByName(bookDTO.getAuthor());
        if (author == null) {
            return null;
        }
        existingBook.setAuthor(author);

        Set<Category> category = categoryRepository.findByNameIn(new HashSet<>(bookDTO.getCategories()));
        if (category == null) {
            return null;
        }
        existingBook.setCategories(category);

        Set<Publisher> publisher = publisherRepository.findByNameIn(new HashSet<>(bookDTO.getPublishers()));
        if (publisher == null) {
            return null;
        }
        existingBook.setPublishers(publisher);

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id){
        var book = bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        bookRepository.deleteById(book.getId());
    }

    public List<BookDTO> getByName(String bookName) {
        List<Book> books = bookRepository.findByName(bookName);
        return mapper.mapBooks(books);
    }

    public List<BookDTO> getBooksByAuthorName(String authorName) {
        List<Book> books = bookRepository.findBooksByAuthorName(authorName);
        return mapper.mapBooks(books);
    }

    public List<BookDTO> getBooksByCategory(String categoryName) {
        List<Book> books = bookRepository.findBooksByCategory(categoryName);
        return mapper.mapBooks(books);
    }

    public List<BookDTO> getBooksByPublisher(String publisherName) {
        List<Book> books = bookRepository.findBooksByPublisher(publisherName);
        return mapper.mapBooks(books);
    }

}
