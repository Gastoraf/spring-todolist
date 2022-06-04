package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
public class ListController {
    private final ListFillingService listFillingService;
    private final ProductService productService;
    private final MyListService myListService;
    private final ListPermissionService listPermissionService;
    private final UserService userService;
    private final MyPermissionService myPermissionService;
    private final ProductCommentsService productCommentsService;
    private final MailSenderService mailSenderService;


    @PostMapping("/list/{idList}/delete/{id}")
    public String deleteListFilling(@PathVariable Long idList, @PathVariable Long id) {
        listFillingService.deleteListFillingById(id);
        return "redirect:/home/" + idList;
    }

    @PostMapping("/list/{idList}/completed/{id}")
    public String completedListFilling(@PathVariable Long idList, @PathVariable Long id) {
        ListsFilling listsFilling = listFillingService.getListFillingById(id);
        listsFilling.setCompleted(!listsFilling.getCompleted());

        listFillingService.saveListFilling(listsFilling);
        return "redirect:/home/" + idList;
    }

    @PostMapping("/list/update/name/{id}")
    public String updateNameList(@PathVariable Long id, @ModelAttribute MyList myListFromListService) {
        myListService.saveMyList(myListFromListService);
        return "redirect:/home/" + id;
    }

    @PostMapping("/list/update/date/{id}")
    public String updateList(@PathVariable Long id, @ModelAttribute MyList myListParam) {
        MyList myList = myListService.getMyListById(id);
        myList.setDate_of_purchase(myListParam.getDate_of_purchase());
        myListService.saveMyList(myList);

        //Рассылка назначенных дат
        List<User> users = listPermissionService.getUsersListPermissionById(id);
        for (User user : users) {
            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format(
                        "Привет, %s .\n" +
                                "В вашем списке %s изменилась планируемая дата покупки на %s .",
                        user.getName(),
                        myList.getName(),
                        myList.getDate_of_purchase()
                );

                mailSenderService.send(user.getEmail(), "Изменилась дата", message);
            }
        }


        return "redirect:/home/" + id;
    }

    @PostMapping("/list/update/product/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ListsFilling product, @ModelAttribute MyList myList) {
        ListsFilling listsFilling = listFillingService.getListFillingById(id);
        listsFilling.setName_product(product.getName_product());
        listsFilling.setQuantity(product.getQuantity());
        listsFilling.setUnit(product.getUnit());
        listsFilling.setPrice(product.getPrice());
        listsFilling.setActual_price(product.getActual_price());
        listsFilling.setCurrency(product.getCurrency());
        listsFilling.setCategory(product.getCategory());
        listsFilling.setDescription(product.getDescription());
        User user = userService.getUserByName(product.getUser().getName());
        listsFilling.setUser(user);
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

    @PostMapping("/list/{idList}/delete_user/{idUser}")
    public String deleteUserFromList(@PathVariable Long idList, @PathVariable Long idUser) {

        listPermissionService.deleteById_userAndId_list(idUser, idList);

        return "redirect:/home/" + idList;
    }

    @GetMapping("/list/product/comments/{idProduct}")
    public String commentsProductById(@PathVariable Long idProduct, Model model) {
        model.addAttribute("comments", productCommentsService.findByIdListsFilling(idProduct));
        model.addAttribute("product", listFillingService.getListFillingById(idProduct));
        return "/product-comments";
    }

    @PostMapping("/list/add/comment/{idProduct}")
    public String addCommentsProduct(HttpServletRequest request, @PathVariable Long idProduct, @RequestParam(name = "comment", required = false) String comment, Model model) {
        User user = userService.getUserByName(request.getRemoteUser());
        ListsFilling listsFilling = listFillingService.getListFillingById(idProduct);
        ProductComments productComments = new ProductComments();
        productComments.setComment(comment);
        productComments.setUser(user);
        productComments.setList_filling(listsFilling);
        productCommentsService.saveProductComment(productComments);
        return "redirect:/list/product/comments/" + idProduct;
    }

    @PostMapping("/product/{idProduct}/comment/delete/{id}")
    public String deleteCommentsProduct(@PathVariable Long idProduct, @PathVariable Long id) {
        productCommentsService.deleteProductCommentById(id);
        return "redirect:/list/product/comments/" + idProduct;
    }
}
