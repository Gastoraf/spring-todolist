package com.example.demo.repositories;

import com.example.demo.model.MyPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPermissionRepository extends JpaRepository<MyPermission, Long> {
}
