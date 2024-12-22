package com.book.library.BookLibrary.DTOs;

public class PublisherInputDTO {

    private String name;

    public PublisherInputDTO() {
    }

    public PublisherInputDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}