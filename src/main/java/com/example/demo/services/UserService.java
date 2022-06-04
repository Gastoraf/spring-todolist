package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repositories.UserRepository;
import freemarker.template.utility.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private MailSenderService mailSenderService;

    public boolean createUser(User user) {
        String userName = user.getName();
        if (userRepository.findByName(userName) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivation_code(UUID.randomUUID().toString());
        log.info("Saving new User with name: {}", userName);
        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
              "Hello, %s \n" +
                      "Welcome to Sweater. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getName(),
                    user.getActivation_code()
            );

            mailSenderService.send(user.getEmail(), "Activation code", message);
        }

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


    public boolean activateUser(String code) {
        User user = userRepository.findByActivation_code(code);
        if (user == null){
            return false;
        }

        user.setActivation_code(null);

        userRepository.save(user);

        return true;

    }
}
