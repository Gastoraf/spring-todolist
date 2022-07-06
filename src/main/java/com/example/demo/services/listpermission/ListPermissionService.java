package com.example.demo.services.listpermission;

import com.example.demo.model.ListPermission;
import com.example.demo.model.User;

import java.util.List;


public interface ListPermissionService {

    public void addListPermission(ListPermission listPpermission);

    public void deleteListPermissionById(Long id);

    public void deleteById_userAndId_list(Long id_user, Long id_list);

    public ListPermission getListPermissionById(Long id);

    public List<User> getUsersListPermissionById(Long idList);
}
