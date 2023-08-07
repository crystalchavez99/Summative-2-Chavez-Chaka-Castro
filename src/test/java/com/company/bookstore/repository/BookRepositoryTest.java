package com.company.bookstore.repository;

import com.company.bookstore.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void setUp(){
        bookRepository.deleteAll();

        book = new Book();
       // book.setAuthorId(1);
        book.setPrice(19.99f);
        book.setIsbn("1357924680");
        book.setTitle("Algorithms");
        //book.setPublisherId(1);
        book.setPublishDate("2023-08-05");
        book = bookRepository.save(book);
    }

    @Test
    public void createBook() {

        Optional<Book> book1 = bookRepository.findById(book.getId());

        assertEquals(book1.get(), book);
    }

    @Test
    void findBookByBookId() {
        Optional<Book> book1 = bookRepository.findById(book.getId());
        assertEquals(book1.get(), book);
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

        Optional<Book> book1 = bookRepository.findById(book.getId());
        assertEquals(book1.get(), book);
    }

}
