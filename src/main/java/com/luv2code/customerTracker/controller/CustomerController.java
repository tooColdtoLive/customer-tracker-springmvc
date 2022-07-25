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
    public String listCustomer(Model model, @RequestParam(value="sort", required = false) String sort){
        List<Customer> customers = null;

        if(sort != null) {
            customers = customerService.getCustomers(Integer.parseInt(sort));
        }else{
            customers = customerService.getCustomers();
        }

        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        model.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("customerId") int customerId, Model model){  // retrieving param in the url
        model.addAttribute("customer", customerService.getCustomer(customerId));

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,
                               BindingResult bindingResult){
        System.out.println("BindingResult: " + bindingResult);

        if (bindingResult.hasErrors()) {    // not functioning
            return "customer-form";
        } else {
            customerService.saveOrUpdateCustomer(customer);
            return "redirect:/customer/list";
        }
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int customerId){
        customerService.deleteCustomer(customerId);
        return "redirect:/customer/list";
    }

    @GetMapping("/searchCustomer")
    public String searchCustomer(@RequestParam(value="searchType") String searchType, @RequestParam(value="searchString", required=false) String searchString, Model model){
        // @RequestParam for any value of form, required=false so that accepts null, delegate the handling
        List<Customer> customers = customerService.searchCustomer(searchType, searchString);

        model.addAttribute("customers", customers);

        return "list-customers";
    }

}
