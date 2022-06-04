package com.example.demo.services;

import com.example.demo.model.MyList;
import com.example.demo.repositories.MyListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyListService {
    private final MyListRepository listRepository;

    public List<MyList> getListByName(String name, Long idUser) {
        List<MyList> myLists = listRepository.findByIdUser(idUser);
        for (MyList myList : myLists) {
            if (Objects.equals(myList.getName(), name)) {
                List<MyList> myLists1 = new ArrayList<>();
                myLists1.add(myList);
                return myLists1;
            }
        }
        return myLists;
    }

    public List<MyList> findByIdUser(Long idUser) {
        return listRepository.findByIdUser(idUser);
    }

    public void saveMyList(MyList myList) {
        log.info("Saving new {}", myList);
        listRepository.save(myList);
    }


    public void deleteMyListById(Long idList) {
        log.info("Delete by id: {}", idList);
        listRepository.deleteById(idList);
    }

    public MyList getMyListById(Long id) {
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listRepository.findById(id).orElse(null);
    }



}
