package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    public void setUp() {
        authorRepository.deleteAll();

        author = new Author();
        author.setFirstName("Emily");
        author.setLastName("Bronte");
        author.setStreet("Market Street");
        author.setCity("Thornton");
        author.setState("CA");
        author.setPostalCode("00000");
        author.setPhone("000-123-4567");
        author.setEmail("wuthering@gmail.com");

        author = authorRepository.save(author);
    }

    @Test
    void createAuthor(){
        Optional<Author> author2 = authorRepository.findById(author.getId());
        assertEquals(author2.get(), author);
    }

    @Test
    void findAuthorById() {
        Optional<Author> author2 = authorRepository.findById(author.getId());
        assertEquals(author2.get(), author);
    }

    @Test
    void findAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        assertEquals(1,authors.size());
    }

    @Test
    void updateAuthor(){
        author.setFirstName("UPDATED");

        authorRepository.save(author);

        Optional<Author> author2 = authorRepository.findById(author.getId());
        assertEquals(author2.get(), author);
    }

    @Test
    void deleteBookById(){
        authorRepository.deleteById(author.getId());

        Optional<Author> author2 = authorRepository.findById(author.getId());
        assertFalse(author2.isPresent());
    }
}
