package org.beyond.controller;

import lombok.extern.slf4j.Slf4j;
import org.beyond.dto.EventDTO;
import org.beyond.model.Category;
import org.beyond.model.Event;
import org.beyond.model.Location;
import org.beyond.service.CategoryService;
import org.beyond.service.EventService;
import org.beyond.service.LocationService;
import org.beyond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;



@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    @GetMapping
    public List<Event> getAllEventsFromDb() {
        log.info("Get all events triggered");
        return eventService.getAllEvents();
    }

    @GetMapping("/{title}")
    public List<Event> getEventsByTitleFromDb(@PathVariable String title) {
        return eventService.getAllEventsByTitle(title);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategoriesFromDb() {
        return categoryService.getAllCategories();
    }

    //Add events / categories / location for individual post requests
    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@RequestBody EventDTO entity) {
        try{
            User organizer = userService.getUserByID(entity.organizer_Id)
            .orElseThrow(() -> new EntityNotFoundException("Organizer not found"));

            Location location = locationService.getLocationByID(entity.location_Id)
            .orElseThrow(() -> new EntityNotFoundException("Location not found"));

            Category category = categoryService.getCategoryByID(entity.category_Id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));

            Event newEvent = Event.builder()
            .title(entity.title)
            .description(entity.description)
            .startTime(entity.startTime)
            .endTime(entity.endTime)
            .createdAt(OffsetDateTime.now())
            .organizer(organizer)
            .location(location)
            .category(category)
            .build();

            eventService.addNewEvent(newEvent);
            return ResponseEntity.ok().body("Success!");
        }
        catch(EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody Event entity) {
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
