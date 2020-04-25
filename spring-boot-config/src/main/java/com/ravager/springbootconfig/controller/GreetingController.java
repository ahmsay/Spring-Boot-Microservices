package com.ravager.springbootconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {

    @Value("${my.greeting: getting greeting failed}")
    private String myGreeting;

    @Value("${my.list.values}")
    private List<String> values;

    @Value("#{${dbValues}}")
    private Map<String, String> dbValues;

    @GetMapping("/greeting")
    public String greeting() {
        return myGreeting + values + dbValues;
    }
}
