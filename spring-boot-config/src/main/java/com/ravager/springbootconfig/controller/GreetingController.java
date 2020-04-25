package com.ravager.springbootconfig.controller;

import com.ravager.springbootconfig.DbSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Profile("test")
public class GreetingController {

    @Value("${my.greeting}")
    private String myGreeting;

    private DbSettings dbSettings;

    public GreetingController(final DbSettings dbSettings) {
        this.dbSettings = dbSettings;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return myGreeting + dbSettings.getConnection() + dbSettings.getHost() + dbSettings.getPort();
    }
}
