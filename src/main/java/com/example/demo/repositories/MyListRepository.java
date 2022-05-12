package com.example.demo.repositories;

import com.example.demo.model.MyList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyListRepository extends JpaRepository<MyList, Long> {
    List<MyList> findByName(String name);
}
