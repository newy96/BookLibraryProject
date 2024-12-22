package com.book.library.BookLibrary.controllers;

import com.book.library.BookLibrary.entities.Book;
import com.book.library.BookLibrary.services.BookService;
import com.book.library.BookLibrary.DTOs.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Any books have been created.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/getBook/{isbn}")
    public ResponseEntity getByIsbn(@PathVariable String isbn) {
        Optional<BookDTO> book = bookService.getBookByIsbn(isbn);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There  is no book with ISBN: " + isbn);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(book);
    }

    @PostMapping("/createBook")
    public ResponseEntity createBook(@RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        if (createdBook == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Some information is missing.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody BookDTO updatedBook) {
        Book updated = bookService.updateBook(id, updatedBook);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Some information is missing.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
    }

    @GetMapping("/getByName/{bookName}")
    public ResponseEntity getByName(@PathVariable String bookName) {
        List<BookDTO> books = bookService.getByName(bookName);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There is no book with this name.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(books);
    }

    @GetMapping("/getByAuthorName/{authorName}")
    public ResponseEntity getByAuthor(@PathVariable String authorName) {
        List<BookDTO> books = bookService.getBooksByAuthorName(authorName);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("This author does not have books.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(books);
    }

    @GetMapping("/getByCategory/{categoryName}")
    public ResponseEntity getByCategory(@PathVariable String categoryName) {
        List<BookDTO> books = bookService.getBooksByCategory(categoryName);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are no books in this category.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(books);
    }

    @GetMapping("/getByPublisher/{publisherName}")
    public ResponseEntity getByPublisher(@PathVariable String publisherName) {
        List<BookDTO> books = bookService.getBooksByPublisher(publisherName);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are no books by this publisher.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(books);
    }

}