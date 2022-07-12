package com.example.demo.service.myPermission;

import com.example.demo.model.entity.MyPermission;
import com.example.demo.repositorie.MyPermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class MyPermissionServiceImpl implements MyPermissionService {
    private final MyPermissionRepository myPermissionRepository;

    @Override
    public MyPermission getMyPermissionById(Long id){
        return myPermissionRepository.getById(id);
    }
}
