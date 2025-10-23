package org.beyond.service;

import org.beyond.model.Vote;
import org.beyond.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    public List<Vote> getAllVotes(){
        return voteRepository.findAll();
    }

    public Optional<Vote> getVoteByUserID(String Id) {
        return voteRepository.findByUserID(Id);
    }

    public Optional<Vote> getVoteByEventID(UUID Id) {
        return voteRepository.findByEventID(Id);
    }

    public Vote addNewVote(Vote u)
    {
        return voteRepository.save(u);
    }
}
