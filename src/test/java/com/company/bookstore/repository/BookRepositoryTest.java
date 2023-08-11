package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    private Book book;
    private Author author;
    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

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

        publisher = new Publisher();
        publisher.setName("Test Publisher");
        publisher.setStreet("123 Test Street");
        publisher.setCity("Test City");
        publisher.setState("TS");
        publisher.setPostalCode("00000");
        publisher.setPhone("555-6789");
        publisher.setEmail("email@gmail.com");
        publisher = publisherRepository.save(publisher);

        book = new Book();
        LocalDate date = LocalDate.of(2020,1,8);
        BigDecimal decimal = new BigDecimal("24.99");
        MathContext mc = new MathContext(4);

        book.setAuthorId(author.getId());
        book.setIsbn("12345678");
        book.setPrice(decimal.round(mc));
        book.setTitle("Testing Books");
        book.setPublisherId(publisher.getId());
        book.setPublishDate(date);

        book = bookRepository.save(book);
    }

    @Test
    void createBook(){
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertEquals(book2.get(), book);
    }

    @Test
    void findBookByBookId() {
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertEquals(book2.get(), book);
    }

    @Test
    void findAllBooks(){
        List<Book> books = bookRepository.findAll();
        assertEquals(1,books.size());
    }

    @Test
    void updateBook(){
        book.setTitle("UPDATED");

        bookRepository.save(book);

        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertEquals(book2.get(), book);
    }

    @Test
    void deleteBookById(){
        bookRepository.deleteById(book.getId());

        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertFalse(book2.isPresent());
    }
    @Test
    void findBooksByAuthorId() {
        List<Book> books = bookRepository.findByAuthorId(book.getAuthorId());
        assertEquals(1, books.size());
    }
}