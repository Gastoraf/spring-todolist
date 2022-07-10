package com.example.demo.repositories;

import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    //Вернуть пользователей по id списка
    @Query(value = "SELECT u.* FROM users u, list_permission l_p WHERE u.id = l_p.id_user AND l_p.id_list = :id_list", nativeQuery = true)
    List<User> findUsersByIdList(@Param("id_list") Long id);

//    @Query(value = "SELECT u.* FROM users u WHERE u.activationCode = :code", nativeQuery = true)
//    User findByActivation_code(@Param("code") String code);

    User findByActivationCode(String code);

}
