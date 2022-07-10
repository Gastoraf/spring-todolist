package com.example.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    @GetMapping("")
    public String getBlogPage() {
        return "/blog/blog";
    }

    @GetMapping("/quarantine")
    public String getQuarantinePage() {
        return "/blog/page/quarantine";
    }

    @GetMapping("/reasons-why-sharing-shopping-list-makes-life-better")
    public String getShoppingListPage() {
        return "/blog/page/reasons-why-sharing-shopping-list-makes-life-better";
    }

    @GetMapping("/kitchen-motivation")
    public String getMainKitchenPage() {
        return "/blog/page/kitchen-motivation";
    }

}
