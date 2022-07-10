package com.example.demo.controller;

import com.example.demo.model.entity.*;
import com.example.demo.model.mapping.ListsFillingMapper;
import com.example.demo.model.mapping.ProductCommentsMapper;
import com.example.demo.services.ProductsService;
import com.example.demo.services.listfilling.ListFillingService;
import com.example.demo.services.listpermission.ListPermissionService;
import com.example.demo.services.mailsender.MailSenderService;
import com.example.demo.services.mylist.MyListService;
import com.example.demo.services.mypermission.MyPermissionService;
import com.example.demo.services.productcomments.ProductCommentsService;
import com.example.demo.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/list")
public class ListController {
    private final ListFillingService listFillingService;
    private final ProductsService productService;
    private final MyListService myListService;
    private final ListPermissionService listPermissionService;
    private final UserService userService;
    private final MyPermissionService myPermissionService;
    private final ProductCommentsService productCommentsService;
    private final MailSenderService mailSenderService;

    private final ListsFillingMapper listsFillingMapper;
    private final ProductCommentsMapper productCommentsMapper;

    @GetMapping("/product/info/{idProduct}")
    public String infoProductById(@PathVariable Long idProduct, Model model, @ModelAttribute MyList myListFromListService) {
        model.addAttribute("product", listsFillingMapper.modelToDto(listFillingService.getListFillingById(idProduct)));
        model.addAttribute("MyList", myListFromListService);
        return "/product";
    }

    @GetMapping("/product/comments/{idProduct}")
    public String commentsProductById(@PathVariable Long idProduct, Model model) {
        model.addAttribute("comments", productCommentsMapper.modelToDto(productCommentsService.findByIdListsFilling(idProduct)) );
        model.addAttribute("product", listsFillingMapper.modelToDto(listFillingService.getListFillingById(idProduct)));
        return "/product-comments";
    }



    @PostMapping("/{idList}/completed/{id}")
    public String completedListFilling(@PathVariable Long idList, @PathVariable Long id) {
        ListsFilling listsFilling = listFillingService.getListFillingById(id);
        listsFilling.setCompleted(!listsFilling.getCompleted());

        listFillingService.saveListFilling(listsFilling);
        return "redirect:/home/" + idList;
    }



    @PostMapping("/update/date/{id}")
    public String updateList(@PathVariable Long id, @ModelAttribute MyList myListParam) {
        MyList myList = myListService.getMyListById(id);
        myList.setDate_of_purchase(myListParam.getDate_of_purchase());
        myListService.saveMyList(myList);

        //Рассылка назначенных дат
        //TODO: создать метод в сервисе mapper->entity->services->entity->dto->front
        mailSenderService.sendScheduledDates(myList, listPermissionService.getUsersListPermissionById(id));


        return "redirect:/home/" + id;
    }

    @PostMapping("/update/product/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ListsFilling product) {
        ListsFilling listsFilling = listFillingService.getListFillingById(id);

        product.setId(id);
        product.setLists(listsFilling.getLists());
        User user = userService.getUserByName(product.getUser().getName());
        product.setUser(user);

        listFillingService.saveListFilling(product);
        return "redirect:/home/" + product.getLists().getId();
    }



    @PostMapping("/add_user/{idList}")
    public String addUserFromList(@RequestParam(name = "name", required = false) String nameUser, @PathVariable Long idList) {

        ListPermission listPermission = new ListPermission();

        listPermission.setUser(userService.getUserByName(nameUser));
        listPermission.setLists(myListService.getMyListById(idList));
        listPermission.setMyPermission(myPermissionService.getMyPermissionById(2L));

        listPermissionService.addListPermission(listPermission);


        return "redirect:/home/" + idList;
    }

    @PostMapping("/{idList}/delete_user/{idUser}")
    public String deleteUserFromList(@PathVariable Long idList, @PathVariable Long idUser) {

        listPermissionService.deleteById_userAndId_list(idUser, idList);

        return "redirect:/home/" + idList;
    }



    @PostMapping("/add/comment/{idProduct}")
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

    @PatchMapping("/update/name/{id}")
    public String updateNameList(@PathVariable Long id, @ModelAttribute MyList myListFromListService) {
        myListService.saveMyList(myListFromListService);
        return "redirect:/home/" + id;
    }

    @DeleteMapping("/{idProduct}/comment/delete/{id}")
    public String deleteCommentsProduct(@PathVariable("idProduct") Long idProduct, @PathVariable("id") Long id) {
        productCommentsService.deleteProductCommentById(id);
        return "redirect:/list/product/comments/" + idProduct;
    }

    @PostMapping("/{idList}/delete/{id}")
    public String deleteListFilling(@PathVariable Long idList, @PathVariable Long id) {
        listFillingService.deleteListFillingById(id);
//        listsFillingMapper.dtoToModel(listFillingService.deleteListFillingById(id));

        return "redirect:/home/" + idList;
    }
}
