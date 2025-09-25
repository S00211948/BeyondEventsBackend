package org.beyond.service;

import org.beyond.model.Event;
import org.beyond.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }
}
