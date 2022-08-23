package com.luv2code.customerTracker.rest;

import com.luv2code.customerTracker.entity.Customer;
import com.luv2code.customerTracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomersById(@PathVariable int customerId){
        return customerService.getCustomer(customerId); // jackson phrase null object to empty JSON, user receive no msg
    }
}
