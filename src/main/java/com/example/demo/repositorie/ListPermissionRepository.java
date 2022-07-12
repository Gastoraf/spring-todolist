package com.example.demo.repositorie;

import com.example.demo.model.entity.ListPermission;
import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListPermissionRepository extends JpaRepository<ListPermission, Long> {
    ListPermission findByUserName(String name);

    ListPermission findByUserAndLists(User user, MyList myList);


}
