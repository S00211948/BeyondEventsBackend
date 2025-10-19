package org.beyond.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar", updatable = false, nullable = false)
    private String id;

    @Column(unique = true)
    private String email;

    private String displayName;
    
    @Column(name = "created_at", nullable = false, updatable = false,
            columnDefinition = "timestamp with time zone default now()")
    private OffsetDateTime createdAt;

}