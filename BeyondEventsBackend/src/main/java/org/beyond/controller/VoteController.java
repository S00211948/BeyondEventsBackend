package org.beyond.controller;

import java.util.List;

import org.beyond.model.Vote;
import org.beyond.service.EventService;
import org.beyond.service.UserService;
import org.beyond.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
