package com.example.demo.services.listfilling;

import com.example.demo.model.entity.ListsFilling;
import com.example.demo.repositories.ListFillingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListFillingServiceImpl implements ListFillingService {
    private final ListFillingRepository listFillingRepository;

    @Override
    public List<ListsFilling> getListFillingByIdListCompletedTrue(Long idList){
        log.info("getListFillingByIdListCompletedTrue {}", listFillingRepository.getListFillingByIdListCompletedTrue(idList));
        return listFillingRepository.getListFillingByIdListCompletedTrue(idList);
    }

    @Override
    public List<ListsFilling> getListFillingByIdListCompletedFalse(Long idList){
        log.info("getListFillingByIdListCompletedFalse {}", listFillingRepository.getListFillingByIdListCompletedFalse(idList));
        return listFillingRepository.getListFillingByIdListCompletedFalse(idList);
    }

    @Override
    public ListsFilling getListFillingById(Long id){
        log.info("Get by id (getListFillingById): {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findById(id).orElse(null);
    }

    @Override
    public String getNameListFillingByIdList(Long idList){
        log.info("getNameListFillingByIdList {}", listFillingRepository.getNameListFillingByIdList(idList));
        return listFillingRepository.getNameListFillingByIdList(idList);
    }

    @Override

    public ListsFilling saveListFilling(ListsFilling listFilling) {
        log.info("Saving List Filling {}", listFilling);
        return listFillingRepository.save(listFilling);
    }

    @Override
    public void deleteListFillingById(Long idListFilling){
        log.info("Delete List Filling by id: {}", idListFilling);
        listFillingRepository.deleteById(idListFilling);
    }

    //Калькулятор покупок
    @Override
    public Double getToBuyMyListById(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findToBuyByIdList(id);
    }

    @Override
    public Double getPurchasedMyListById(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findPurchasedByIdList(id);
    }

    @Override
    public Double getActualPurchasedMyListById(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findActualPurchasedByIdList(id);
    }

    @Override
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
