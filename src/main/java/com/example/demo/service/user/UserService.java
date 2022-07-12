package com.example.demo.service.user;

import com.example.demo.model.entity.User;


public interface UserService {

    public boolean createUser(User user);

    public User getUserByName(String name);

    public User getUserById(Long id);

    public boolean activateUser(String code);
}
