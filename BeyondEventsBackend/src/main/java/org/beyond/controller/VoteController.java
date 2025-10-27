package org.beyond.controller;

import org.beyond.model.VoteEntity;
import org.beyond.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "http://localhost:3000")
public class VoteController {

    @Autowired
    VoteService voteService;

    @GetMapping
    public List<VoteEntity> getAllVotesFromDb() {
        return voteService.getAllVotes();
    }

}
