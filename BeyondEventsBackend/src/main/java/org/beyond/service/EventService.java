package org.beyond.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.beyond.dto.EventDTO;
import org.beyond.model.CategoryEntity;
import org.beyond.model.EventEntity;
import org.beyond.model.LocationEntity;
import org.beyond.model.UserEntity;
import org.beyond.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    public List<EventEntity> getAllEvents() {
        List<EventEntity> eventEntities = eventRepository.findAll();
        log.info("Returned events: {}", eventEntities);

        return eventEntities;
    }

    public List<EventEntity> getAllMainEvents() {
        List<EventEntity> eventEntities = eventRepository.findAllMainEvents();
        log.info("Returned main events: {}", eventEntities);
        return eventEntities;
    }

    public List<EventEntity> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    public Optional<List<EventEntity>> getAllEventsByParentUuid(UUID parentUuid) {
        return eventRepository.findByParentUuid(parentUuid);
    }

    public Optional<EventEntity> getEventByID(UUID id) {
        return eventRepository.findByid(id);
    }

    public EventEntity addNewEvent(EventDTO eventDto) {
        UserEntity organizer = userService.getUserByID(eventDto.organizer_Id)
                .orElseThrow(() -> new EntityNotFoundException("Organizer not found"));

        LocationEntity locationEntity = locationService.getLocationByID(eventDto.location_Id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));

        CategoryEntity categoryEntity = categoryService.getCategoryByID(eventDto.category_Id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        EventEntity newEventEntity = EventEntity.builder()
                .title(eventDto.title)
                .description(eventDto.description)
                .startTime(eventDto.startTime)
                .endTime(eventDto.endTime)
                .createdAt(OffsetDateTime.now())
                .organizer(organizer)
                .locationEntity(locationEntity)
                .categoryEntity(categoryEntity)
                .organizationFeatures(eventDto.features)
                .iconImageUrl(eventDto.iconImageUrl)
                .parentUuid(eventDto.parent_uuid)
                .build();

        return eventRepository.save(newEventEntity);
    }

    public void deleteByID(UUID id) {
        eventRepository.deleteById(id);
    }

    public EventEntity updateEvent(EventEntity e) //throws NoSuchObjectException
    {
        EventEntity existing = getEventByID(e.getId())
        .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        if (existing != null) {
            existing.setTitle(e.getTitle());
            existing.setDescription(e.getDescription());
            existing.setStartTime(e.getStartTime());
            existing.setEndTime(e.getEndTime());
            existing.setCategoryEntity(e.getCategoryEntity());
            existing.setLocationEntity(e.getLocationEntity());
            existing.setOrganizer(e.getOrganizer());

            return eventRepository.save(existing);
        }
        //throw new NoSuchObjectException("Employee not exist with id: " + e.getId());
        else return null;
    }
}
