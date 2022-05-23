package com.example.demo.services;

import com.example.demo.model.MyList;
import com.example.demo.repositories.MyListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyListService {
    private final MyListRepository listRepository;

    public List<MyList> getListByName(String name){
        if (name != null){
            return listRepository.findByName(name);
        }
        return listRepository.findAll();
    }

    public List<MyList> findByIdUser(Long idUser){
        return listRepository.findByIdUser(idUser);
    }

    public void saveMyList(MyList myList) {
        log.info("Saving new {}", myList);
        listRepository.save(myList);
    }

    public void deleteMyListById(Long idList){
        log.info("Delete by id: {}", idList);
        listRepository.deleteById(idList);
    }

    public MyList getMyListById(Long id){
        log.info("Get by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listRepository.findById(id).orElse(null);
    }

}
