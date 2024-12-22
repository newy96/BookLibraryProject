package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.DTOs.PublisherDTO;
import com.book.library.BookLibrary.DTOs.PublisherInputDTO;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    List<PublisherDTO> getAllPublishers();

    List<PublisherDTO> getAllPublishersByBookName(String bookName);

    Optional<PublisherDTO> getPublisherById(Long id);

    PublisherDTO createPublisher(PublisherInputDTO publisherDTO);

    PublisherDTO updatePublisher(Long id, PublisherInputDTO publisherDTO);

    void deletePublisher(Long id);
}
