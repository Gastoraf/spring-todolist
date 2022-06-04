package com.example.demo.services;

import com.example.demo.model.ListsFilling;
import com.example.demo.model.MyList;
import com.example.demo.repositories.ListFillingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListFillingService {
    private final ListFillingRepository listFillingRepository;

    public List<ListsFilling> getListFillingByIdListCompletedTrue(Long idList){
        log.info("getListFillingByIdListCompletedTrue {}", listFillingRepository.getListFillingByIdListCompletedTrue(idList));
        return listFillingRepository.getListFillingByIdListCompletedTrue(idList);
    }

    public List<ListsFilling> getListFillingByIdListCompletedFalse(Long idList){
        log.info("getListFillingByIdListCompletedFalse {}", listFillingRepository.getListFillingByIdListCompletedFalse(idList));
        return listFillingRepository.getListFillingByIdListCompletedFalse(idList);
    }

    public ListsFilling getListFillingById(Long id){
        log.info("Get by id (getListFillingById): {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findById(id).orElse(null);
    }

    public String getNameListFillingByIdList(Long idList){
        log.info("getNameListFillingByIdList {}", listFillingRepository.getNameListFillingByIdList(idList));
        return listFillingRepository.getNameListFillingByIdList(idList);
    }

    public void saveListFilling(ListsFilling listFilling) {
        log.info("Saving List Filling {}", listFilling);
        listFillingRepository.save(listFilling);
    }

    public void deleteListFillingById(Long idListFilling){
        log.info("Delete List Filling by id: {}", idListFilling);
        listFillingRepository.deleteById(idListFilling);
    }

    //Калькулятор покупок
    public Double getToBuyMyListById(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findToBuyByIdList(id);
    }

    public Double getPurchasedMyListById(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findPurchasedByIdList(id);
    }

    public Double getActualPurchasedMyListById(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findActualPurchasedByIdList(id);
    }

    public List getPurchasedBuyerByIdList(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findPurchasedBuyerByIdList(id);
    }

//
//    public MyList getBoughtActuallyMyListById(Long id) {
//        log.info("Get by id: {}", id);
//        //TODO: Здесь можно исключение провернуть
//        return listFillingRepository.findById(id).orElse(null);
//    }


}
