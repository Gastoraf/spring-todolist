package com.example.demo.repositorie;

import com.example.demo.model.entity.ProductComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentsRepository extends JpaRepository<ProductComments, Long> {

    @Query(value = "SELECT * FROM product_comments WHERE id_list_filling = :id_list_filling", nativeQuery = true)
    List<ProductComments> findByIdListsFilling(@Param("id_list_filling") Long id);
}
