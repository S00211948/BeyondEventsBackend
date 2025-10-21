package org.beyond.repository;

import org.beyond.model.Vote;
import org.beyond.model.id_objects.VoteID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface VoteRepository extends JpaRepository<Vote, VoteID> {
    Optional<Vote> findByUserID(String id);

    Optional<Vote> findByEventID(UUID id);
}
