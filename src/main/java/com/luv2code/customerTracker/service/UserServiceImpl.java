package com.luv2code.customerTracker.service;

import com.luv2code.customerTracker.dao.UserDAO;
import com.luv2code.customerTracker.entity.Role;
import com.luv2code.customerTracker.entity.User;
import com.luv2code.customerTracker.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(value = "securityTransactionManager")
    public void saveOrUpdateUser(User user) {
        // encode password here?
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(roleService.getRoleByName("employee"));
        userDAO.saveOrUpdateUser(user);
    }

    @Override
    @Transactional(value = "securityTransactionManager")
    public void saveOrUpdateUser(CrmUser crmUser) {
        // encode password here?
        User user = new User(crmUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveOrUpdateUser(user);
    }

    @Override
    @Transactional(value = "securityTransactionManager")
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    @Transactional(value = "securityTransactionManager")
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    @Transactional(value = "securityTransactionManager")
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional(value = "securityTransactionManager")
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    @Override
    @Transactional(value = "securityTransactionManager")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new
                org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoleList()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
