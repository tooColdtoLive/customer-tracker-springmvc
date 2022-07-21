package com.luv2code.customerTracker.service;

import com.luv2code.customerTracker.dao.CustomerDAO;
import com.luv2code.customerTracker.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

// flow layers: Controller -> Service (business logic) -> DAO (data access)
@Service    // subclass of @Component, for business logic services
public class CustomerServiceImpl implements CustomerService{

    @Autowired  // finds class which implements the interface, can use tgt with @Qualifier
    private CustomerDAO customerDAO;

    @Override
    @Transactional  // to auto begin and to close a session transaction
    // @Transactional moved from DAO to service layer, to ensure DAO depends on service
    // also enable running multiple DAO methods in same transaction, good for rollback
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }
}
