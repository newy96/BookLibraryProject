package com.book.library.BookLibrary.Mapper;

import org.springframework.stereotype.Component;
import com.book.library.BookLibrary.DTOs.BookDTO;
import com.book.library.BookLibrary.entities.Book;
import com.book.library.BookLibrary.DTOs.PublisherDTO;
import com.book.library.BookLibrary.entities.Publisher;
import com.book.library.BookLibrary.DTOs.CategoryDTO;
import com.book.library.BookLibrary.entities.Category;
import com.book.library.BookLibrary.DTOs.AuthorDTO;
import com.book.library.BookLibrary.entities.Author;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public final CustomModelMapper modelMapper;


    public Mapper(CustomModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<BookDTO> mapBooks(List<Book> books) {
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    public List<AuthorDTO> mapAuthors(List<Author> authors) {
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> mapCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public List<PublisherDTO> mapPublishers(List<Publisher> publishers) {
        return publishers.stream()
                .map(publisher -> modelMapper.map(publisher, PublisherDTO.class))
                .collect(Collectors.toList());
    }
}
