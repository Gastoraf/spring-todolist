package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/security/login";
    }


    @GetMapping("/registration")
    public String registration() {
        return "/security/registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)){
            model.addAttribute("messageDanger", "Такой логин уже существует");
            return "/security/registration";
        }
        model.addAttribute("messageSuccess", "Пожалуйста зайдите к себе на почту и подтвердите email");
        return "/security/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if (isActivated){
            model.addAttribute("messageSuccess", "Почта успешно активирована");
        } else {
            model.addAttribute("messageDanger", "Почту не получилось активировать");
        }

        return "/security/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }


}