package org.beyond.service;

import org.beyond.model.VoteEntity;
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

    public List<VoteEntity> getAllVotes() {
        return voteRepository.findAll();
    }

    public Optional<VoteEntity> getVoteByUserID(String id) {
        return voteRepository.findByUserID(id);
    }

    public Optional<VoteEntity> getVoteByEventID(UUID id) {
        return voteRepository.findByEventID(id);
    }

    public VoteEntity addNewVote(VoteEntity u) {
        return voteRepository.save(u);
    }

    public void organizeEvents()
    {
        List<VoteEntity> votes = getAllVotes();
        
    }
}
