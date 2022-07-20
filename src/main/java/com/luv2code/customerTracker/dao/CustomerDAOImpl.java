package com.luv2code.customerTracker.dao;

import com.luv2code.customerTracker.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository     // subclass of @Component, for Data Access Objects,
                // apply auto scanning, translating JDBC checked exceptions into unchecked exceptions
public class CustomerDAOImpl implements CustomerDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    @Transactional
    public void saveCustomers(List<Customer> customers) {
        Session session = sessionFactory.getCurrentSession();

        for(Customer customer : customers){
            session.save(customer);
        }
    }

    @Override
    @Transactional  // to auto begin and to close a session transaction
    public List<Customer> getCustomers() {

        return sessionFactory.getCurrentSession().createQuery("from Customer", Customer.class).getResultList();
    }
}
