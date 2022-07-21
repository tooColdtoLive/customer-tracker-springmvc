package com.luv2code.customerTracker.controller;

import com.luv2code.customerTracker.dao.CustomerDAO;
import com.luv2code.customerTracker.entity.Customer;
import com.luv2code.customerTracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController
//@Validated
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @InitBinder // define a data pre-processor
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);    // defined in Spring API, ture means trim to null

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);  // apply StringTrimmerEditor to every String input
    }

    @GetMapping("/list")
    public String listCustomer(Model model){
        List<Customer> customers = customerService.getCustomers();

        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        model.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, // @Valid to trigger validation for the object
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {    // not functioning
            return "customer-form"; // return to the form with error message of validation
        } else {
            customerService.saveCustomer(customer);
            return "redirect:/customer/list";
        }
    }

}
