package org.beyond.dto;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public class EventDTO {
    public String title;

    public String description;

    public OffsetDateTime startTime;

    public OffsetDateTime endTime;

    public String organizer_Id;

    public Long category_Id;

    public UUID location_Id;

    public Set<String> features;

    public String iconImageUrl;

    public UUID parent_uuid;
}
