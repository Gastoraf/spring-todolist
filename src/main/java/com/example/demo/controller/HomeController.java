package com.example.demo.controller;

import com.example.demo.model.entity.MyList;
import com.example.demo.model.mapping.ListsFillingMapper;
import com.example.demo.service.listPermission.ListPermissionService;
import com.example.demo.service.model.ModelService;
import com.example.demo.service.myList.MyListService;
import com.example.demo.service.myPermission.MyPermissionService;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.service.listFilling.ListFillingService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final MyListService myListService;
    private final ListFillingService listFillingService;
    private final ListPermissionService listPermissionService;
    private final UserService userService;
    private final MyPermissionService myPermissionService;
    private final ModelService modelService;

    private final ListsFillingMapper listsFillingMapper;

    @GetMapping("")
    public String getMyLists(Model model, HttpServletRequest request) {
        modelService.getMyLists(model, myListService.findByIdUser(userService.getUserByName(request.getRemoteUser()).getId()));
        return "/home/home";
    }

    @GetMapping("/search")
    public String getMyListByName(@RequestParam(name = "name", required = false) String name, Model model, HttpServletRequest request) {
        modelService.getMyLists(model, myListService.getListByName(name, userService.getUserByName(request.getRemoteUser()).getId()));
        return "/home/home";
    }

    @PostMapping("/create")
    public String createList(MyList myList, HttpServletRequest request) {
        myListService.saveMyList(myList);
        listPermissionService.addListPermission(myList, request);

        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteList(@PathVariable Long id) {
        myListService.deleteMyListById(id);
        return "redirect:/home";
    }

}
