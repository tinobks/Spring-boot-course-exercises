package com.develhope.tino.es17deploy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @GetMapping("/tinobokoshev")
    public String devName() {
        return "Tino Bokoshev";
    }
}
