package com.luv2code.customerTracker.service;

import com.luv2code.customerTracker.dao.RoleDAO;
import com.luv2code.customerTracker.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional(value = "securityTransactionManager")
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}
