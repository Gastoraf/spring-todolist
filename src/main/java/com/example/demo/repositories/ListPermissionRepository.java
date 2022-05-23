package com.example.demo.repositories;

import com.example.demo.model.ListPermission;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListPermissionRepository extends JpaRepository<ListPermission, Long> {
    ListPermission findByUserName(String name);
}
