package com.luv2code.customerTracker.dao;

import com.luv2code.customerTracker.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    void saveCustomer(Customer customer);

    void saveCustomers(List<Customer> customers);

    List<Customer> getCustomers();
}
