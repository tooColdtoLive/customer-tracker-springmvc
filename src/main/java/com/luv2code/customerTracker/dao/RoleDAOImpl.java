package com.luv2code.customerTracker.dao;

import com.luv2code.customerTracker.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private SessionFactory securitySessionFactory;

    @Override
    public Role getRoleByName(String name) {
        Session session = securitySessionFactory.getCurrentSession();

        Query query = session.createQuery("from Role like :name ");
        query.setParameter("name", name);

        return (Role) query.getSingleResult();
    }
}
