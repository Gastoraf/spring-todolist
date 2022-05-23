package com.example.demo.services;

import com.example.demo.model.ListPermission;
import com.example.demo.model.MyList;
import com.example.demo.repositories.ListPermissionRepository;
import com.example.demo.repositories.MyListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListPermissionService {
    private final ListPermissionRepository listPermissionRepository;

    public void addListPermission(ListPermission listPpermission) {
        log.info("Saving new ListPermission {}", listPpermission);
        listPermissionRepository.save(listPpermission);
    }

    public void deleteListPermissionById(Long id){
        log.info("Delete ListPermission by id: {}", id);
        listPermissionRepository.deleteById(id);
    }

    public ListPermission getListPermissionById(Long id){
        log.info("Get ListPermission by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listPermissionRepository.findById(id).orElse(null);
    }
}
