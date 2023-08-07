package com.company.bookstore.controller;

import com.company.bookstore.models.Book;
import com.company.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(BookController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;



    private Book book;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception{
        book = new Book();
        //book.setAuthorId(1);
        book.setPrice(19.99f);
        book.setIsbn("1357924680");
        book.setTitle("Algorithms");
        //book.setPublisherId(1);
        book.setPublishDate("2023-08-05");
    }

    @Test
    void addBook() throws Exception{
        String input = mapper.writeValueAsString(book);

        mockMvc.perform(post("/books")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getBookById() throws Exception{
        mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateBook() throws Exception{
        book.setPrice(24.99f);
        String input = mapper.writeValueAsString(book);
        mockMvc.perform(put("/books")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBookById() throws Exception{
        mockMvc.perform(delete("/books/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void getBooksByAuthor() throws Exception{
        mockMvc.perform(get("/books/author/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
