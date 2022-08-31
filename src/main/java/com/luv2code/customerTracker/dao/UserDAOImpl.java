package com.luv2code.customerTracker.dao;

import com.luv2code.customerTracker.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory securitySessionFactory;

    @Override
    public void saveOrUpdateUser(User user) {
        securitySessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public User getUserById(int userId) {
        return securitySessionFactory.getCurrentSession().get(User.class, userId);
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = securitySessionFactory.getCurrentSession();

        Query query = session.createQuery("from User where username like :username");
        query.setParameter("username", username);

        User retUser = null;
        try {
            retUser = (User) query.getSingleResult();
        }catch (NoResultException ex){
            return null;
        }

        return retUser;
    }

    @Override
    public List<User> getUsers() {
        // get all has no method but create query
        return securitySessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    public void deleteUser(int userId) {
        // remove record reference by id has no method but create query
        Session session = securitySessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from User where id = :userId");
        query.setParameter(":userId", userId);
        query.executeUpdate();
    }
}
