package com.example.demo.services;

import com.example.demo.model.ListPermission;
import com.example.demo.model.User;
import com.example.demo.repositories.ListPermissionRepository;
import com.example.demo.repositories.MyListRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListPermissionService {
    private final ListPermissionRepository listPermissionRepository;
    private final UserRepository userRepository;
    private final MyListRepository myListRepository;

    public void addListPermission(ListPermission listPpermission) {
        log.info("Saving new ListPermission {}", listPpermission);
        listPermissionRepository.save(listPpermission);
    }

    public void deleteListPermissionById(Long id){
        log.info("Delete ListPermission by id: {}", id);
        listPermissionRepository.deleteById(id);
    }

    public void deleteById_userAndId_list(Long id_user, Long id_list){
        log.info("Delete ListPermission by id: {}", id_user);
        ListPermission listPermission = listPermissionRepository.findByUserAndLists(userRepository.getById(id_user), myListRepository.getById(id_list));
        listPermissionRepository.deleteById(listPermission.getId());
    }

    public ListPermission getListPermissionById(Long id){
        log.info("Get ListPermission by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listPermissionRepository.findById(id).orElse(null);
    }

    public List<User> getUsersListPermissionById(Long idList){
        log.info("Get getUsersListPermissionById: {}", idList);
        //TODO: Здесь можно исключение провернуть
        return userRepository.findUsersByIdList(idList);
    }
}
