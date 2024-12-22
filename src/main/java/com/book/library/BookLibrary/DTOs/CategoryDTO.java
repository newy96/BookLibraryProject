package com.book.library.BookLibrary.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CategoryDTO {

    private Long id;

    @NotBlank
    private String name;

    private List<BookDTO> books;

    public CategoryDTO()
    {
    }

    public CategoryDTO(String name)
    {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}