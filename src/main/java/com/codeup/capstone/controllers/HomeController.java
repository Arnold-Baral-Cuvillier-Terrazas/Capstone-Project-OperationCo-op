package com.codeup.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcome(){
        return "home";
    }

}

// ----------- FOR (PRODUCTION) Uncomment below and  Comment on TOP for file mapping purposes
// ----------- FOR (ORIGIN) Comment below and  UnComment on TOP for file mapping purpose