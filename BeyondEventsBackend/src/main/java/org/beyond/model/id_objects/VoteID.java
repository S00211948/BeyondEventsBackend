package org.beyond.model.id_objects;

import java.io.Serializable;
import java.util.UUID;

public class VoteID implements Serializable
{
    private String userID;

    private UUID eventID;

    public VoteID(String _userID,UUID _eventID)
    {
        this.userID = _userID;
        this.eventID = _eventID;
    }
}
