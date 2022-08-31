package com.luv2code.customerTracker.dao;

import com.luv2code.customerTracker.entity.Role;

public interface RoleDAO {
    Role getRoleByName(String name);
}
