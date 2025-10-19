package org.beyond.model;

import java.util.UUID;

import org.beyond.model.id_objects.VoteID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "votes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@IdClass(VoteID.class)
public class Vote {
    @Id
    private String userID;

    @Id
    private UUID eventID;

    private int value;
}
