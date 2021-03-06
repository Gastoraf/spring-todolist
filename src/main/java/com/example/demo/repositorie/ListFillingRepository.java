package com.example.demo.repositorie;

import com.example.demo.model.entity.ListsFilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListFillingRepository extends JpaRepository<ListsFilling, Long> {

//    List<ListsFilling> findByCompletedTrueAndListsId(Long id);



    @Query(value = "SELECT * FROM lists_filling as l_f where l_f.id_list = :id_param and l_f.completed = 1", nativeQuery = true)
    Optional<List<ListsFilling>> getListFillingByIdListCompletedTrue(@Param("id_param") Long id);

    @Query(value = "SELECT * FROM lists_filling as l_f where l_f.id_list = :id_param and l_f.completed = 0", nativeQuery = true)
    Optional<List<ListsFilling>> getListFillingByIdListCompletedFalse(@Param("id_param") Long id);

    @Query(value = "SELECT SUM(price) FROM `lists_filling` WHERE !completed AND id_list = :id_list", nativeQuery = true)
    Double findToBuyByIdList(@Param("id_list") Long id);

    @Query(value = "SELECT SUM(price) FROM `lists_filling` WHERE completed AND id_list = :id_list", nativeQuery = true)
    Double findPurchasedByIdList(@Param("id_list") Long id);

    @Query(value = "SELECT SUM(actual_price) FROM `lists_filling` WHERE completed AND id_list = :id_list", nativeQuery = true)
    Double findActualPurchasedByIdList(@Param("id_list") Long id);

    @Query(value = "SELECT u.name, SUM(price) price, SUM(actual_price) actual_price FROM users u, `lists_filling` l_f WHERE u.id = l_f.id_buyer and completed and id_list = :id_list GROUP BY `id_buyer`", nativeQuery = true)
    List findPurchasedBuyerByIdList(@Param("id_list") Long id);


}
