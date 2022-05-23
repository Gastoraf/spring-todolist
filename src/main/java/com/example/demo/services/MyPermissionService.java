package com.example.demo.services;

import com.example.demo.model.MyPermission;
import com.example.demo.repositories.MyPermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class MyPermissionService {
    private final MyPermissionRepository myPermissionRepository;

    public MyPermission getMyPermissionById(Long id){
        return myPermissionRepository.getById(id);
    }
}
