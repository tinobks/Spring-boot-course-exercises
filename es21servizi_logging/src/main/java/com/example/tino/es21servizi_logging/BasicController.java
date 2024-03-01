package com.example.tino.es21servizi_logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @Autowired
    private Environment environment;
    @Autowired
    private BasicService basicService;

    private final Logger logger = LoggerFactory.getLogger(BasicController.class);

    @GetMapping("/")
    public String welcome() {
        logger.info("Welcome to my application!");
        return "Welcome to my application!";
    }

    @GetMapping("/exp")
    public double exp() {
        int a = environment.getProperty("custom.env.var1", Integer.class, 2);
        int b = environment.getProperty("custom.env.var2", Integer.class, 8);

        return basicService.calculatePower(a,b);
    }

    @GetMapping("/get-errors")
    public void getErrors() throws Exception {
        logger.error("ERROR!");
        throw new Exception("error");
    }
}
