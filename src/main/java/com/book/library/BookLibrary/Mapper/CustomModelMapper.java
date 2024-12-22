package com.book.library.BookLibrary.Mapper;

import com.book.library.BookLibrary.DTOs.*;
import com.book.library.BookLibrary.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomModelMapper extends ModelMapper {

    public <D> D map(Object source, Class<D> destinationType) {
        if (source instanceof Book && destinationType == BookDTO.class) {
            return (D) mapBookToDTOWithNames((Book) source);
        } else if (source instanceof Category && destinationType == CategoryDTO.class) {
            return (D) mapCategoryToDTO((Category) source);
        } else if (source instanceof Author && destinationType == AuthorDTO.class) {
            return (D) mapAuthorToDTO((Author) source);
        } else if (source instanceof Publisher && destinationType == PublisherDTO.class) {
            return (D) mapPublisherToDTO((Publisher) source);
        }
        return super.map(source, destinationType);
    }

    private BookDTO mapBookToDTOWithNames(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setName(book.getName());
        bookDTO.setSerialName(book.getSerialName());
        bookDTO.setDescription(book.getDescription());

        if (book.getAuthor() != null) {
            bookDTO.setAuthor(book.getAuthor().getName());
        }

        if (book.getCategories() != null) {
            List<String> categoryNames = book.getCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.toList());
            bookDTO.setCategories(categoryNames);
        }

        if (book.getPublishers() != null) {
            List<String> publisherNames = book.getPublishers().stream()
                    .map(Publisher::getName)
                    .collect(Collectors.toList());
            bookDTO.setPublishers(publisherNames);
        }

        return bookDTO;
    }

    private CategoryDTO mapCategoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        if (category.getBooks() != null) {
            List<BookDTO> bookDTOs = category.getBooks().stream()
                    .map(this::mapBookToDTOWithNames)
                    .collect(Collectors.toList());
            categoryDTO.setBooks(bookDTOs);
        }

        return categoryDTO;
    }

    private AuthorDTO mapAuthorToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setDescription(author.getDescription());

        if (author.getBooks() != null) {
            List<BookDTO> bookDTOs = author.getBooks().stream()
                    .map(this::mapBookToDTOWithNames)
                    .collect(Collectors.toList());
            authorDTO.setBooks(bookDTOs);
        }

        return authorDTO;
    }

    private PublisherDTO mapPublisherToDTO(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());

        if (publisher.getBooks() != null) {
            List<BookDTO> bookDTOs = publisher.getBooks().stream()
                    .map(this::mapBookToDTOWithNames)
                    .collect(Collectors.toList());
            publisherDTO.setBooks(bookDTOs);
        }

        return publisherDTO;
    }
}