package com.example.demo.controller;

import com.example.demo.model.MyList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class HelpContoller {

    @GetMapping("/help")
    public String getMainHelpPage() {
        return "help";
    }

    @GetMapping("/help/registration_help")
    public String getHelp1Page() {
        return "/help/registration_help";
    }

}
