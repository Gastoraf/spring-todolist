package com.example.demo.service.listFilling;

import com.example.demo.model.dto.listFilling.CreateListsFillingDto;
import com.example.demo.model.entity.ListsFilling;
import com.example.demo.model.entity.User;
import com.example.demo.model.mapping.ListsFillingMapper;
import com.example.demo.repositorie.ListFillingRepository;
import com.example.demo.service.myList.MyListService;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListFillingServiceImpl implements ListFillingService {
    private final UserService userService;
    private final MyListService myListService;

    private final ListFillingRepository listFillingRepository;

    private final ListsFillingMapper listsFillingMapper;

    @Override
    public List<ListsFilling> getListFillingByIdListCompletedTrue(Long idList) {
        log.info("findByCompletedTrueAndListsId {}", listFillingRepository.getListFillingByIdListCompletedTrue(idList));
        return listFillingRepository.getListFillingByIdListCompletedTrue(idList);
    }

    @Override
    public List<ListsFilling> getListFillingByIdListCompletedFalse(Long idList) {
        log.info("getListFillingByIdListCompletedFalse {}", listFillingRepository.getListFillingByIdListCompletedFalse(idList));
        return listFillingRepository.getListFillingByIdListCompletedFalse(idList);
    }

    @Override
    public ListsFilling getListFillingById(Long id) {
        log.info("Get by id (getListFillingById): {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findById(id).orElse(null);
    }

    @Override

    public ListsFilling saveListFilling(ListsFilling listFilling) {
        log.info("Saving List Filling {}", listFilling);
        return listFillingRepository.save(listFilling);
    }

    @Override
    public ListsFilling saveListFilling(Long id, CreateListsFillingDto createListsFillingDto, HttpServletRequest request) {
        ListsFilling listsFilling = listsFillingMapper.dtoToModel(createListsFillingDto);
        listsFilling.setUser(userService.getUserByName(request.getRemoteUser()));
        listsFilling.setId(null);
        listsFilling.setCompleted(false);

        listsFilling.setLists(myListService.getMyListById(id));
        log.info("Saving List Filling {}", listsFilling);
        return listFillingRepository.save(listsFilling);
    }

    @Override
    public void deleteListFillingById(Long id) {
        log.info("Delete List Filling by id: {}", id);
//        ListsFilling listsFilling = listFillingRepository.findById(id).orElse(null);
//        //.orElseThrow(() -> new AccessRuntimeException("Планета не найдена.", HttpStatus.NOT_FOUND));
//        assert listsFilling != null;
        listFillingRepository.deleteById(id);
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

    @Override
    public ResponseEntity<ListsFilling> updateCompletedById(Long id) {
        try {
            ListsFilling listsFilling = getListFillingById(id);
            listsFilling.setCompleted(!listsFilling.getCompleted());
            return new ResponseEntity<>(listFillingRepository.save(listsFilling), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ListsFilling> updateListFilling(Long id, ListsFilling updateListsFilling) {
        try {
            ListsFilling listsFilling = getListFillingById(id);

            updateListsFilling.setId(id);
            updateListsFilling.setLists(listsFilling.getLists());
            User user = userService.getUserByName(updateListsFilling.getUser().getName());
            updateListsFilling.setUser(user);
            return new ResponseEntity<>(listFillingRepository.save(updateListsFilling), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//
//    public MyList getBoughtActuallyMyListById(Long id) {
//        log.info("Get by id: {}", id);
//        //TODO: Здесь можно исключение провернуть
//        return listFillingRepository.findById(id).orElse(null);
//    }


}
