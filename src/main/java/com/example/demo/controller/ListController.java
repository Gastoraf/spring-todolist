package com.example.demo.controller;

import com.example.demo.model.ListPermission;
import com.example.demo.model.ListsFilling;
import com.example.demo.model.MyList;
import com.example.demo.model.Product;
import com.example.demo.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ListController {
    private final ListFillingService listFillingService;
    private final ProductService productService;
    private final MyListService myListService;
    private final ListPermissionService listPermissionService;
    private final UserService userService;
    private final MyPermissionService myPermissionService;


    @PostMapping("/list/{idList}/delete/{id}")
    public String deleteListFilling(@PathVariable Long idList, @PathVariable Long id) {
        listFillingService.deleteListFillingById(id);
        return "redirect:/home/" + idList;
    }

    @PostMapping("/list/update/{id}")
    public String updateList(@PathVariable Long id, @ModelAttribute MyList myListFromListService) {
        myListService.saveMyList(myListFromListService);
        return "redirect:/home/" + id;
    }

    @PostMapping("/list/update/product/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ListsFilling product, @ModelAttribute MyList myList) {
        ListsFilling listsFilling = listFillingService.getListFillingById(id);
        listsFilling.setName_product(product.getName_product());
        listsFilling.setQuantity(product.getQuantity());
        listsFilling.setUnit(product.getUnit());
        listsFilling.setPrice(product.getPrice());
        listsFilling.setCurrency(product.getCurrency());
        listsFilling.setDescription(product.getDescription());
        listsFilling.setCompleted(product.getCompleted());
        listFillingService.saveListFilling(listsFilling);
        return "redirect:/home/" + listsFilling.getLists().getId();
    }

    @GetMapping("/list/product/info/{idProduct}")
    public String infoProductById(@PathVariable Long idProduct, Model model, @ModelAttribute MyList myListFromListService) {
        model.addAttribute("product", listFillingService.getListFillingById(idProduct));
        model.addAttribute("MyList", myListFromListService);
        return "/product";
    }

    @PostMapping("/list/add_user/{idList}")
    public String addUserFromList(@RequestParam(name = "name", required = false) String nameUser, @PathVariable Long idList) {

        ListPermission listPermission = new ListPermission();

        listPermission.setUser(userService.getUserByName(nameUser));
        listPermission.setLists(myListService.getMyListById(idList));
        listPermission.setMyPermission(myPermissionService.getMyPermissionById(2L));

        listPermissionService.addListPermission(listPermission);


        return "redirect:/home/" + idList;
    }
}
