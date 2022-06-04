package com.example.demo.repositories;

import com.example.demo.model.ListPermission;
import com.example.demo.model.MyList;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListPermissionRepository extends JpaRepository<ListPermission, Long> {
    ListPermission findByUserName(String name);

    ListPermission findByUserAndLists(User user, MyList myList);


}
