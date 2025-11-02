package org.beyond.repository;

import org.beyond.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {
    List<EventEntity> findByTitle(String title);

    Optional<List<EventEntity>> findByParentUuid(UUID parentUuid);

    Optional<EventEntity> findByid(UUID id);

    @Query(value = "SELECT * FROM events WHERE parent_uuid is null",
            nativeQuery = true)
    List<EventEntity> findAllMainEvents();

    //boolean deleteByid(UUID id);
}
