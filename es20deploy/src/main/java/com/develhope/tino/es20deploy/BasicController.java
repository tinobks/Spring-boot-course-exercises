package com.develhope.tino.es20deploy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    private Environment environment;

    @GetMapping("welcome")
    public String welcome() {
        return environment.getProperty("welcomeMsg");
    }
}
