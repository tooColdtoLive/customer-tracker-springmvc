package com.luv2code.customerTracker.service;

import com.luv2code.customerTracker.entity.Customer;

import java.util.List;

public interface CustomerService {

    void saveCustomer(Customer customer);

    void saveCustomers(List<Customer> customers);

    List<Customer> getCustomers();
}