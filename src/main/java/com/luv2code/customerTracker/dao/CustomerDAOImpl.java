package com.luv2code.customerTracker.dao;

import com.luv2code.customerTracker.entity.Customer;
import com.luv2code.customerTracker.util.CustomerSortUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Locale;

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
    public List<Customer> getCustomers(int sort) {

        String sortColumn = null;

        switch (sort){
            case CustomerSortUtil.FIRST_NAME :
                sortColumn = "firstName";
                break;

            case CustomerSortUtil.EMAIL :
                sortColumn = "email";
                break;

            case CustomerSortUtil.LAST_NAME :
            default:
                sortColumn = "lastName";
                break;
        }

        return sessionFactory.getCurrentSession().createQuery("from Customer order by " + sortColumn, Customer.class).getResultList();
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

    @Override
    public List<Customer> searchCustomer(String searchType, String searchString) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("lastName")));

        if(searchString != null && !searchString.trim().isEmpty()) {
            criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get(searchType)),
                    "%" + searchString.toLowerCase() + "%"));
        }

       return session.createQuery(criteriaQuery).getResultList();
    }
}
