package com.example.demo.services.mylist;

import com.example.demo.model.entity.MyList;

import java.util.List;


public interface MyListService {

    public List<MyList> getListByName(String name, Long idUser);

    public List<MyList> findByIdUser(Long idUser);

    public void saveMyList(MyList myList);

    public void deleteMyListById(Long idList);

    public MyList getMyListById(Long id);

}
