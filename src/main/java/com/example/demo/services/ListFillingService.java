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

    public List<ListsFilling> getListFillingByIdList(Long idList){
        log.info("getListFillingByIdList {}", listFillingRepository.getListFillingByIdList(idList));
        return listFillingRepository.getListFillingByIdList(idList);
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


}
