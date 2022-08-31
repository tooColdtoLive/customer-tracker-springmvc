package com.luv2code.customerTracker.controller;

import com.luv2code.customerTracker.service.UserService;
import com.luv2code.customerTracker.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @InitBinder // define a data pre-processor
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);    // defined in Spring API, ture means trim to null

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);  // apply StringTrimmerEditor to every String input
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage(){
        return "accessDenied";
    }

    @GetMapping("/user/registration")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new CrmUser());

        return "registration";
    }

    @PostMapping("/user/registration/register")
    public String processRegistration(@Validated @ModelAttribute("user") CrmUser crmUser,
                                      BindingResult bindingResult,
                                      Model model){
        if (bindingResult.hasErrors()) {
            return "registration"; // return to the form with error message of validation
        } else if(userService.getUserByUsername(crmUser.getUsername()) != null){
            model.addAttribute("registrationError", "Username already exists.");
            return "registration";
        }else {
            userService.saveOrUpdateUser(crmUser);
            model.addAttribute("registrationSuccess", "Successfully Registered.");
            return "login";
        }
    }
}
