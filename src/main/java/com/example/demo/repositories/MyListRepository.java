package com.example.demo.repositories;

import com.example.demo.model.ListsFilling;
import com.example.demo.model.MyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyListRepository extends JpaRepository<MyList, Long> {
    List<MyList> findByName(String name);

    @Query(value = "SELECT l.* FROM lists l, list_permission l_p WHERE l.id = l_p.id_list AND l_p.id_user = :id_user", nativeQuery = true)
    List<MyList> findByIdUser(@Param("id_user") Long id);



//    @Query(value = "SELECT l.* FROM lists l, list_permission l_p WHERE l.id = l_p.id_list AND l_p.id_user = :id_user", nativeQuery = true)
//    List<MyList> findByIdUser(@Param("id_user") Long id);
//
//    @Query(value = "SELECT l.* FROM lists l, list_permission l_p WHERE l.id = l_p.id_list AND l_p.id_user = :id_user", nativeQuery = true)
//    List<MyList> findByIdUser(@Param("id_user") Long id);
}
