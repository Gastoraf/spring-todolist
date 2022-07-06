package com.example.demo.controller;

import com.example.demo.model.ListPermission;
import com.example.demo.model.ListsFilling;
import com.example.demo.model.MyList;
import com.example.demo.model.User;
import com.example.demo.model.dto.ProductsDto;
import com.example.demo.model.mapping.ProductsMapper;
import com.example.demo.services.*;
import com.example.demo.services.listpermission.ListPermissionService;
import com.example.demo.services.mylist.MyListService;
import com.example.demo.services.mypermission.MyPermissionService;
import com.example.demo.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.services.listfilling.ListFillingService;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MyListService myListService;
    private final ListFillingService listFillingService;
    private final ProductsService productsService;
    private final ListPermissionService listPermissionService;
    private final UserService userService;
    private final MyPermissionService myPermissionService;

    private final ProductsMapper productsMapper;

    @GetMapping("/home")
    public String getMyLists(@RequestParam(name = "name", required = false) String name, Model model, HttpServletRequest request) {
        User user = userService.getUserByName(request.getRemoteUser());
        model.addAttribute("myLists", myListService.findByIdUser(user.getId()));
        System.out.println("dfssdf: {}" + request.getRemoteUser());
        return "home";
    }

    @GetMapping("/home/user/{id}")
    public ProductsDto getMyLists(@PathVariable Integer id) {
        return productsMapper.toDto(productsService.getProductById(id));
    }

    @GetMapping("/home/search")
    public String getMyListByName(@RequestParam(name = "name", required = false) String name, Model model, HttpServletRequest request) {
        User user = userService.getUserByName(request.getRemoteUser());
        model.addAttribute("myLists", myListService.getListByName(name, user.getId()));
        System.out.println("dfssdf: {}" + request.getRemoteUser());
        return "home";
    }


    @PostMapping("/home/create")
    public String createList(MyList myList, HttpServletRequest request) {
        User user = userService.getUserByName(request.getRemoteUser());
        myListService.saveMyList(myList);
        ListPermission listPermission = new ListPermission();
        listPermission.setLists(myList);
        listPermission.setUser(user);
        listPermission.setMyPermission(myPermissionService.getMyPermissionById(1L));
        listPermissionService.addListPermission(listPermission);
        return "redirect:/home";
    }

    @PostMapping("/home/delete/{id}")
    public String deleteList(@PathVariable Long id) {
        myListService.deleteMyListById(id);
        return "redirect:/home";
    }

    @GetMapping("/home/{id}")
    public String listInfoById(@PathVariable Long id, Model model) {
        if ((long) listFillingService.getListFillingByIdListCompletedTrue(id).size() == 0) {
            model.addAttribute("myListFromFillingServiceCompletedTrue", 0);
        } else {
            model.addAttribute("myListFromFillingServiceCompletedTrue", listFillingService.getListFillingByIdListCompletedTrue(id));
        }
        if ((long) listFillingService.getListFillingByIdListCompletedFalse(id).size() == 0) {
            model.addAttribute("myListFromFillingServiceCompletedFalse", 0);
        } else {
            model.addAttribute("myListFromFillingServiceCompletedFalse", listFillingService.getListFillingByIdListCompletedFalse(id));
        }
        model.addAttribute("usersListPermission", listPermissionService.getUsersListPermissionById(id));
        model.addAttribute("myListFromListService", myListService.getMyListById(id));
        model.addAttribute("myListFilling", new ListsFilling());
        //Расчет стоимости
        model.addAttribute("ToBuy", listFillingService.getToBuyMyListById(id));
        model.addAttribute("Purchased", listFillingService.getPurchasedMyListById(id));
        model.addAttribute("ActualPurchased", listFillingService.getActualPurchasedMyListById(id));
        model.addAttribute("PurchasedBuyer", listFillingService.getPurchasedBuyerByIdList(id));
        //
        model.addAttribute("newUser", new User());
        return "list";
    }

    @PostMapping("/home/add/{id}")
    public String addListFilling(@PathVariable Long id, @ModelAttribute ListsFilling listsFilling, HttpServletRequest request) {
        User user = userService.getUserByName(request.getRemoteUser());
        listsFilling.setUser(user);
        listsFilling.setId(null);
        listsFilling.setCompleted(false);

        listsFilling.setLists(myListService.getMyListById(id));
        listFillingService.saveListFilling(listsFilling);

        return "redirect:/home/" + id;
    }


    @GetMapping("/")
    public String getHomePage() {
        return "main";
    }


}
