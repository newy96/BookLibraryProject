package com.book.library.BookLibrary.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

public class BookDTO {
    @NotBlank
    private String isbn;

    @NotBlank
    private String name;

    @NotBlank
    private String serialName;

    @NotBlank
    private String description;

    @NotBlank
    private String author;

    @NotEmpty
    private List<String> categories;

    @NotEmpty
    private List<String> publishers;

    public BookDTO() {
    }

    public BookDTO(String isbn, String name, String serialName, String description, String authorName, List<String> categoryName, List<String> publisherName){
        this.isbn = isbn;
        this.name = name;
        this.serialName = serialName;
        this.description = description;
        this.author = authorName;
        this.categories = categoryName;
        this.publishers = publisherName;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSerialName() { return serialName; }

    public void setSerialName(String serialName) { this.serialName = serialName; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public List<String> getCategories() { return categories; }

    public void setCategories(List<String> category) { this.categories = category; }

    public List<String> getPublishers() { return publishers; }

    public void setPublishers(List<String> publisher) { this.publishers = publisher; }
}