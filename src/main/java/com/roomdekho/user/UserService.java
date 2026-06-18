package com.roomdekho.user;

import com.roomdekho.exception.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        return userRepository.save(user);
    }
}