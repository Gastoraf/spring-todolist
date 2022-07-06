package com.example.demo.services.user;

import com.example.demo.model.User;


public interface UserService {

    public boolean createUser(User user);

    public User getUserByName(String name);

    public User getUserById(Long id);

    public boolean activateUser(String code);
}
