package org.beyond.service;

import org.beyond.model.User;
import org.beyond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByID(UUID Id) {
        return userRepository.findByid(Id);
    }

    public User addNewUser(User u)
    {
        return userRepository.save(u);
    }
}
