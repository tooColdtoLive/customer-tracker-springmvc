package com.luv2code.customerTracker.dao;

import com.luv2code.customerTracker.entity.User;

import java.util.List;

public interface UserDAO {
    void saveOrUpdateUser(User user);

    User getUserById(int userId);

    User getUserByUsername(String username);

    List<User> getUsers();

    void deleteUser(int userId);
}
