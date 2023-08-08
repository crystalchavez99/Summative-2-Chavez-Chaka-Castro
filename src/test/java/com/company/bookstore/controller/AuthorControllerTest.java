package com.company.bookstore.controller;

import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private MockMvc mockMvc;

    private Author author;
    ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setUp() throws Exception{
        author = new Author();
        author.setFirstName("Emily");
        author.setLastName("Brontë");
        author.setStreet("Market Street");
        author.setCity("Thornton");
        author.setState("Yorkshire");
        author.setPostalCode("00000");
        author.setPhone("000-123-4567");
        author.setEmail("wutheringheights@gmail.com");
    }

    @Test
    void addAuthor() throws Exception {
        String input = mapper.writeValueAsString(author);

        mockMvc.perform(post("/authors")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getAuthorById() throws Exception {
        mockMvc.perform(get("/authors/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateAuthor() throws Exception {
        author.setFirstName("UPDATED");
        String inputJson = mapper.writeValueAsString(author);

        mockMvc.perform(put("/authors")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAuthorById() throws Exception {
        mockMvc.perform(delete("/authors/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
