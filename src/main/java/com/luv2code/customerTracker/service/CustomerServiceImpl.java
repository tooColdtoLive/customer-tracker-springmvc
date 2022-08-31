package com.luv2code.customerTracker.service;

import com.luv2code.customerTracker.dao.CustomerDAO;
import com.luv2code.customerTracker.entity.Customer;
import com.luv2code.customerTracker.util.CustomerSortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

// flow layers: Controller -> Service (business logic) -> DAO (data access)
@Service    // subclass of @Component, for business logic services
public class CustomerServiceImpl implements CustomerService{

    @Autowired  // finds class which implements the interface, can use tgt with @Qualifier
    private CustomerDAO customerDAO;

    @Override
    @Transactional(value = "cusTransactionManager")
    public void saveOrUpdateCustomer(Customer customer) {
        customerDAO.saveOrUpdateCustomer(customer);
    }

    @Override
    @Transactional(value = "cusTransactionManager")
    public void saveOrUpdateCustomers(List<Customer> customers) {
        customerDAO.saveOrUpdateCustomers(customers);
    }

    @Override
    @Transactional(value = "cusTransactionManager")
    public Customer getCustomer(int customerId) {
        return customerDAO.getCustomer(customerId);
    }

    @Override
    @Transactional(value = "cusTransactionManager")
    public List<Customer> getCustomers() {
        return getCustomers(CustomerSortUtil.LAST_NAME);
    }

    @Override
    @Transactional(value = "cusTransactionManager")  // to auto begin and to close a session transaction
    // @Transactional moved from DAO to service layer, to ensure DAO depends on service
    // also enable running multiple DAO methods in same transaction, good for rollback
    public List<Customer> getCustomers(int sort) {
        return customerDAO.getCustomers(sort);
    }

    @Override
    @Transactional(value = "cusTransactionManager")
    public void deleteCustomer(int customerId) {
        customerDAO.deleteCustomer(customerId);
    }

    @Override
    @Transactional(value = "cusTransactionManager")
    public void deleteCustomer(Customer customer) {
        customerDAO.deleteCustomer(customer);
    }

    @Override
    @Transactional(value = "cusTransactionManager")
    public void deleteCustomers(List<Customer> customers) {
        customerDAO.deleteCustomers(customers);
    }

    @Override
    @Transactional(value = "cusTransactionManager")
    public List<Customer> searchCustomer(String searchType, String searchString) {
        return customerDAO.searchCustomer(searchType, searchString);
    }
}
