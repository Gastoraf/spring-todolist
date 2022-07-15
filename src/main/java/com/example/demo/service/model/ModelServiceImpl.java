package com.example.demo.service.model;

import com.example.demo.model.dto.listFilling.ListsFillingDto;
import com.example.demo.model.dto.productcomments.ProductCommentsDto;
import com.example.demo.model.entity.ListsFilling;
import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.User;
import com.example.demo.service.listFilling.ListFillingService;
import com.example.demo.service.listPermission.ListPermissionService;
import com.example.demo.service.myList.MyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ListFillingService listFillingService;
    private final MyListService myListService;
    private final ListPermissionService listPermissionService;

    @Override
    public void addAttributeInfoProduct(Model model, ListsFillingDto listsFillingDto, MyList myList) {
        model.addAttribute("product", listsFillingDto);
        model.addAttribute("MyList", myList);
    }

    @Override
    public void addAttributeCommentsProduct(Model model, List<ProductCommentsDto> productCommentsDtos, ListsFillingDto listsFillingDto) {
        model.addAttribute("comments",  productCommentsDtos);
        model.addAttribute("product",listsFillingDto);
    }

    @Override
    public void getMyLists(Model model, List<MyList> myLists) {
        model.addAttribute("myLists", myLists);
    }

    @Override
    public void getInfoMyList(Model model, Long id) {
        if ((long) listFillingService.getListFillingByIdListCompletedTrue(id).size() != 0) {
            model.addAttribute("myListFromFillingServiceCompletedTrue", listFillingService.getListFillingByIdListCompletedTrue(id));
        }
        if ((long) listFillingService.getListFillingByIdListCompletedFalse(id).size() != 0) {
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

        model.addAttribute("newUser", new User());
    }

    @Override
    public void getMessageException(Model model, String e) {
        model.addAttribute("MessageException", e);
    }


}
