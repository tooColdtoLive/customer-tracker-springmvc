package com.luv2code.customerTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showMainMenuPage(){
        return "main-menu";
    }
}
