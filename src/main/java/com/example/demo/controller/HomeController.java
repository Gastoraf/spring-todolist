package com.example.demo.controller;

import com.example.demo.model.dto.listFilling.CreateListsFillingDto;
import com.example.demo.model.entity.ListPermission;
import com.example.demo.model.entity.ListsFilling;
import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.User;
import com.example.demo.model.dto.products.ProductsDto;
import com.example.demo.model.mapping.ListsFillingMapper;
import com.example.demo.model.mapping.ProductsMapper;
import com.example.demo.service.*;
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
    private final ProductsService productsService;
    private final ListPermissionService listPermissionService;
    private final UserService userService;
    private final MyPermissionService myPermissionService;
    private final ModelService modelService;

    private final ProductsMapper productsMapper;
    private final ListsFillingMapper listsFillingMapper;

    @GetMapping("")
    public String getMyLists(Model model, HttpServletRequest request) {
        modelService.getMyLists(model, myListService.findByIdUser(userService.getUserByName(request.getRemoteUser()).getId()));
        return "/home/home";
    }

    @GetMapping("/user/{id}")
    public ProductsDto getMyLists(@PathVariable Integer id) {
        return productsMapper.toDto(productsService.getProductById(id));
    }

    @GetMapping("/search")
    public String getMyListByName(@RequestParam(name = "name", required = false) String name, Model model, HttpServletRequest request) {
        modelService.getMyLists(model, myListService.getListByName(name, userService.getUserByName(request.getRemoteUser()).getId()));
        return "/home/home";
    }


    @GetMapping("/{id}")
    public String listInfoById(@PathVariable Long id, Model model) {
        modelService.getInfoMyList(model, id);
        return "home/list/list";
    }


    @PostMapping("/create")
    public String createList(MyList myList, HttpServletRequest request) {
        myListService.saveMyList(myList);
        listPermissionService.addListPermission(myList, request);

        return "redirect:/home";
    }


    @PostMapping("/add/{id}")
    public String addListFilling(@PathVariable Long id, @ModelAttribute CreateListsFillingDto createListsFillingDto, HttpServletRequest request) {

        listFillingService.saveListFilling(id, createListsFillingDto, request);

        return "redirect:/home/" + id;
    }


    @DeleteMapping("/delete/{id}")
    public String deleteList(@PathVariable Long id) {
        myListService.deleteMyListById(id);
        return "redirect:/home";
    }


}
