package com.example.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HelpContoller {

    @GetMapping("/help")
    public String getHelpPage() {
        return "help/help";
    }

    @GetMapping("/help/registration_help")
    public String getRegistration_helpPage() {
        return "/help/page/registration_help";
    }

    @GetMapping("/help/add_list")
    public String getAdd_listPage() {
        return "/help/page/add_list";
    }

    @GetMapping("/help/add_product")
    public String getAdd_productPage() {
        return "/help/page/add_product";
    }

}
