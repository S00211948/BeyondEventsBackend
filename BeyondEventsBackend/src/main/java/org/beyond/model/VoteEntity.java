package org.beyond.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beyond.model.id_objects.VoteID;

import java.util.UUID;

@Entity
@Table(name = "votes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@IdClass(VoteID.class)
public class VoteEntity {
    @Id
    private String userID;

    @Id
    private UUID eventID;

    private int value;
}
