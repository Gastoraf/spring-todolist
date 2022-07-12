package com.example.demo.repositorie;

import com.example.demo.model.entity.MyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyListRepository extends JpaRepository<MyList, Long> {
    List<MyList> findByName(String name);

    String findNameById(Long id);

    @Query(value = "SELECT l.* FROM lists l, list_permission l_p WHERE l.id = l_p.id_list AND l_p.id_user = :id_user", nativeQuery = true)
    List<MyList> findByIdUser(@Param("id_user") Long id);


}
