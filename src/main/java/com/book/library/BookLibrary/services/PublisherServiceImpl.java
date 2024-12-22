package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.entities.Publisher;
import com.book.library.BookLibrary.repositories.PublisherRepository;
import com.book.library.BookLibrary.Mapper.Mapper;
import com.book.library.BookLibrary.DTOs.PublisherDTO;
import com.book.library.BookLibrary.DTOs.PublisherInputDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final Mapper mapper;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, Mapper mapper) {
        this.publisherRepository = publisherRepository;
        this.mapper = mapper;
    }

    public List<PublisherDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return mapper.mapPublishers(publishers);
    }

    public List<PublisherDTO> getAllPublishersByBookName(String bookName) {
        List<Publisher> publishers = publisherRepository.findAllByBookName(bookName);
        return mapper.mapPublishers(publishers);
    }

    public Optional<PublisherDTO> getPublisherById(Long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        PublisherDTO publisherDTO = mapper.modelMapper.map(publisher.orElse(null), PublisherDTO.class);
        return Optional.ofNullable(publisherDTO);
    }

    public PublisherDTO createPublisher(PublisherInputDTO publisherDTO) {
        Publisher publisherToSave = mapper.modelMapper.map(publisherDTO, Publisher.class);
        Publisher savedPublisher = publisherRepository.save(publisherToSave);
        return mapper.modelMapper.map(savedPublisher, PublisherDTO.class);
    }

    public PublisherDTO updatePublisher(Long id, PublisherInputDTO publisherDTO) {
        Publisher existingPublisher = publisherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        existingPublisher.setName(publisherDTO.getName());

        Publisher updatedPublisher = publisherRepository.save(existingPublisher);
        return mapper.modelMapper.map(updatedPublisher, PublisherDTO.class);
    }

    public void deletePublisher(Long id) {
        Publisher existingPublisher = publisherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        publisherRepository.deleteById(existingPublisher.getId());
    }
}
