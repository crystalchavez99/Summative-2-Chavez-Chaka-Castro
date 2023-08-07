package com.company.bookstore.controller;

import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @MockBean
    private PublisherRepository publisherRepository;

    @Autowired
    private MockMvc mockMvc;

    private Publisher publisher;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Test Publisher");
        // Set other properties...

        when(publisherRepository.findById(1)).thenReturn(Optional.of(publisher));
        when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher);
    }

    @Test
    void addPublisher() throws Exception {
        String input = mapper.writeValueAsString(publisher);

        mockMvc.perform(post("/publishers")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(publisherRepository, times(1)).save(any(Publisher.class));
    }

    @Test
    void getPublisherById() throws Exception {
        mockMvc.perform(get("/publishers/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPublishers() throws Exception {
        mockMvc.perform(get("/publishers"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePublisher() throws Exception {
        String inputJson = mapper.writeValueAsString(publisher);

        mockMvc.perform(put("/publishers")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(publisherRepository, times(1)).save(any(Publisher.class));
    }

    @Test
    void deletePublisherById() throws Exception {
        mockMvc.perform(delete("/publishers/1"))
                .andExpect(status().isNoContent());

        verify(publisherRepository, times(1)).deleteById(1);
    }
}

