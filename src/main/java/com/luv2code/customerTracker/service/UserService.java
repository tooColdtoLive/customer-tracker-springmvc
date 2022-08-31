package com.luv2code.customerTracker.service;


import com.luv2code.customerTracker.entity.User;
import com.luv2code.customerTracker.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveOrUpdateUser(User user);

    void saveOrUpdateUser(CrmUser user);

    User getUserById(int userId);

    User getUserByUsername(String username);

    List<User> getUsers();

    void deleteUser(int userId);
}
