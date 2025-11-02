package org.beyond.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LocationEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // e.g. 'physical' or 'online', consider looking into ENUM

    private String address; // For physical meetings

    private String url; // For online teams / zoom meetings
}
