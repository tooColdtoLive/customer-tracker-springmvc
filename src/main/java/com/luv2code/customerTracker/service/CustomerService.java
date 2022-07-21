package com.luv2code.customerTracker.service;

import com.luv2code.customerTracker.entity.Customer;

import java.util.List;

public interface CustomerService {

    void saveOrUpdateCustomer(Customer customer);

    void saveOrUpdateCustomers(List<Customer> customers);

    Customer getCustomer(int customerId);

    List<Customer> getCustomers();
}
