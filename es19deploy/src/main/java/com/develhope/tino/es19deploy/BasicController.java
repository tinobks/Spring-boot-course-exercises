package com.develhope.tino.es19deploy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    private Environment environment;

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello " + environment.getProperty("devName") + ", your authCode: " + environment.getProperty("authCode");
    }
}
