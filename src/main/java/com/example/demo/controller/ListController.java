package com.example.demo.controller;

import com.example.demo.model.entity.*;
import com.example.demo.model.mapping.ListsFillingMapper;
import com.example.demo.model.mapping.ProductCommentsMapper;
import com.example.demo.service.ProductsService;
import com.example.demo.service.listFilling.ListFillingService;
import com.example.demo.service.listPermission.ListPermissionService;
import com.example.demo.service.mailSender.MailSenderService;
import com.example.demo.service.model.ModelService;
import com.example.demo.service.myList.MyListService;
import com.example.demo.service.myPermission.MyPermissionService;
import com.example.demo.service.productComments.ProductCommentsService;
import com.example.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/list")
@Slf4j
public class ListController {
    private final ListFillingService listFillingService;
    private final ProductsService productService;
    private final MyListService myListService;
    private final ListPermissionService listPermissionService;
    private final UserService userService;
    private final MyPermissionService myPermissionService;
    private final ProductCommentsService productCommentsService;
    private final MailSenderService mailSenderService;
    private final ModelService modelService;

    private final ListsFillingMapper listsFillingMapper;
    private final ProductCommentsMapper productCommentsMapper;

    @GetMapping("/product/info/{idProduct}")
    public String infoProductById(@PathVariable Long idProduct, Model model, @ModelAttribute MyList myList) {
        modelService.addAttributeInfoProduct(model, listsFillingMapper.modelToDto(listFillingService.getListFillingById(idProduct)), myList);
        return "/home/list/info/product";
    }

    @GetMapping("/product/comments/{idProduct}")
    public String commentsProductById(@PathVariable Long idProduct, Model model) {
        modelService.addAttributeCommentsProduct(model, productCommentsMapper.modelToDto(productCommentsService.findByIdListsFilling(idProduct)), listsFillingMapper.modelToDto(listFillingService.getListFillingById(idProduct)));
        return "/home/list/comments/product-comments";
    }


    @PostMapping("/{idList}/completed/{id}")
    public String updateCompletedListFilling(@PathVariable Long idList, @PathVariable Long id) {

        //TODO: вывести пользователю "что-то пошло не так"
        log.info("updateCompletedListFilling: {}", listFillingService.updateCompletedById(id));

        return "redirect:/home/" + idList;
    }



    @PostMapping("/update/date/{id}")
    public String updateList(@PathVariable Long id, @ModelAttribute String newDate) {
        myListService.updateDate(id, newDate);

        //Рассылка назначенных дат
        //TODO: создать метод в сервисе mapper->entity->services->entity->dto->front
        //TODO: создать исключение
        mailSenderService.sendScheduledDates(myListService.getMyListById(id), listPermissionService.getUsersListPermissionById(id));

        return "redirect:/home/" + id;
    }

    @PostMapping("/update/product/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ListsFilling updateListsFilling) {
        //TODO: создать исключение

        log.info("updateProduct: {}", listFillingService.updateListFilling(id, updateListsFilling));
        return "redirect:/home/" + updateListsFilling.getLists().getId();
    }



    @PostMapping("/add_user/{idList}")
    public String addUserFromList(@RequestParam(name = "name", required = false) String nameUser, @PathVariable Long idList) {
        //TODO: создать исключение

        listPermissionService.addListPermission(nameUser, idList);


        return "redirect:/home/" + idList;
    }

    @PostMapping("/{idList}/delete_user/{idUser}")
    public String deleteUserFromList(@PathVariable Long idList, @PathVariable Long idUser) {

        listPermissionService.deleteById_userAndId_list(idUser, idList);

        return "redirect:/home/" + idList;
    }



    @PostMapping("/add/comment/{idProduct}")
    public String addCommentsProduct(HttpServletRequest request, @PathVariable Long idProduct, @RequestParam(name = "comment", required = false) String comment) {
        //TODO: вынести все в сервис

//        productCommentsService.saveProductComment(productComments);
        productCommentsService.saveProductComment(request, idProduct, comment);
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

    @DeleteMapping("/{idList}/delete/{id}")
    public String deleteListFilling(@PathVariable Long idList, @PathVariable Long id) {
        listFillingService.deleteListFillingById(id);

        return "redirect:/home/" + idList;
    }
}
