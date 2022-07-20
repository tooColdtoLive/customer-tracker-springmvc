package com.luv2code.customerTracker.controller;

import com.luv2code.customerTracker.dao.CustomerDAO;
import com.luv2code.customerTracker.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired  // finds class which implements the interface, can use tgt with @Qualifier
    private CustomerDAO customerDAO;

    @RequestMapping("/list")
    public String listCustomer(Model model){
        List<Customer> customers = customerDAO.getCustomers();

        model.addAttribute("customers", customers);

        return "list-customers";
    }

}
