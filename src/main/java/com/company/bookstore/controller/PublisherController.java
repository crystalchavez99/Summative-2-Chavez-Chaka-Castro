package com.company.bookstore.controller;

import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {
    @Autowired
    PublisherRepository publisherRepo;

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    @GetMapping("/publishers/{id}")
    public Publisher getPublisherById(@PathVariable int id) {
        Optional<Publisher> returnVal = publisherRepo.findById(id);
        return returnVal.orElse(null);
    }

    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        return publisherRepo.findAll();
    }

    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher) {
        publisherRepo.save(publisher);
    }

    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisherById(@PathVariable int id) {
        publisherRepo.deleteById(id);
    }
}
