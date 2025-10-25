package org.beyond.controller;

import java.util.List;

import org.beyond.model.Vote;
import org.beyond.service.EventService;
import org.beyond.service.UserService;
import org.beyond.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "http://localhost:3000")
public class VoteController {

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    VoteService voteService;

    @GetMapping
    public List<Vote> getAllVotesFromDb() {
        return voteService.getAllVotes();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVote(@RequestBody Vote entity) {
        try
        {
            userService.getUserByID(entity.getUserID())
            .orElseThrow(() -> new EntityNotFoundException("Organizer not found"));

            eventService.getEventByID(entity.getEventID())
            .orElseThrow(() -> new EntityNotFoundException("Event not found"));

            if(entity.getValue() > 4 || entity.getValue() < 1)
            {
                throw new IllegalArgumentException("Vote value must be of value 1, 2, or 3");
            }

            voteService.addNewVote(entity);
            return ResponseEntity.ok().body("Success!");
        }
        catch(EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    

}
