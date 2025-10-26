package org.beyond.service;

import lombok.extern.slf4j.Slf4j;
import org.beyond.model.Event;
import org.beyond.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        log.info("Returned events: {}", events);

        return events;
    }

    public List<Event> getAllMainEvents() {
        List<Event> events = eventRepository.findAllMainEvents();
        log.info("Returned main events: {}", events);
        return events;
    }

    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    public List<Event> getAllEventsByParentUuid(UUID parentUuid) {
        return eventRepository.findByParentUuid(parentUuid);
    }

    public Event getEventByID(UUID id) {
        return eventRepository.findByid(id);
    }

    public Event addNewEvent(Event e) {
        return eventRepository.save(e);
    }

    public void deleteByID(UUID id) {
        eventRepository.deleteById(id);
    }

    public Event updateEvent(Event e) //throws NoSuchObjectException
    {
        Event existing = getEventByID(e.getId());
        if (existing != null) {
            existing.setTitle(e.getTitle());
            existing.setDescription(e.getDescription());
            existing.setStartTime(e.getStartTime());
            existing.setEndTime(e.getEndTime());
            existing.setCategory(e.getCategory());
            existing.setLocation(e.getLocation());
            existing.setOrganizer(e.getOrganizer());

            return eventRepository.save(existing);
        }
        //throw new NoSuchObjectException("Employee not exist with id: " + e.getId());
        else return null;
    }
}
