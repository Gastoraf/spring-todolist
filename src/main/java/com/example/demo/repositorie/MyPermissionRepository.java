package com.example.demo.repositorie;

import com.example.demo.model.entity.MyPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPermissionRepository extends JpaRepository<MyPermission, Long> {
}
