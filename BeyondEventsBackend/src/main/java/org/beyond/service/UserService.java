package org.beyond.service;

import org.beyond.model.User;
import org.beyond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserByID(String Id) {
        return userRepository.findByid(Id);
    }

    public User addNewUser(User u)
    {
        return userRepository.save(u);
    }
}
