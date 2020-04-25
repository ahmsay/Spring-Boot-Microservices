package com.ravager.springbootconfig.controller;

import com.ravager.springbootconfig.DbSettings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Profile("test")
public class GreetingController {

    private DbSettings dbSettings;

    public GreetingController(final DbSettings dbSettings) {
        this.dbSettings = dbSettings;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return dbSettings.getConnection() + dbSettings.getHost() + dbSettings.getPort();
    }
}
