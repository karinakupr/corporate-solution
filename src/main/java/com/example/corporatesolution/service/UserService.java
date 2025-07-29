package com.example.corporatesolution.service;

import com.example.corporatesolution.model.User;
import com.example.corporatesolution.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User getByCredentials(String username, String password) {
        return userRepository.findByName(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}
