package com.luv2code.customerTracker.rest;

import com.luv2code.customerTracker.entity.Customer;
import com.luv2code.customerTracker.exception.CustomerNotFoundException;
import com.luv2code.customerTracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Customer customer = customerService.getCustomer(customerId);

        if(customer == null){
            throw new CustomerNotFoundException("Customer not found, id - " + customerId);
        }

        return customer; // jackson phrase null object to empty JSON, user receive no msg
    }

    // for client request body, type must be application/json , else cant process it
    @PostMapping("/customers")
    public Customer addCustomers(@RequestBody Customer customer){   // @RequestBody to read from request body,
                                                                    // and auto convert to POJO by spring REST

        customer.setId(0);  // to clear any existing id and facilitate the empty requirement for saveOrUpdate() in dao

        customerService.saveOrUpdateCustomer(customer); // customer object will be updated

        return customer; // jackson phrase null object to empty JSON, user receive no msg
    }

    // for client request body, type must be application/json , else cant process it
    @PutMapping("/customers")
    public Customer updateCustomers(@RequestBody Customer customer){   // @RequestBody to read from request body,
        customerService.saveOrUpdateCustomer(customer); // customer object will be updated

        return customer; // jackson phrase null object to empty JSON, user receive no msg
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomers(@PathVariable int customerId){
        customerService.deleteCustomer(customerId);

        // return customer; // jackson phrase null object to empty JSON, user receive no msg
    }
}
