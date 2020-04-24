package com.ravager.springbootconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Value("${my.greeting}")
    private String myGreeting;

    @GetMapping("/greeting")
    public String greeting() {
        return myGreeting;
    }
}
