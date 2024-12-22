package com.book.library.BookLibrary.DTOs;

public class CategoryInputDTO {

    private String name;

    public CategoryInputDTO() {
    }

    public CategoryInputDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}