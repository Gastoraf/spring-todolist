package com.example.demo.controller;

import com.example.demo.model.MyList;
import com.example.demo.services.MyListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MainController {
    private final MyListService myListService;

    @GetMapping("/main")
    public String getMyLists(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("myLists", myListService.getListByName(name));
        return "main";
    }

    @PostMapping("/main/create")
    public String createProduct(MyList myList) {
        myListService.saveMyList(myList);
        return "redirect:/main";
    }

    @GetMapping("/main/{id}")
    public String listInfo(@PathVariable Long id, Model model) {
        model.addAttribute("myList", myListService.getMyListById(id));
        return "list";
    }
}
