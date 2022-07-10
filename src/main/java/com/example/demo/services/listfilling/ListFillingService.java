package com.example.demo.services.listfilling;

import com.example.demo.model.entity.ListsFilling;

import java.util.List;


public interface ListFillingService {

    List<ListsFilling> getListFillingByIdListCompletedTrue(Long idList);

    List<ListsFilling> getListFillingByIdListCompletedFalse(Long idList);

    ListsFilling getListFillingById(Long id);

    ListsFilling saveListFilling(ListsFilling listFilling);

    void deleteListFillingById(Long idListFilling);

    //Калькулятор покупок
    Double getToBuyMyListById(Long id);

    Double getPurchasedMyListById(Long id);

    Double getActualPurchasedMyListById(Long id);

    List getPurchasedBuyerByIdList(Long id);



}
