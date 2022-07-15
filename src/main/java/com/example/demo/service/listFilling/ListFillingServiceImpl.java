package com.example.demo.service.listFilling;

import com.example.demo.exception.AccessRuntimeException;
import com.example.demo.model.dto.listFilling.CreateListsFillingDto;
import com.example.demo.model.entity.ListsFilling;
import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.User;
import com.example.demo.model.mapping.ListsFillingMapper;
import com.example.demo.repositorie.ListFillingRepository;
import com.example.demo.repositorie.MyListRepository;
import com.example.demo.repositorie.UserRepository;
import com.example.demo.service.myList.MyListService;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListFillingServiceImpl implements ListFillingService {
    private final UserService userService;
    private final MyListService myListService;

    private final ListFillingRepository listFillingRepository;
    private final UserRepository userRepository;
    private final MyListRepository myListRepository;

    private final ListsFillingMapper listsFillingMapper;

    @Override
    public List<ListsFilling> getListFillingByIdListCompletedTrue(Long idList) {
        log.info("findByCompletedTrueAndListsId {}", idList);
        return listFillingRepository.getListFillingByIdListCompletedTrue(idList).orElseThrow(() -> new AccessRuntimeException("Не удалось найти.",
                HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ListsFilling> getListFillingByIdListCompletedFalse(Long idList) {
        log.info("getListFillingByIdListCompletedFalse {}", listFillingRepository.getListFillingByIdListCompletedFalse(idList));
        return listFillingRepository.getListFillingByIdListCompletedFalse(idList).orElseThrow(() -> new AccessRuntimeException("Не удалось найти.",
                HttpStatus.NOT_FOUND));
    }

    @Override
    public ListsFilling getListFillingById(Long id) {
        log.info("Get by id (getListFillingById): {}", id);
        //TODO: Здесь можно исключение провернуть
        return listFillingRepository.findById(id).orElse(null);
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
    public RedirectView updateCompletedById(Long idList, Long idListFilling, RedirectAttributes redirectAttributes, RedirectView redirectView) {

        log.info("Update Completed By Id : {}", idListFilling);

        ListsFilling listsFilling = listFillingRepository.findById(idListFilling)
                .orElseThrow(() -> new AccessRuntimeException(
                        "Элемент списка не найден. Попробуйте еще раз.",
                        HttpStatus.NOT_FOUND, redirectView));

        listsFilling.setCompleted(!listsFilling.getCompleted());
        listFillingRepository.save(listsFilling);

        return redirectView;

    }

    @Override
    public RedirectView updateListFilling(Long id, ListsFilling updateListsFilling) {

        RedirectView redirectView = new RedirectView("/list/product/info/" + id, true);

        ListsFilling listsFilling = listFillingRepository.findById(id)
                .orElseThrow(() -> new AccessRuntimeException("Не удалось сохранить элемент. Попробуйте еще раз.",
                        HttpStatus.NOT_FOUND, redirectView));

        User user = userRepository.findById(listsFilling.getUser().getId())
                .orElseThrow(() -> new AccessRuntimeException("Не удалось сохранить элемент. Попробуйте еще раз.",
                        HttpStatus.NOT_FOUND, redirectView));

        updateListsFilling.setId(id);
        updateListsFilling.setLists(listsFilling.getLists());
        updateListsFilling.setUser(user);
        listFillingRepository.save(updateListsFilling);
        return new RedirectView("/list/" + listsFilling.getLists().getId(), true);

    }

    @Override
    public RedirectView deleteListFillingById(Long idList, Long idListFilling, RedirectView redirectView) {
        log.info("Delete List Filling by id: {}", idListFilling);

        ListsFilling listsFilling = listFillingRepository.findById(idListFilling)
                .orElseThrow(() -> new AccessRuntimeException(
                        "Элемент списка не найден. Попробуйте еще раз.",
                        HttpStatus.NOT_FOUND, redirectView));
        listFillingRepository.delete(listsFilling);
        return redirectView;

    }


}
