package org.beyond.repository;

import org.beyond.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByTitle(String title);

    List<Event> findByParentUuid(UUID parentUuid);

    Event findByid(UUID id);

    //boolean deleteByid(UUID id);
}
