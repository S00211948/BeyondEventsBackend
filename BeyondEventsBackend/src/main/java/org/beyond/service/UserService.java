package org.beyond.service;

import org.beyond.model.User;
import org.beyond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByID(String id) {
        return userRepository.findByid(id);
    }

    public User addNewUser(User u) {
        return userRepository.save(u);
    }
}
