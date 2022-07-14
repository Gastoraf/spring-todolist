package com.example.demo.service.listFilling;

import com.example.demo.model.dto.listFilling.CreateListsFillingDto;
import com.example.demo.model.entity.ListsFilling;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ListFillingService {

    List<ListsFilling> getListFillingByIdListCompletedTrue(Long idList);

    List<ListsFilling> getListFillingByIdListCompletedFalse(Long idList);

    ListsFilling getListFillingById(Long id);

    ListsFilling saveListFilling(ListsFilling listFilling);

    ListsFilling saveListFilling(Long id, CreateListsFillingDto createListsFillingDto, HttpServletRequest request);

    RedirectView deleteListFillingById(Long idList, Long idListFilling, RedirectView redirectView);

    //Калькулятор покупок
    Double getToBuyMyListById(Long id);

    Double getPurchasedMyListById(Long id);

    Double getActualPurchasedMyListById(Long id);

    List getPurchasedBuyerByIdList(Long id);


    RedirectView updateCompletedById(Long idList, Long id, RedirectAttributes redirectAttributes, RedirectView redirectView);

    RedirectView updateListFilling(Long id, ListsFilling updateListsFilling, RedirectView redirectView);
}
