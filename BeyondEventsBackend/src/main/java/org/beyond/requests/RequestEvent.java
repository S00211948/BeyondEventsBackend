package org.beyond.requests;

import java.time.OffsetDateTime;
import java.util.UUID;

public class RequestEvent {
    public String title;

    public String description;

    public OffsetDateTime startTime;

    public OffsetDateTime endTime;

    public UUID organizer_Id;

    public Long category_Id;

    public UUID location_Id;
}
