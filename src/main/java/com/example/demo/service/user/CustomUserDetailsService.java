package com.example.demo.service.user;

import com.example.demo.exception.AccessRuntimeException;
import com.example.demo.model.entity.User;
import com.example.demo.repositorie.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name).orElseThrow(() -> new AccessRuntimeException(new RedirectView("/login", true),
                "Не удалось найти пользователя.",
                HttpStatus.NOT_FOUND));

        log.info("loadUserByUsername {}", user.getActivation_code());
        if (user.getActivation_code() != null) {
            throw new AccessRuntimeException("Не удалось войти. Требуется активировать почту.",
                    HttpStatus.NOT_FOUND, new RedirectView("/login", true));
        }
        return user;
    }


}
