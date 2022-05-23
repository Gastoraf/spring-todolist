package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String userName = user.getName();
        if (userRepository.findByName(userName) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with email: {}", userName);
        userRepository.save(user);
        return true;
    }

    public User getUserByName(String name){
        if (userRepository.findByName(name) != null){
            return userRepository.findByName(name);
        }
        return null;
    }

    public User getUserById(Long id){
        userRepository.getById(id);
        return userRepository.getById(id);
    }
}
