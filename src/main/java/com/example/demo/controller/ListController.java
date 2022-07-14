package com.example.demo.controller;

import com.example.demo.model.dto.listFilling.CreateListsFillingDto;
import com.example.demo.model.entity.*;
import com.example.demo.model.mapping.ListsFillingMapper;
import com.example.demo.model.mapping.ProductCommentsMapper;
import com.example.demo.service.listFilling.ListFillingService;
import com.example.demo.service.listPermission.ListPermissionService;
import com.example.demo.service.mailSender.MailSenderService;
import com.example.demo.service.model.ModelService;
import com.example.demo.service.myList.MyListService;
import com.example.demo.service.myPermission.MyPermissionService;
import com.example.demo.service.productComments.ProductCommentsService;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/list")
@Slf4j
public class ListController {
    private final ListFillingService listFillingService;
    private final MyListService myListService;
    private final ListPermissionService listPermissionService;
    private final UserService userService;
    private final MyPermissionService myPermissionService;
    private final ProductCommentsService productCommentsService;
    private final MailSenderService mailSenderService;
    private final ModelService modelService;

    private final ListsFillingMapper listsFillingMapper;
    private final ProductCommentsMapper productCommentsMapper;

    @GetMapping("/{id}")
    public String listInfoById(@PathVariable Long id, Model model, @ModelAttribute("getMessage") String eMessage) {
        modelService.getInfoMyList(model, id);
        modelService.getMessageException(model, eMessage);
        return "home/list/list";
    }

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

    @PostMapping("/add/{id}")
    public String addListFilling(@PathVariable Long id, @ModelAttribute CreateListsFillingDto createListsFillingDto, HttpServletRequest request) {

        listFillingService.saveListFilling(id, createListsFillingDto, request);

        return "redirect:/list/" + id;
    }


    @PostMapping("/{idList}/completed/{id}")
    public RedirectView updateCompletedListFilling(@PathVariable Long idList, @PathVariable Long id, final RedirectAttributes redirectAttributes) {

        log.info("update Completed List Filling: {}", id);

        return listFillingService.updateCompletedById(idList, id, redirectAttributes, new RedirectView("/list/" + idList, true));

    }


    @PostMapping("/update/date/{id}")
    public String updateList(@PathVariable Long id, @ModelAttribute String newDate) {
        myListService.updateDate(id, newDate);

        //Рассылка назначенных дат
        //TODO: создать метод в сервисе mapper->entity->services->entity->dto->front
        //TODO: создать исключение
        mailSenderService.sendScheduledDates(myListService.getMyListById(id), listPermissionService.getUsersListPermissionById(id));

        return "redirect:/list/" + id;
    }

    @PostMapping("/update/product/{id}")
    public RedirectView updateProduct(@PathVariable Long id, final RedirectAttributes redirectAttributes, @ModelAttribute ListsFilling updateListsFilling) {
        //TODO: создать исключение

        log.info("updateProduct: {}", id);
        return listFillingService.updateListFilling(id, updateListsFilling, new RedirectView("/list/" + updateListsFilling.getLists().getId(), true));
    }


    @PostMapping("/add_user/{idList}")
    public String addUserFromList(@RequestParam(name = "name", required = false) String nameUser, @PathVariable Long idList) {
        //TODO: создать исключение

        listPermissionService.addListPermission(nameUser, idList);


        return "redirect:/list/" + idList;
    }

    @PostMapping("/{idList}/delete_user/{idUser}")
    public String deleteUserFromList(@PathVariable Long idList, @PathVariable Long idUser) {

        listPermissionService.deleteById_userAndId_list(idUser, idList);

        return "redirect:/list/" + idList;
    }


    @PostMapping("/add/comment/{idProduct}")
    public String addCommentsProduct(HttpServletRequest request, @PathVariable Long idProduct, @RequestParam(name = "comment", required = false) String comment) {
        //TODO: вынести все в сервис

//        productCommentsService.saveProductComment(productComments);
        productCommentsService.saveProductComment(request, idProduct, comment);
        return "redirect:/list/product/comments/" + idProduct;
    }

    @PatchMapping("/update/name/{id}")
    public String patchingNameList(@PathVariable Long id, @ModelAttribute MyList myListFromListService) {
        myListService.saveMyList(myListFromListService);
        return "redirect:/list/" + id;
    }

    @DeleteMapping("/{idProduct}/comment/delete/{id}")
    public String deleteCommentsProduct(@PathVariable("idProduct") Long idProduct, @PathVariable("id") Long id) {
        productCommentsService.deleteProductCommentById(id);
        return "redirect:/list/product/comments/" + idProduct;
    }

    @DeleteMapping("/{idList}/delete/{id}")
    public RedirectView deleteListFilling(@PathVariable Long idList, @PathVariable Long id, final RedirectAttributes redirectAttributes) {
        return listFillingService.deleteListFillingById(idList, id, new RedirectView("/list/" + idList, true));
    }
}
