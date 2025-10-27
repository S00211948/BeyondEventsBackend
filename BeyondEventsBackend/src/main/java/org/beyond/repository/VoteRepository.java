package org.beyond.repository;

import org.beyond.model.VoteEntity;
import org.beyond.model.id_objects.VoteID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, VoteID> {
    Optional<VoteEntity> findByUserID(String id);

    Optional<VoteEntity> findByEventID(UUID id);
}
