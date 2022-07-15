package com.example.demo.service.user;

import com.example.demo.exception.AccessRuntimeException;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repositorie.UserRepository;
import com.example.demo.service.mailSender.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public boolean createUser(User user) {
        String userName = user.getName();
        if (userRepository.findByName(userName) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivation_code(UUID.randomUUID().toString());
        log.info("Saving new User with name: {}", userName);
        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "%s, добро пожаловать в Listcery. Пожалуйста, подтвердите Ваш аккаунт перейдя по следующей ссылке: http://localhost:8080/activate/%s",
                    user.getName(),
                    user.getActivation_code()
            );

            mailSenderService.send(user.getEmail(), "Listcery: подтвердите почту", message);
        }

        return true;
    }

    @Override
    public User getUserByName(String name) {
            return userRepository.findByName(name).orElseThrow(() -> new AccessRuntimeException("Не удалось найти.",
                    HttpStatus.NOT_FOUND));
    }

    @Override
    public User getUserById(Long id) {
        userRepository.getById(id);
        return userRepository.getById(id);
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findAll().stream().filter(userB -> code.equals(userB.getActivation_code()))
                .findAny()
                .orElse(null);
        if (user == null) {
            return false;
        }

        user.setActivation_code(null);

        userRepository.save(user);

        return true;

    }
}
