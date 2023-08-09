package com.company.bookstore.repository;

import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository publisherRepository;

    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        publisher = new Publisher();
        publisher.setName("Test Publisher");
        publisher.setStreet("123 Test Street");
        publisher.setCity("Test City");
        publisher.setState("Test State");
        publisher.setPostalCode("00000");
        publisher.setPhone("555-6789");
        publisher.setEmail("email@gmail.com");

        publisher = publisherRepository.save(publisher);
    }

    @Test
    void createPublisher() {
        Optional<Publisher> publisher2 = publisherRepository.findById(publisher.getId());
        assertTrue(publisher2.isPresent());
        assertEquals(publisher2.get(), publisher);
    }

    @Test
    void findPublisherByPublisherId() {
        Optional<Publisher> publisher2 = publisherRepository.findById(publisher.getId());
        assertTrue(publisher2.isPresent());
        assertEquals(publisher2.get(), publisher);
    }

    @Test
    void findAllPublishers() {
        Iterable<Publisher> publishers = publisherRepository.findAll();
        assertNotNull(publishers);
        assertEquals(1, ((List<Publisher>) publishers).size());
    }

    @Test
    void updatePublisher() {
        publisher.setName("Updated Publisher");

        publisherRepository.save(publisher);

        Optional<Publisher> publisher2 = publisherRepository.findById(publisher.getId());
        assertTrue(publisher2.isPresent());
        assertEquals(publisher2.get(), publisher);
    }

    @Test
    void deletePublisherById() {
        publisherRepository.deleteById(publisher.getId());

        Optional<Publisher> publisher2 = publisherRepository.findById(publisher.getId());
        assertFalse(publisher2.isPresent());
    }
}
