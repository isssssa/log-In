package com.example.testbeck.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myController {

    @GetMapping("/")
    @Secured("ROLE_USER")
    public String secureEndpoint() {
        return "This is a secure endpoint!";
    }
}