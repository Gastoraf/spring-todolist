package com.example.demo.service.myList;

import com.example.demo.model.entity.ListsFilling;
import com.example.demo.model.entity.MyList;
import com.example.demo.repositorie.MyListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyListServiceImpl implements MyListService {
    private final MyListRepository listRepository;

    @Override
    public List<MyList> getListByName(String name, Long idUser) {
        List<MyList> myLists = listRepository.findByIdUser(idUser);

        MyList myList = myLists.stream()
                .filter(customer -> name.equals(customer.getName()))
                .findAny()
                .orElse(null);

        if (myList != null) {
            myLists = new ArrayList<>();
            myLists.add(myList);
            return myLists;
        }
        return myLists;
    }

    @Override
    public List<MyList> findByIdUser(Long idUser) {
        return listRepository.findByIdUser(idUser);
    }

    @Override
    public void saveMyList(MyList myList) {
        log.info("Saving new {}", myList);
        listRepository.save(myList);
    }


    @Override
    public void deleteMyListById(Long idList) {
        log.info("Delete MyList by id: {}", idList);
        listRepository.deleteById(idList);
    }

    @Override
    public MyList getMyListById(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listRepository.findById(id).orElse(null);
    }

    @Override
    public String getNameById(Long id) {
        log.info("Get name by id: {}", id);
        return listRepository.findNameById(id);
    }

    @Override
    public ResponseEntity<MyList> updateDate(Long id, String newDate) {
        try {
            MyList myList = getMyListById(id);
            myList.setDate_of_purchase(newDate);
            return new ResponseEntity<>(listRepository.save(myList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
