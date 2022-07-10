package com.example.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/help")
public class HelpController {

    @GetMapping("")
    public String getHelpPage() {
        return "help/help";
    }

    @GetMapping("/registration_help")
    public String getRegistration_helpPage() {
        return "/help/page/registration_help";
    }

    @GetMapping("/add_list")
    public String getAdd_listPage() {
        return "/help/page/add_list";
    }

    @GetMapping("/add_product")
    public String getAdd_productPage() {
        return "/help/page/add_product";
    }

}
