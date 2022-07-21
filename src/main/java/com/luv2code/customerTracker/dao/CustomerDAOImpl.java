package com.luv2code.customerTracker.dao;

import com.luv2code.customerTracker.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    public void saveOrUpdateCustomer(Customer customer) {
        sessionFactory.getCurrentSession().saveOrUpdate(customer);
    }

    @Override
    public void saveOrUpdateCustomers(List<Customer> customers) {
        Session session = sessionFactory.getCurrentSession();

        for(Customer customer : customers){
            session.saveOrUpdate(customer);
        }
    }

    @Override
    public Customer getCustomer(int customerId) {
        return sessionFactory.getCurrentSession().get(Customer.class, customerId);
    }

    @Override
    public List<Customer> getCustomers() {

        return sessionFactory.getCurrentSession().createQuery("from Customer order by lastName", Customer.class).getResultList();
    }

    @Override
    public void deleteCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", customerId);

        query.executeUpdate();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        sessionFactory.getCurrentSession().remove(customer);
    }

    @Override
    public void deleteCustomers(List<Customer> customers) {
        Session session = sessionFactory.getCurrentSession();

        for(Customer customer : customers){
            session.remove(customer);
        }
    }
}
