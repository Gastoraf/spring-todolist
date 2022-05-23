package com.example.demo.repositories;

import com.example.demo.model.ListsFilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListFillingRepository extends JpaRepository<ListsFilling, Long> {


    @Query(value = "SELECT * FROM lists_filling as l_f where l_f.id_list = :id_param", nativeQuery = true)
    List<ListsFilling> getListFillingByIdList(@Param("id_param") Long id);

    @Query(value = "SELECT name FROM lists as l where l.id = :id_param", nativeQuery = true)
    String getNameListFillingByIdList(@Param("id_param") Long id);

}
