package com.example.demo.services.listfilling;

import com.example.demo.model.ListsFilling;
import com.example.demo.model.MyList;
import com.example.demo.repositories.ListFillingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ListFillingService {

    public List<ListsFilling> getListFillingByIdListCompletedTrue(Long idList);

    public List<ListsFilling> getListFillingByIdListCompletedFalse(Long idList);

    public ListsFilling getListFillingById(Long id);

    public String getNameListFillingByIdList(Long idList);

    public void saveListFilling(ListsFilling listFilling);

    public void deleteListFillingById(Long idListFilling);

    //Калькулятор покупок
    public Double getToBuyMyListById(Long id);

    public Double getPurchasedMyListById(Long id);

    public Double getActualPurchasedMyListById(Long id);

    public List getPurchasedBuyerByIdList(Long id);



}
