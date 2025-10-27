package org.beyond.service;

import org.beyond.model.UserEntity;
import org.beyond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<UserEntity> getUserByID(String Id) {
        return userRepository.findByid(Id);
    }

    public UserEntity addNewUser(UserEntity u) {
        return userRepository.save(u);
    }
}
