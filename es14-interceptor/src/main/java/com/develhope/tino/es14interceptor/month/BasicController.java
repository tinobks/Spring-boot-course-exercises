package com.develhope.tino.es14interceptor.month;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ciao")
public class BasicController {

    @GetMapping("/benvenuto")
    public String welcome() {
        return "Welcome!";
    }
}
