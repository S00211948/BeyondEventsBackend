package org.beyond.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.beyond.dto.EventDTO;
import org.beyond.model.CategoryEntity;
import org.beyond.model.EventEntity;
import org.beyond.model.UserEntity;
import org.beyond.service.CategoryService;
import org.beyond.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<EventEntity> getAllEventsFromDb() {
        log.info("Get all events triggered");
        return eventService.getAllEvents();
    }

    @GetMapping("/main")
    public List<EventEntity> getAllMainEventsFromDb() {
        log.info("Get all main events triggered");
        return eventService.getAllMainEvents();
    }

    @GetMapping("/sub-events/{parentUuid}")
    public Optional<List<EventEntity>> getAllEventsByParentEventFromDb(@PathVariable UUID parentUuid) {
        return eventService.getAllEventsByParentUuid(parentUuid);
    }

    @GetMapping("/{title}")
    public List<EventEntity> getEventsByTitleFromDb(@PathVariable String title) {
        return eventService.getAllEventsByTitle(title);
    }

    @GetMapping("/categories")
    public List<CategoryEntity> getAllCategoriesFromDb() {
        return categoryService.getAllCategories();
    }

    //Add events / categories / location for individual post requests
    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@RequestBody EventDTO eventDTO) {
        try {
            EventEntity eventEntity = eventService.addNewEvent(eventDTO);
            return ResponseEntity.ok().body(eventEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody EventEntity entity) {
        if (eventService.updateEvent(entity) != null) {
            return "Success!";
        } else {
            return "Error: No such Event";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteByID(@PathVariable("id") UUID id) {
        if (eventService.getEventByID(id) != null) {
            eventService.deleteByID(id);
            if (eventService.getEventByID(id) == null) {
                return "Success!";
            } else {
                return "Failed to delete.";
            }
        } else {
            return "No such Event";
        }
    }
}
