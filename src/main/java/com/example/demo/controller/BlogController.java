package com.example.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BlogController {

    @GetMapping("/blog")
    public String getBlogPage() {
        return "/blog/blog";
    }

    @GetMapping("/blog/quarantine")
    public String getQuarantinePage() {
        return "/blog/page/quarantine";
    }

    @GetMapping("/blog/reasons-why-sharing-shopping-list-makes-life-better")
    public String getShoppingListPage() {
        return "/blog/page/reasons-why-sharing-shopping-list-makes-life-better";
    }

    @GetMapping("/blog/kitchen-motivation")
    public String getMainKitchenPage() {
        return "/blog/page/kitchen-motivation";
    }

}
