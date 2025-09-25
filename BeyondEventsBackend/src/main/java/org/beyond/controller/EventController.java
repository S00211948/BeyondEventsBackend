package org.beyond.controller;

import org.beyond.model.Category;
import org.beyond.model.Event;
import org.beyond.service.CategoryService;
import org.beyond.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Event> getAllEventsFromDb() {
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

}
