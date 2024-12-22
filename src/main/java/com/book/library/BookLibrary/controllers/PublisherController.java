package com.book.library.BookLibrary.controllers;

import com.book.library.BookLibrary.DTOs.PublisherDTO;
import com.book.library.BookLibrary.DTOs.PublisherInputDTO;
import com.book.library.BookLibrary.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @GetMapping("/byBookName/{bookName}")
    public ResponseEntity<List<PublisherDTO>> getPublishersByBookName(@PathVariable String bookName) {
        List<PublisherDTO> publishers = publisherService.getAllPublishersByBookName(bookName);
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @GetMapping("/getPublisher/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable Long id) {
        Optional<PublisherDTO> publisher = publisherService.getPublisherById(id);
        return publisher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createPublisher")
    public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherInputDTO publisherDTO) {
        PublisherDTO createdPublisher = publisherService.createPublisher(publisherDTO);
        return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
    }

    @PutMapping("/updatePublisher/{id}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable Long id, @RequestBody PublisherInputDTO publisherDTO) {
        PublisherDTO updatedPublisher = publisherService.updatePublisher(id, publisherDTO);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }

    @DeleteMapping("/deletePublisher/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}