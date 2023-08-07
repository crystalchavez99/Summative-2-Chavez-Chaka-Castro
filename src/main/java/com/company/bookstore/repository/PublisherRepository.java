package com.company.bookstore.repository;

import com.company.bookstore.models.Publisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherRepository {

    public PublisherRepository() {
        seedDataStore();
    }

    private List<Publisher> publishers = new ArrayList<>();

    private void seedDataStore() {
        Publisher p1 = new Publisher();
        p1.setName("Publisher 1");
        p1.setStreet("Street 1");
        p1.setCity("City 1");
        p1.setState("State 1");
        p1.setPostalCode("12345");
        p1.setPhone("1234567890");
        p1.setEmail("publisher1@example.com");

        Publisher p2 = new Publisher();
        p2.setName("Publisher 2");
        p2.setStreet("Street 2");
        p2.setCity("City 2");
        p2.setState("State 2");
        p2.setPostalCode("67890");
        p2.setPhone("9876543210");
        p2.setEmail("publisher2@example.com");

        publishers.add(p1);
        publishers.add(p2);
    }

    // Create a new publisher
    public Publisher addPublisher(Publisher publisher) {
        publishers.add(publisher);
        return publisher;
    }

    // Get all publishers
    public List<Publisher> getAllPublishers() {
        return publishers;
    }

    // Get publisher by id
    public Publisher getPublisherById(String id) {
        for (Publisher publisher : publishers) {
            if (publisher.getName().equals(id)) {
                return publisher;
            }
        }
        return null;
    }

    // Update a publisher
    public Publisher updatePublisher(Publisher publisher) {
        int index = findPublisherIndexById(publisher.getName());
        if (index != -1) {
            publishers.set(index, publisher);
            return publisher;
        }
        return null;
    }

    // Delete publisher by id
    public boolean deletePublisherById(String id) {
        int index = findPublisherIndexById(id);
        if (index != -1) {
            publishers.remove(index);
            return true;
        }
        return false;
    }

    private int findPublisherIndexById(String id) {
        for (int i = 0; i < publishers.size(); i++) {
            if (publishers.get(i).getName().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    
}
