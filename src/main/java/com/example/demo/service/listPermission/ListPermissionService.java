package com.example.demo.service.listPermission;

import com.example.demo.model.entity.ListPermission;
import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ListPermissionService {

    void addListPermission(ListPermission listPermission);

    void addListPermission(MyList myList, HttpServletRequest request);

    void addListPermission(String nameUser, Long idList);

    void deleteListPermissionById(Long id);

    void deleteById_userAndId_list(Long id_user, Long id_list);

    ListPermission getListPermissionById(Long id);

    List<User> getUsersListPermissionById(Long idList);


}
